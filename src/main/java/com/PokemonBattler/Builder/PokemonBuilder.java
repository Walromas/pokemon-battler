package com.PokemonBattler.Builder;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;

import com.PokemonBattler.API.MoveREST.MoveService;
import com.PokemonBattler.Builder.Move.Move;
import com.PokemonBattler.Builder.Pokemon.Pokemon;
import com.PokemonBattler.Builder.Stats.Stats;
import com.PokemonBattler.Builder.Stats.StatsCalculator;
import com.PokemonBattler.Builder.Types.Types;

@Dependent
public class PokemonBuilder {
    private String name;
    private String backSpriteURL;
    private String frontSpriteURL;
    private List<Types> types;
    private Stats stats;
    private Map<String, Integer> moveSet;
    private Set<Move> currentMoves;
    private int level;

    @Inject
    MoveService moveService;


    public PokemonBuilder setName(String name) {
        this.name = name;
        return this;
    }
    public PokemonBuilder setBackSpriteURL(String backSpriteURL) {
        this.backSpriteURL = backSpriteURL;
        return this;
    }
    public PokemonBuilder setFrontSpriteURL(String frontSpriteURL) {
        this.frontSpriteURL = frontSpriteURL;
        return this;
    }

    public PokemonBuilder setTypes(List<Types> types) {
        this.types = types;
        return this;
    }

    public PokemonBuilder setStats(Stats stats) {
        this.stats = StatsCalculator.calculateStats(stats, this.level);
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
                .map(moveService::createMove)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
        return this;
    }
    public PokemonBuilder setLevel( int level) {
        this.level = level;
        return this;
    }

    public Pokemon build() {
        return new Pokemon(name, level, types, stats, moveSet, currentMoves,backSpriteURL, frontSpriteURL);
    }
}