package com.PokemonBattler.GUI;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import com.PokemonBattler.Builder.Pokemon.Pokemon;
import com.PokemonBattler.Encounters.StarterGenerator;
import com.PokemonBattler.api.Parse.PokemonParser;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class PokemonStarterGUI extends Application {

    private StarterGenerator starterGenerator;

    @Override
    public void init() {
        Weld weld = new Weld();
        WeldContainer weldContainer = weld.initialize();
        this.starterGenerator = weldContainer.select(StarterGenerator.class).get();
    }

    @Override
    public void start(Stage stage) throws MalformedURLException {
        List<Pokemon> starterList = starterGenerator.generateStarters();

        Pokemon starter1 = starterList.get(0);
        Pokemon starter2 = starterList.get(1);
        Pokemon starter3 = starterList.get(2);

        String starter1Url = starter1.getFrontSpriteURL();
        String starter2Url = starter2.getFrontSpriteURL();
        String starter3Url = starter3.getFrontSpriteURL();

        String starter1name = starter1.getName();
        String starter2name = starter2.getName();
        String starter3name = starter3.getName();


        HBox root = new HBox(20);
        root.setStyle("-fx-background-color: black;");
        root.setAlignment(Pos.CENTER);
        URL url = new URL("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/");
        DisableSSLVerification.openConnectionWithCustomSSL(url);

        Button buttonStarter1 = createPokemonButton(starter1name, starter1Url);
        Button buttonStarter2 = createPokemonButton(starter2name, starter2Url);
        Button buttonStarter3 = createPokemonButton(starter3name, starter3Url);

        root.getChildren().addAll(
                buttonStarter1,
                buttonStarter2,
                buttonStarter3
        );

        buttonStarter1.setOnAction(event -> starterGenerator.pickStarter(starter1, stage));
        buttonStarter2.setOnAction(event -> starterGenerator.pickStarter(starter2, stage));
        buttonStarter3.setOnAction(event -> starterGenerator.pickStarter(starter3,stage));

        Scene scene = new Scene(root);
        stage.setTitle("Pokémon Starters");
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();

    }

    private Button createPokemonButton(String name, String imageUrl) {
        ImageView imageView = new ImageView(new Image(imageUrl));
        imageView.setFitWidth(200);
        imageView.setFitHeight(200);


        Label nameLabel = new Label(name);
        nameLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: white;");


        VBox panel = new VBox(15, imageView, nameLabel);
        panel.setStyle("-fx-background-color: Transparent; -fx-padding: 20; -fx-border-radius: 10; -fx-background-radius: 10;");
        panel.setAlignment(Pos.CENTER);
        panel.setPrefWidth(300);

        Button button = new Button();
        button.setGraphic(panel);
        button.setStyle("-fx-background-color: Transparent");


        return button;
    }

    public static void main(String[] args) {
        launch();
    }
}

