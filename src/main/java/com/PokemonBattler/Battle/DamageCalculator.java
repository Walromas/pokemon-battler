package com.PokemonBattler.Battle;

import com.PokemonBattler.Builder.Move.Move;
import com.PokemonBattler.Builder.Pokemon.Pokemon;

public class DamageCalculator {

    public static int calculateDamage(Move move, Pokemon opponent, Pokemon defender) {
        double baseDamage = (((2 * opponent.getLevel() + 2) / 5) * move.getPower() * (opponent.getStats().getAttack() / defender.getStats().getDefence())) / 50 + 2;

        double modifier = TypeEffectivenessService.getEffectiveness(move.getType(), defender.getTypes());

        return (int) (baseDamage * modifier);

    }
}
