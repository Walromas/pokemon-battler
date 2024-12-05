package com.PokemonBattler.api.Parse;

import java.io.StringReader;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;

import com.PokemonBattler.api.Fetch.ApiMoveFetcher;
import com.PokemonBattler.api.Fetch.MoveFetcher;
import com.PokemonBattler.Builder.Pokemon.Pokemon;
import com.PokemonBattler.Builder.PokemonBuilder;
import com.PokemonBattler.Builder.Stats.Stats;
import com.PokemonBattler.Builder.Types;

public class PokemonParser implements APIParser<Pokemon> {

    @Override
    public Pokemon parse(final String jsonResponse) {
        JsonObject pokemonJson = Json.createReader(new StringReader(jsonResponse)).readObject();

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
        MoveFetcher moveFetcher = new ApiMoveFetcher();
        return new PokemonBuilder(moveFetcher)
                .setName(pName)
                .setLevel(5)
                .setStats(pStats)
                .setTypes(pTypes)
                .setMoveSet(pMoveSet)
                .setCurrentMoves(pMoveSet)
                .build();


    }
}
