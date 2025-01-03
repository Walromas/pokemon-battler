package com.PokemonBattler.API.Parse;

import java.io.StringReader;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Provider;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;

import com.PokemonBattler.Builder.Pokemon.Pokemon;
import com.PokemonBattler.Builder.PokemonBuilder;
import com.PokemonBattler.Builder.Stats.Stats;
import com.PokemonBattler.Builder.Types.Types;

@ApplicationScoped
public class PokemonParser implements APIParser<Pokemon> {
    @Inject
    Provider<PokemonBuilder> pokemonBuilderProvider;

    @Override
    public Pokemon parse(final String jsonResponse) {
        JsonObject pokemonJson = Json.createReader(new StringReader(jsonResponse)).readObject();

        PokemonBuilder pokemonBuilder = pokemonBuilderProvider.get();

        String pName = pokemonJson.getString("name");


        List<Types> pTypes = pokemonJson.getJsonArray("types").stream()
                .map(typeValue -> {
                    String typeName = typeValue.asJsonObject()
                            .getJsonObject("type")
                            .getString("name").toUpperCase();

                    return Types.valueOf(typeName);
                })
                .toList();
        Stats pStats = new Stats(
                pokemonJson.getJsonArray("stats").getJsonObject(0).getInt("base_stat"), // hp
                pokemonJson.getJsonArray("stats").getJsonObject(1).getInt("base_stat"), // attack
                pokemonJson.getJsonArray("stats").getJsonObject(2).getInt("base_stat"), // defense
                pokemonJson.getJsonArray("stats").getJsonObject(3).getInt("base_stat"), // special-attack
                pokemonJson.getJsonArray("stats").getJsonObject(4).getInt("base_stat"), // special-defense
                pokemonJson.getJsonArray("stats").getJsonObject(5).getInt("base_stat")  // speed
        );
        Map<String, Integer> pMoveSet = pokemonJson.getJsonArray("moves").stream()
                .map(moveValue -> {
                    JsonObject moveObject = moveValue.asJsonObject().getJsonObject("move");
                    String moveName = moveObject.getString("name");

                    JsonArray versionGroupDetails = moveValue.asJsonObject().getJsonArray("version_group_details");
                    JsonObject latestVersion = versionGroupDetails.getJsonObject(versionGroupDetails.size() - 1);
                    int levelLearned = latestVersion.getInt("level_learned_at");
                    String learnMethod = latestVersion.getJsonObject("move_learn_method")
                            .getString("name");


                    if (Objects.equals(learnMethod, ("level-up"))) {
                        return Map.entry(moveName, levelLearned);
                    }else {
                        return null;
                    }

                })
                .filter(Objects::nonNull)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue
                ));

        String pFrontSprite = pokemonJson.getJsonObject("sprites").getString("front_default");
        String pBackSprite = pokemonJson.getJsonObject("sprites").getString("back_default");

        return pokemonBuilder
                .setName(pName)
                .setBackSpriteURL(pBackSprite)
                .setFrontSpriteURL(pFrontSprite)
                .setLevel(5)
                .setStats(pStats)
                .setTypes(pTypes)
                .setMoveSet(pMoveSet)
                .setCurrentMoves(pMoveSet)
                .build();


    }
}
