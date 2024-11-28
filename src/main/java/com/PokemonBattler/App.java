package com.PokemonBattler;

import static com.PokemonBattler.api.PokemonApiClient.getPokemonData;
import static com.PokemonBattler.api.DataParser.parsePokemonData;

import com.PokemonBattler.Builder.Pokemon;
import com.PokemonBattler.api.PokemonService;

public class App
{
    public static void main( String[] args )
    {
        Pokemon pokemon = parsePokemonData(getPokemonData("charmander"));
        PokemonService pokemonService = new PokemonService();
        pokemonService.savePokemon(pokemon);

    }
}
