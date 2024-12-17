package com.PokemonBattler.GUI;

import java.util.Objects;
import java.util.Set;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import com.PokemonBattler.API.PokemonREST.PokemonService;
import com.PokemonBattler.Builder.Move.Move;
import com.PokemonBattler.Builder.Pokemon.Pokemon;
import com.PokemonBattler.Encounters.RandomEncounterGenerator;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
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
        encounterImageView.setX(860);
        encounterImageView.setY(260);

        Pokemon pokemon = pokemonService.findPokemonById(1L);
        String pokemonSprite = pokemon.getBackSpriteURL();
        ImageView pokemonImageView = createPokemonImage(pokemonSprite);
        pokemonImageView.setX(330);
        pokemonImageView.setY(460);
        Set<Move> currentMoves = pokemon.getCurrentMoves();

        GridPane moveGrid = new GridPane();
        moveGrid.setHgap(10);
        moveGrid.setVgap(10);
        moveGrid.setLayoutX(1050);
        moveGrid.setLayoutY(600);


        int row = 0;
        int col = 0;

        for (Move move:currentMoves){
            Button button = createMoveButtons(move);

            moveGrid.add(button, col, row);

            col++;
            if(col > 1) {
                col = 0;
                row++;
            }
        }


        root.getChildren().addAll(encounterImageView, pokemonImageView, moveGrid);

        Scene battleScene = new Scene(root, 800, 600);

        stage.setScene(battleScene);
        stage.setFullScreen(true);
        stage.setTitle("Pokémon Battle!");
        stage.show();
    }
    private ImageView createPokemonImage(String sprite) {
        ImageView pokemonImageView = new ImageView(new Image(sprite));
        pokemonImageView.setFitWidth(300);
        pokemonImageView.setFitHeight(300);
        return pokemonImageView;
    }
    private Button createMoveButtons(Move move) {
        String moveName = move.getName();
        Label moveLabel = new Label(moveName);
        Button button = new Button();
        button.setGraphic(moveLabel);
        button.setPrefHeight(100);
        button.setPrefWidth(200);
        return button;
    }
}
