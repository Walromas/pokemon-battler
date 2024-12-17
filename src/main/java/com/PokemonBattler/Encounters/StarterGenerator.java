package com.PokemonBattler.Encounters;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import com.PokemonBattler.API.PokemonREST.PokemonService;
import com.PokemonBattler.Builder.Pokemon.Pokemon;
import com.PokemonBattler.GUI.BattleScene;

import javafx.stage.Stage;
@ApplicationScoped
public class StarterGenerator {
    @Inject
    PokemonService pokemonService;
    @Inject
    BattleScene battleScene;
    public List<Pokemon> generateStarters() {
        Random rand = new Random();
        int upperbound = 649;
        List<Pokemon> starterList = new ArrayList<>();

        for(int i = 0; i < 3; i++) {
            Long randomId = (long) rand.nextInt(upperbound);
            starterList.add(pokemonService.createPokemon(String.valueOf(randomId)));
        }
        return starterList;
    }

    public void pickStarter(Pokemon selectedPokemon, Stage stage) {
        System.out.println("You chose: " + selectedPokemon.getName());
        pokemonService.savePokemon(selectedPokemon);
        battleScene.startScene(stage);

    }

}
