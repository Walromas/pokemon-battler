package com.PokemonBattler.API.PokemonREST;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

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
    public CompletableFuture<Pokemon> createPokemon(String id) {
        return pokemonApiClient.getPokemonData(id)
                .thenApplyAsync(jsonResponse -> pokemonParser.parse(jsonResponse));

    }
    public void savePokemon(Pokemon pokemon) {
        pokemonRepository.savePokemon(pokemon);
    }

    public Pokemon findPokemonById(Long id) {
        return pokemonRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Pokemon with ID " + id + " not found."));
    }
}
