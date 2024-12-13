package com.PokemonBattler.Battle;

import java.util.List;

import com.PokemonBattler.Builder.Types.Types;

public class TypeEffectivenessService {
    public static double getEffectiveness(Types mType, List<Types> pTypes) {
        double modifier = 1.0;

        for(Types pType : pTypes) {
            if (pType.isWeakTo(mType)) {
                modifier *= 2.0;
            } else if (pType.resists(mType)) {
                modifier *= 0.5;
            }

        }
        return modifier;
    }
}
