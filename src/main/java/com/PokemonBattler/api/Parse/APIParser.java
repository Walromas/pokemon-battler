package com.PokemonBattler.api.Parse;

public interface APIParser<Variable> {
    Variable parse(String jsonResponse);
}
