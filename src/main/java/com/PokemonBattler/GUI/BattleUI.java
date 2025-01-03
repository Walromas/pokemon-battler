package com.PokemonBattler.GUI;

import java.util.Objects;
import java.util.Set;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Provider;

import com.PokemonBattler.API.PokemonREST.PokemonService;
import com.PokemonBattler.Builder.Move.Move;
import com.PokemonBattler.Builder.Pokemon.Pokemon;
import com.PokemonBattler.Encounters.RandomEncounterGenerator;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
@ApplicationScoped
public class BattleUI {
    private ProgressBar encounterHpBar;
    private ProgressBar playerHpBar;
    private Label encounterName;
    private Label playerName;
    private ImageView encounterImageView;
    private ImageView playerImageView;
    @Inject
    RandomEncounterGenerator randomEncounterGenerator;
    @Inject
    PokemonService pokemonService;
    @Inject
    UIService uiService;
    public void startScene(Stage stage) {
        Pokemon encounter = randomEncounterGenerator.generateRandPokemon();
        Pokemon pokemon = pokemonService.findPokemonById(1L);

        Scene battleScene = new Scene(initalizeUI(), 800, 600);
        stage.setScene(battleScene);
        stage.setFullScreen(true);
        stage.setTitle("Pokémon Battle!");
        stage.show();

        updateBattleUI(pokemon, encounter);
    }
    private Pane initalizeUI(){
        ImageView backgroundView = uiService.createBackground();
        Pane root = new Pane(backgroundView);
        backgroundView.fitWidthProperty().bind(root.widthProperty());
        backgroundView.fitHeightProperty().bind(root.heightProperty());

        this.encounterHpBar = uiService.createHpBar();
        this.encounterName = new Label();
        VBox encounterInfo = new VBox(encounterName, encounterHpBar);
        encounterInfo.setLayoutX(820);
        encounterInfo.setLayoutY(200);
        this.encounterImageView = new ImageView();
        encounterImageView.setX(860);
        encounterImageView.setY(260);

        this.playerHpBar = uiService.createHpBar();
        this.playerName = new Label();
        VBox playerInfo = new VBox(playerName, playerHpBar);
        playerInfo.setLayoutX(290);
        playerInfo.setLayoutY(400);
        this.playerImageView = new ImageView();
        playerImageView.setX(330);
        playerImageView.setY(460);

        root.getChildren().addAll(encounterImageView, playerImageView, encounterInfo, playerInfo);

        return root;
    }


    public void updateBattleUI(Pokemon playerPokemon, Pokemon encounterPokemon) {
        encounterHpBar.setProgress((double) encounterPokemon.getCurrentHP() / (encounterPokemon.getStats().getHp()));
        playerHpBar.setProgress((double) playerPokemon.getCurrentHP() / (playerPokemon.getStats().getHp()));
        encounterName.setText(encounterPokemon.getName());
        playerName.setText(playerPokemon.getName());
        encounterImageView.setImage(new Image(encounterPokemon.getFrontSpriteURL()));
        encounterImageView.setFitWidth(300);
        encounterImageView.setFitHeight(300);
        playerImageView.setImage(new Image(playerPokemon.getBackSpriteURL()));
        playerImageView.setFitWidth(300);
        playerImageView.setFitHeight(300);
    }
}
