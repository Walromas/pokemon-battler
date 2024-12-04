package com.PokemonBattler.Builder.Stats;

public class StatsCalculator {
    public static Stats calculateStats(Stats baseStats, int level) {
        int hp = ((2 * baseStats.getHp()* level)/100) + level + 10;
        int attack = ((2 * baseStats.getAttack()* level)/100) + 5;
        int defence = ((2 * baseStats.getDefence()* level)/100) + 5;
        int specialAttack = ((2 * baseStats.getSpecialAttack()* level)/100) + 5;
        int speicalDefence = ((2 * baseStats.getSpecialDefence()* level)/100) + 5;
        int speed = ((2 * baseStats.getSpeed()* level)/100) + 5;

        return new Stats(hp, attack, defence, specialAttack, speicalDefence, speed);
    }
}
