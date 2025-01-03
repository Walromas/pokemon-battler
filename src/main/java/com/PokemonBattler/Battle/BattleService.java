package com.PokemonBattler.Battle;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import com.PokemonBattler.Builder.Move.Move;
import com.PokemonBattler.Builder.Pokemon.Pokemon;
import com.PokemonBattler.GUI.BattleUI;
@ApplicationScoped
public class BattleService {
    private Pokemon playerPokemon;
    private Pokemon encounterPokemon;
    @Inject
    BattleUI battleUI;

    public void initializeBattle(Pokemon playerPokemon, Pokemon encounterPokemon) {
        this.playerPokemon = playerPokemon;
        this.encounterPokemon = encounterPokemon;
        battleUI.updateBattleUI(playerPokemon, encounterPokemon);
    }
    public void speedCheck(Pokemon playerPokemon, Pokemon encounterPokemon) {
        if(playerPokemon.getStats().getSpeed() > encounterPokemon.getStats().getSpeed()) {
        }
    }
}
