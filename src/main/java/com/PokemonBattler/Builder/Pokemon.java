package com.PokemonBattler.Builder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.OrderColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "Pokemon")
public class Pokemon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    private int level;
    private int currentHP;
    @Embedded
    private Stats stats;
    @ElementCollection
    @CollectionTable(
            name = "pokemon_move_sets",
            joinColumns = @JoinColumn(name = "pokemon_id")
    )
    @MapKeyColumn(name = "move_name")
    @Column(name = "level_learned")
    private Map<String, Integer> moveSet;
    @ElementCollection(targetClass = Types.class)
    @CollectionTable(
            name = "pokemon_types",
            joinColumns = @JoinColumn(name = "pokemon_id")
    )
    @Enumerated(EnumType.STRING)
    private List<Types> types;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "pokemon_current_moves",
            joinColumns = @JoinColumn(name = "pokemon_id"),
            inverseJoinColumns = @JoinColumn(name = "move_id")
    )
    private Set<Move> currentMoves;

    public Pokemon(String name, int level, List<Types> types, Stats stats, Map<String, Integer> moveSet, Set<Move> currentMoves) {
        this.name = name;
        this.level = level;
        this.types = types != null ? types : new ArrayList<>();
        this.stats = stats;
        this.moveSet = moveSet;
        this.currentMoves = currentMoves;
        this.currentHP = stats.getHp();
    }

    public Pokemon() {

    }

    @Override
    public String toString() {
        return "Pokemon{name='" + name + "', types=" + types + ", stats=" + stats + ", moveSet=" + moveSet + "}";
    }
    public void takeDamage(int damage, Types mType, List<Types> types) {
        for(Types element : types) {
            if (element.isWeakTo(mType)) {
                currentHP -= (damage * 2);
            } else if (element.resists(mType)) {
                currentHP -= (int) (damage * 0.5);
            } else {
                currentHP -= damage;
            }
        }
        if (currentHP < 0) {
            currentHP = 0;
        }
        if (isFainted()) {
            System.out.println(name + " has fainted!");
        }
    }
    public String getName() {
        return name;
    }
    public Stats getStats() {
        return stats;
    }
    public List<Types> getTypes() {
        return types;
    }
    public int getLevel() {
        return level;
    }
    public int getCurrentHP() {
        return currentHP;
    }
    public Map<String, Integer> getMoveSet() {
        return moveSet;
    }
    public Set<Move> getCurrentMoves() {
        return currentMoves;
    }
    public boolean isFainted() {
        return currentHP == 0;
    }
    public void useMove(int index) {
        System.out.println("NOT IMPLEMENTED!");
    }
}

