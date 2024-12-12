package com.PokemonBattler.api.Fetch;

import static com.PokemonBattler.api.PokemonApiClient.getMoveData;

import java.util.logging.Logger;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import org.hibernate.service.spi.InjectService;

import com.PokemonBattler.Builder.Move;
import com.PokemonBattler.api.Parse.APIParser;
import com.PokemonBattler.api.Parse.MoveParser;
@ApplicationScoped
public class ApiMoveFetcher implements MoveFetcher {
    @Inject
    private APIParser<Move> moveParser;

    @Override
    public Move fetchMove(String moveName) {
        String moveData = getMoveData(moveName);
        if(moveData == null) {
            return null;
        }
        //MoveParser moveParser = new MoveParser();
        return moveParser.parse(moveData);
    }
}
