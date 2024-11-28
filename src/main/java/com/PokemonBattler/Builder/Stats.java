package com.PokemonBattler.Builder;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Stats {
    @Column(name = "special_attack")
    private int specialAttack;
    @Column(name = "special_defence")
    private int specialDefence;
    @Column(name = "attack")
    private int attack;
    @Column(name = "defence")
    private int defence;
    @Column(name = "speed")
    private int speed;
    @Column(name = "hp")
    private int hp;


    public Stats(int hp, int attack, int defence, int specialAttack, int specialDefence, int speed) {
        this.attack = attack;
        this.defence = defence;
        this.speed = speed;
        this.hp = hp;
        this.specialAttack = specialAttack;
        this.specialDefence = specialDefence;
    }

    public Stats() {

    }

    @Override
    public String toString() {
        return "Stats{hp='" + hp + "', attack=" + attack + ", defence=" + defence + ", special attack=" + specialAttack + ", special defence=" + specialDefence + ", speed=" + speed + "}";
    }

    public int getHp() {
        return hp;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefence() {
        return defence;
    }

    public int getSpeed() {
        return speed;
    }
    public int getSpecialAttack() {
        return specialAttack;
    }

    public int getSpecialDefence() {
        return specialDefence;
    }
}

