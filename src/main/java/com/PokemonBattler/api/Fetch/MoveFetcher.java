package com.PokemonBattler.api.Fetch;

import jakarta.inject.Inject;

import com.PokemonBattler.Builder.Move;

public interface MoveFetcher {

    Move fetchMove(String moveName);
}
