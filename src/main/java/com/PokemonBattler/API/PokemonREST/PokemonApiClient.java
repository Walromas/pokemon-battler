package com.PokemonBattler.API.PokemonREST;

import static jakarta.ws.rs.core.MediaType.TEXT_PLAIN;

import java.util.concurrent.CompletableFuture;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Produces;

import com.PokemonBattler.API.ApiClientBase;
@ApplicationScoped
public class PokemonApiClient extends ApiClientBase {
    @Produces(TEXT_PLAIN)
    public CompletableFuture<String> getPokemonData(String id) {
        return getData("pokemon/" + id);
    }
}
