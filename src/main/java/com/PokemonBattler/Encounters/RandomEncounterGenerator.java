package com.PokemonBattler.Encounters;

import java.util.Random;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import com.PokemonBattler.API.PokemonREST.PokemonService;
import com.PokemonBattler.Builder.Pokemon.Pokemon;

@ApplicationScoped
public class RandomEncounterGenerator {
    @Inject
    PokemonService pokemonService;

    public Pokemon generateRandPokemon() {
        Random rand = new Random();
        int upperbound = 906;
        Long randomId = (long) rand.nextInt(upperbound);
        return pokemonService.createPokemon(String.valueOf(randomId));
    }
}
