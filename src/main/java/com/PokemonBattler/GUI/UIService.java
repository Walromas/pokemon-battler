package com.PokemonBattler.GUI;

import java.util.Objects;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import com.PokemonBattler.Builder.Move.Move;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

@ApplicationScoped
public class UIService {
    public Image createPokemonImage(String sprite) {
        Image pokemonImage = new Image(sprite);
        return pokemonImage;
    }
    public Button createMoveButtons(Move move) {
        String moveName = move.getName();
        Label moveLabel = new Label(moveName);
        Button button = new Button();
        button.setGraphic(moveLabel);
        button.setPrefHeight(100);
        button.setPrefWidth(200);
        return button;
    }
    public ProgressBar createHpBar() {
        ProgressBar hpBar = new ProgressBar();
        hpBar.setPrefWidth(150);
        return hpBar;
    }
    public ImageView createBackground() {
        Image backgroundImage = new Image(Objects.requireNonNull(getClass().getResource("/Images/Battle.png")).toExternalForm());
        ImageView backgroundView = new ImageView(backgroundImage);
        backgroundView.setPreserveRatio(false);
        backgroundView.setFitWidth(800);
        backgroundView.setFitHeight(600);
        return backgroundView;
    }
}
