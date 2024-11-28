package com.PokemonBattler.Builder;

import static com.PokemonBattler.api.DataParser.parseMoveData;
import static com.PokemonBattler.api.PokemonApiClient.getMoveData;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class PokemonBuilder {
    private String name;
    private List<Types> types;
    private Stats stats;
    private Map<String, Integer> moveSet;
    private Set<Move> currentMoves;
    private int level;

    public PokemonBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public PokemonBuilder setTypes(List<Types> types) {
        this.types = types;
        return this;
    }

    public PokemonBuilder setStats(Stats stats) {
        int hp = ((2 * stats.getHp()* this.level)/100) + this.level + 10;
        int attack = ((2 * stats.getAttack()* this.level)/100) + 5;
        int defence = ((2 * stats.getDefence()* this.level)/100) + 5;
        int specialAttack = ((2 * stats.getSpecialAttack()* this.level)/100) + 5;
        int speicalDefence = ((2 * stats.getSpecialDefence()* this.level)/100) + 5;
        int speed = ((2 * stats.getSpeed()* this.level)/100) + 5;
        this.stats = new Stats(hp, attack, defence, specialAttack, speicalDefence, speed);
        return this;
    }

    public PokemonBuilder setMoveSet(Map<String, Integer> moveSet) {
        this.moveSet = moveSet;
        return this;
    }
    public PokemonBuilder setCurrentMoves(Map<String, Integer> moveSet) {
        List<String> recentMoves = moveSet.entrySet().stream()
                .filter(entry -> entry.getValue() <= this.level)
                .sorted(Comparator.comparingInt(Map.Entry<String, Integer>::getValue))
                .map(Map.Entry::getKey)
                .limit(4)
                .toList();

        this.currentMoves = recentMoves.stream()
                .map(moveName -> {
                    String moveData = getMoveData(moveName);
                    if (moveData != null) {
                        return parseMoveData(moveData);
                    } else {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
        return this;
    }
    public PokemonBuilder setLevel( int level) {
        this.level = level;
        return this;
    }

    public Pokemon build() {
        return new Pokemon(name, level, types, stats, moveSet, currentMoves);
    }
}