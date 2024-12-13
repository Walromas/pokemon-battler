package com.PokemonBattler.Builder.Types;

public enum Types {
    GRASS(new String[] {"Fire", "Ice", "Bug", "Poison", "Flying"}, new String[] {"Water", "Electric"}),
    FIRE(new String[] {"Water", "Rock", "Ground"}, new String[] {"Fire", "Bug", "Steel", "Fairy"}),
    WATER(new String[] {"Electric", "Grass"}, new String[] {"Fire", "Water", "Ice"}),
    ELECTRIC(new String[] {"Ground"}, new String[] {"Electric", "Flying"}),
    FLYING(new String[] {"Electric", "Ice", "Rock"}, new String[] {"Fighting", "Bug", "Grass"}),
    NORMAL(new String[] {"Fighting"}, new String[] {"Ghost"}),
    BUG(new String[] {"Fire", "Flying", "Rock"}, new String[] {"Fighting", "Grass", "Ground"}),
    DARK(new String[] {"Fighting", "Fairy", "Bug"}, new String[] {"Ghost", "Psychic"}),
    DRAGON(new String[] {"Ice", "Dragon", "Fairy"}, new String[] {"Dragon"}),
    FAIRY(new String[] {"Steel", "Poison"}, new String[] {"Fighting", "Dragon", "Dark"}),
    FIGHTING(new String[] {"Flying", "Psychic", "Fairy"}, new String[] {"Normal", "Rock", "Bug", "Steel", "Ice", "Dark"}),
    GHOST(new String[] {"Ghost", "Dark"}, new String[] {"Normal", "Fighting"}),
    ICE(new String[] {"Fire", "Fighting", "Rock", "Steel"}, new String[] {"Grass", "Ground", "Flying", "Dragon"}),
    POISON(new String[] {"Ground", "Psychic"}, new String[] {"Fighting", "Bug", "Fairy"}),
    PSYCHIC(new String[] {"Bug", "Ghost", "Dark"}, new String[] {"Fighting", "Psychic"}),
    ROCK(new String[] {"Water", "Grass", "Ground", "Steel", "Fighting"}, new String[] {"Fire", "Flying", "Bug"}),
    GROUND(new String[] {"Fire", "Electric", "Poison", "Rock", "Steel"}, new String[] {"Water", "Ice", "Grass"}),
    STEEL(new String[] {"Fire", "Fighting", "Ground"}, new String[] {"Bug", "Fairy", "Dragon", "Rock", "Normal", "Psychic"});


    private final String[] weaknesses;
    private final String[] resistances;

    Types(final String[] weaknesses, String[] resistances) {
        this.weaknesses = weaknesses;
        this.resistances = resistances;
    }

    public String[] getWeaknesses() {
        return weaknesses;
    }

    public String[] getResistances() {
        return resistances;
    }

    public boolean isWeakTo(Types mType) {
        for(String elements : weaknesses) {
            if(elements.equals(mType.name())) {
                return true;
            }
        }
        return false;
    }

    public boolean resists(Types mType) {
        for (String elements :resistances) {
            if(elements.equals(mType.name())) {
                return true;
            }
        }
        return false;
    }
};

