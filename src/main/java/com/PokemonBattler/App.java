package com.PokemonBattler;

import com.PokemonBattler.Builder.Pokemon.Pokemon;
import com.PokemonBattler.Encounters.StarterGenerator;
import com.PokemonBattler.api.Parse.PokemonParser;
import com.PokemonBattler.api.PokemonApiClient;
import com.PokemonBattler.api.PokemonRepository;

public class App
{
    public static void main( String[] args )
    {
        PokemonParser pokemonParser = new PokemonParser();
        StarterGenerator starterGenerator = new StarterGenerator();
        starterGenerator.pickStarter(starterGenerator.generateStarters(pokemonParser));



    }
}
