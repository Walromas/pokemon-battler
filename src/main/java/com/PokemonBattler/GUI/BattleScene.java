package com.PokemonBattler.GUI;

import java.util.Objects;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import com.PokemonBattler.API.PokemonREST.PokemonService;
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
    @Inject
    PokemonService pokemonService;
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
        ImageView encounterImageView = createPokemonImage(encounterSprite);
        encounterImageView.setX(875);
        encounterImageView.setY(280);

        Pokemon pokemon = pokemonService.findPokemonById(1L);
        String pokemonSprite = pokemon.getBackSpriteURL();
        ImageView pokemonImageView = createPokemonImage(pokemonSprite);
        pokemonImageView.setX(330);
        pokemonImageView.setY(490);

        root.getChildren().addAll(encounterImageView, pokemonImageView);

        Scene battleScene = new Scene(root, 800, 600);

        stage.setScene(battleScene);
        stage.setFullScreen(true);
        stage.setTitle("Pokémon Battle!");
        stage.show();
    }
    private ImageView createPokemonImage(String sprite) {
        ImageView pokemonImageView = new ImageView(new Image(sprite));
        pokemonImageView.setFitWidth(275);
        pokemonImageView.setFitHeight(275);
        return pokemonImageView;
    }
}
