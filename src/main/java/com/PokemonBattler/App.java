package com.PokemonBattler;

import com.PokemonBattler.Builder.Move.ApiMoveFetcher;
import com.PokemonBattler.Builder.Move.MoveFetcher;
import com.PokemonBattler.Builder.Pokemon.Pokemon;
import com.PokemonBattler.api.Parse.PokemonParser;
import com.PokemonBattler.api.PokemonApiClient;
import com.PokemonBattler.api.PokemonRepository;

public class App
{
    public static void main( String[] args )
    {
        PokemonParser pokemonParser = new PokemonParser();
        PokemonRepository pokemonRepo = new PokemonRepository();
        Pokemon pokemon = pokemonParser.parse(PokemonApiClient.getPokemonData("charmander"));
        pokemonRepo.savePokemon(pokemon);


    }
}
