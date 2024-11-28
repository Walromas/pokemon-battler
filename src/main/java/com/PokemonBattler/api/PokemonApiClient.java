package com.PokemonBattler.api;

import static jakarta.ws.rs.core.MediaType.TEXT_PLAIN;

import jakarta.ws.rs.Produces;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;

public class PokemonApiClient {

    private static final String API_URL = "https://pokeapi.co/api/v2/";
    @Produces(TEXT_PLAIN)
    public static String getData(String endPoint){
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(API_URL + endPoint);
        Response response = target.request().get();
        if (response.getStatus() == 200) {
            return response.readEntity(String.class);
        } else {
            return "Error: " + response.getStatus();
        }
    }
    @Produces(TEXT_PLAIN)
    public static String getPokemonData(String pokemonName) {
        return getData("pokemon/" + pokemonName);

    }
    @Produces(TEXT_PLAIN)
    public static String getMoveData(String moveName) {
        return getData("move/" + moveName);
    }

}
