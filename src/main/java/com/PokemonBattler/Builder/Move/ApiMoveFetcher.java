package com.PokemonBattler.Builder.Move;

import static com.PokemonBattler.api.PokemonApiClient.getMoveData;

import com.PokemonBattler.api.Parse.MoveParser;

public class ApiMoveFetcher implements MoveFetcher{
    private final MoveParser moveParser;

    public ApiMoveFetcher() {
        this.moveParser = new MoveParser();
    }

    @Override
    public Move fetchMove(String moveName) {
        String moveData = getMoveData(moveName);
        if(moveData == null) {
            return null;
        }
        return moveParser.parse(moveData);
    }
}
