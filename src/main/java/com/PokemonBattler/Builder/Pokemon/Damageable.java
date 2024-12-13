package com.PokemonBattler.Builder.Pokemon;

import java.util.List;

import com.PokemonBattler.Builder.Types.Types;

public interface Damageable {
    void takeDamage(int damage, Types moveType, List<Types> pokemonTypes);
}
