package com.PokemonBattler.API.MoveREST;

import java.util.concurrent.CompletableFuture;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import com.PokemonBattler.API.Parse.MoveParser;
import com.PokemonBattler.Builder.Move.Move;

@ApplicationScoped
public class MoveService {
    @Inject
    MoveParser moveParser;
    @Inject
    MoveApiClient moveApiClient;

    public CompletableFuture<Move> createMove(String moveName) {
        return moveApiClient.getMoveData(moveName)
                .thenApply(jsonresponse -> moveParser.parse(jsonresponse));
    }
}
