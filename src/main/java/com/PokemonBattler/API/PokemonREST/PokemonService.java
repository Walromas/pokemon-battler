package com.PokemonBattler.API.PokemonREST;

import java.util.Optional;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import com.PokemonBattler.API.Parse.PokemonParser;
import com.PokemonBattler.Builder.Pokemon.Pokemon;

@ApplicationScoped
public class PokemonService {

    @Inject
    PokemonApiClient pokemonApiClient;
    @Inject
    PokemonParser pokemonParser;
    @Inject
    PokemonRepository pokemonRepository;

    public Pokemon createPokemon(String id) {
        String jsonResponse = pokemonApiClient.getPokemonData(id);
        return pokemonParser.parse(jsonResponse);
    }
    public void savePokemon(Pokemon pokemon) {
        pokemonRepository.savePokemon(pokemon);
    }
}
