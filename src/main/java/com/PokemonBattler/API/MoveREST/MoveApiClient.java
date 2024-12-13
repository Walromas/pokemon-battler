package com.PokemonBattler.API.MoveREST;

import static jakarta.ws.rs.core.MediaType.TEXT_PLAIN;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Produces;

import com.PokemonBattler.API.ApiClientBase;
@ApplicationScoped
public class MoveApiClient extends ApiClientBase {
    @Produces(TEXT_PLAIN)
    public String getMoveData(String moveName) {
        return getData("move/" + moveName);
    }
}
