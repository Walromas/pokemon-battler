package com.PokemonBattler.Encounters;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import com.PokemonBattler.Builder.Pokemon.Pokemon;
import com.PokemonBattler.api.Parse.PokemonParser;
import com.PokemonBattler.api.PokemonApiClient;
import com.PokemonBattler.api.PokemonRepository;

import javafx.stage.Stage;
@ApplicationScoped
public class StarterGenerator {
    @Inject
    PokemonParser pokemonParser;
    public List<Pokemon> generateStarters() {
        Random rand = new Random();
        int upperbound = 906;
        List<Pokemon> starterList = new ArrayList<>();

        for(int i = 0; i < 3; i++) {
            int randomId = rand.nextInt(upperbound);
            starterList.add(pokemonParser.parse(PokemonApiClient.getPokemonData(String.valueOf(randomId))));
        }
        return starterList;
    }

    public void pickStarter(Pokemon selectedPokemon, Stage stage) {
        System.out.println("You chose: " + selectedPokemon.getName());
        PokemonRepository.savePokemon(selectedPokemon);
        stage.close();

    }

}
