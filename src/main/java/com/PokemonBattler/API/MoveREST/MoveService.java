package com.PokemonBattler.API.MoveREST;

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

    public Move createMove(String moveName) {
        String jsonResponse = moveApiClient.getMoveData(moveName);
        return moveParser.parse(jsonResponse);
    }
}
