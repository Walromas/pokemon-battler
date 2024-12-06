package com.PokemonBattler.GUI;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import com.PokemonBattler.Builder.Pokemon.Pokemon;
import com.PokemonBattler.Encounters.StarterGenerator;
import com.PokemonBattler.api.Parse.PokemonParser;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class PokemonStarterGUI extends Application {

    @Override
    public void start(Stage stage) throws MalformedURLException {

        PokemonParser pokemonParser = new PokemonParser();
        StarterGenerator starterGenerator = new StarterGenerator();
        List<Pokemon> starterList = starterGenerator.generateStarters(pokemonParser);

        Pokemon starter1 = starterList.get(0);
        Pokemon starter2 = starterList.get(1);
        Pokemon starter3 = starterList.get(2);

        String starter1Url = starter1.getSpriteURL();
        String starter2Url = starter2.getSpriteURL();
        String starter3Url = starter3.getSpriteURL();

        String starter1name = starter1.getName();
        String starter2name = starter2.getName();
        String starter3name = starter3.getName();


        HBox root = new HBox(20);
        root.setStyle("-fx-background-color: black;");
        root.setAlignment(Pos.CENTER);
        URL url = new URL("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/");
        DisableSSLVerification.openConnectionWithCustomSSL(url);

        root.getChildren().addAll(
                createPokemonPanel(starter1name, starter1Url),
                createPokemonPanel(starter2name, starter2Url),
                createPokemonPanel(starter3name, starter3Url)
        );

        Scene scene = new Scene(root);
        stage.setTitle("Pokémon Starters");
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }

    private VBox createPokemonPanel(String name, String imageUrl) {
        ImageView imageView = new ImageView(new Image(imageUrl));
        imageView.setFitWidth(150);
        imageView.setFitHeight(150);

        Label nameLabel = new Label(name);
        nameLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: white;");


        VBox panel = new VBox(15, imageView, nameLabel);
        panel.setStyle("-fx-background-color: grey; -fx-padding: 20; -fx-border-radius: 10; -fx-background-radius: 10;");
        panel.setAlignment(Pos.CENTER);
        panel.setPrefWidth(300);

        return panel;
    }

    public static void main(String[] args) {
        launch();
    }
}

