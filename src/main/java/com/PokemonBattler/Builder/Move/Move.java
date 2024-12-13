package com.PokemonBattler.Builder.Move;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import com.PokemonBattler.Builder.Types.Types;

@Entity
@Table(name = "move")
public class Move {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Enumerated(EnumType.STRING)
    private Types type;
    @Column(name = "power")
    private int power;
    @Column(name = "accuracy")
    private int accuracy;
    @Column(name = "pp")
    private int pp;

    public Move(String name, Types type, int power, int accuracy,int pp){
        this.name   = name;
        this.type = type;
        this.power = power;
        this.accuracy = accuracy;
        this.pp = pp;
    }

    public Move() {

    }

    @Override
    public String toString() {
        return "Move{name='" + name + "', type=" + type + ", power=" + power + ", accuracy=" + accuracy +", pp=" + pp + "}";
    }
    public String getName() {
        return name;
    }

    public Types getType() {
        return type;
    }

    public int getPower() {
        return power;
    }

    public int getAccuracy() {
        return accuracy;
    }
    public int getPp() {
        return pp;
    }
}
