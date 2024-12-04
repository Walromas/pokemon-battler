package com.PokemonBattler.api.Parse;

import com.PokemonBattler.Builder.Move.MoveFetcher;

public interface APIParser<Variable> {
    Variable parse(String jsonResponse);
}
