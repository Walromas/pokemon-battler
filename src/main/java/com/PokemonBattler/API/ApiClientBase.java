package com.PokemonBattler.API;

import static jakarta.ws.rs.core.MediaType.TEXT_PLAIN;

import java.util.concurrent.CompletableFuture;

import jakarta.ws.rs.Produces;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;

public class ApiClientBase {
    private static final Client client = ClientBuilder.newClient();
    private static final String API_URL = "https://pokeapi.co/api/v2/";

    @Produces(TEXT_PLAIN)
    public static CompletableFuture<String> getData(String endPoint){
        return CompletableFuture.supplyAsync(() -> {
            WebTarget target = client.target(API_URL + endPoint);
            Response response = target.request().get();
            if (response.getStatus() == 200) {
                return response.readEntity(String.class);
            } else {
                return "Error: " + response.getStatus();
            }
        });
    }
}
