package com.PokemonBattler.API.Parse;

public interface APIParser<Variable> {
    Variable parse(String jsonResponse);
}
