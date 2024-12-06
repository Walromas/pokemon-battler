package com.PokemonBattler.api.Parse;

import java.io.StringReader;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class FormParser implements APIParser<String>{
    @Override
    public String parse(final String jsonResponse) {
        JsonObject formJson = Json.createReader(new StringReader(jsonResponse)).readObject();
        return formJson.getJsonObject("sprites").getString("front_default");

    }
}
