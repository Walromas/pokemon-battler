package com.PokemonBattler.api.Fetch;

import com.PokemonBattler.Builder.Move;

public interface MoveFetcher {
    Move fetchMove(String moveName);
}
