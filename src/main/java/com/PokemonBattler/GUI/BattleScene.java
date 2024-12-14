package com.PokemonBattler.GUI;

import java.util.Objects;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import com.PokemonBattler.Builder.Pokemon.Pokemon;
import com.PokemonBattler.Encounters.RandomEncounterGenerator;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
@ApplicationScoped
public class BattleScene {
    @Inject
    RandomEncounterGenerator randomEncounterGenerator;
    public void startScene(Stage stage) {
        Image backgroundImage = new Image(Objects.requireNonNull(getClass().getResource("/Images/Battle.png")).toExternalForm());
        ImageView backgroundView = new ImageView(backgroundImage);
        backgroundView.setPreserveRatio(false);
        backgroundView.setFitWidth(800);
        backgroundView.setFitHeight(600);

        Pane root = new Pane(backgroundView);

        backgroundView.fitWidthProperty().bind(root.widthProperty());
        backgroundView.fitHeightProperty().bind(root.heightProperty());

        Pokemon encounter = randomEncounterGenerator.generateRandPokemon();
        String encounterSprite = encounter.getFrontSpriteURL();
        ImageView encounterImageView = new ImageView(new Image(encounterSprite));



        Scene battleScene = new Scene(root, 800, 600);

        stage.setScene(battleScene);
        stage.setFullScreen(true);
        stage.setTitle("Pokémon Battle!");
        stage.show();
    }
}
