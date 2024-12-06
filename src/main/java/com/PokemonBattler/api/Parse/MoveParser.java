package com.PokemonBattler.api.Parse;

import java.io.StringReader;

import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.JsonNumber;
import jakarta.json.JsonObject;
import jakarta.json.JsonValue;

import com.PokemonBattler.Builder.Move;
import com.PokemonBattler.Builder.Types;

public class MoveParser implements APIParser<Move> {
    @Inject
    public MoveParser() {

    }
    @Override
    public Move parse(final String jsonResponse) {
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
