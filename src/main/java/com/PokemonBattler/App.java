package com.PokemonBattler;

import static com.PokemonBattler.api.PokemonApiClient.getPokemonData;
import static com.PokemonBattler.api.DataParser.parsePokemonData;

import com.PokemonBattler.Builder.Pokemon;
import com.PokemonBattler.api.PokemonRepository;

public class App
{
    public static void main( String[] args )
    {
        Pokemon pokemon = parsePokemonData(getPokemonData("charmander"));
        Pokemon pokemon1 = parsePokemonData(getPokemonData("charmander"));
        PokemonRepository pokemonService = new PokemonRepository();
        pokemonService.savePokemon(pokemon);
        pokemonService.savePokemon(pokemon1);

    }
}
