package com.PokemonBattler.Builder;

public enum Types {
    GRASS(new String[] {"Fire", "Ice", "Bug", "Poison", "Flying"}, new String[] {"Water", "Electric"}),
    FIRE(new String[] {"Water","Rock","Ground"}, new String[] {"Fire"}),
    WATER(new String[] {"Grass", "Electric"}, new String[] {"Fire", "Water", "Ice"}),
    ELECTRIC(new String[] {"Ground"}, new String[] {"Electric", "Flying"}),
    FLYING(new String[] {"Electric", "Ice"}, new String[] {"Fighting", "Grass", "Bug"}),
    NORMAL(new String[] {"Fighting"}, new String[] {"Ghost"});

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

