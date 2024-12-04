package com.PokemonBattler.api.Fetch;

import static com.PokemonBattler.api.PokemonApiClient.getMoveData;

import com.PokemonBattler.Builder.Move;
import com.PokemonBattler.api.Parse.MoveParser;

public class ApiMoveFetcher implements MoveFetcher {

    @Override
    public Move fetchMove(String moveName) {
        String moveData = getMoveData(moveName);
        if(moveData == null) {
            return null;
        }
        MoveParser moveParser = new MoveParser();
        return moveParser.parse(moveData);
    }
}
