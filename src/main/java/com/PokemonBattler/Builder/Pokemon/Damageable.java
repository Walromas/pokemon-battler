package com.PokemonBattler.Builder.Pokemon;

import java.util.List;

import com.PokemonBattler.Builder.Move.Move;
import com.PokemonBattler.Builder.Types.Types;

public interface Damageable {
    void takeDamage(Move move, Pokemon opponent, Pokemon defender);
}
