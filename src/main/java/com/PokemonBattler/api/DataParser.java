package com.PokemonBattler.api;

import java.io.StringReader;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonNumber;
import jakarta.json.JsonValue;


import com.PokemonBattler.Builder.Move;
import com.PokemonBattler.Builder.Pokemon;
import com.PokemonBattler.Builder.PokemonBuilder;
import com.PokemonBattler.Builder.Stats;
import com.PokemonBattler.Builder.Types;

public class DataParser {

    public static Pokemon parsePokemonData(String jsonResponse) {
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

        return new PokemonBuilder()
                .setName(pName)
                .setLevel(25)
                .setStats(pStats)
                .setMoveSet(pMoveSet)
                .setTypes(pTypes)
                .setCurrentMoves(pMoveSet)
                .build();
    }
    public static Move parseMoveData(String jsonResponse) {
        JsonObject moveJson = Json.createReader(new StringReader(jsonResponse)).readObject();

        String mName = moveJson.getString("name");

        int mAccuracy = 0;
        JsonValue accuracyValue = moveJson.get("accuracy");
        if (accuracyValue instanceof JsonNumber) {
            mAccuracy = ((JsonNumber) accuracyValue).intValue();
        }

        int mPower = 0;
        JsonValue powerValue = moveJson.get("power");
        if (powerValue instanceof JsonNumber) {
            mPower = ((JsonNumber) powerValue).intValue();
        }

        int mPP = 0;
        JsonValue ppValue = moveJson.get("pp");
        if (ppValue instanceof JsonNumber) {
            mPP = ((JsonNumber) ppValue).intValue();
        }

        Types mTypes = Types.NORMAL;
        JsonObject typeObject = moveJson.getJsonObject("type");
        if (typeObject != null) {
            String typeName = typeObject.getString("name", "normal"); // Default to "normal" if missing
            mTypes = Types.valueOf(typeName.toUpperCase());
        }

        return new Move(mName, mTypes, mPower, mAccuracy, mPP);
    }


}

