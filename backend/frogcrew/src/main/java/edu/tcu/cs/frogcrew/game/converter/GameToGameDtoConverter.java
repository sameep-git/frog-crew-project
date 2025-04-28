package edu.tcu.cs.frogcrew.game.converter;

import edu.tcu.cs.frogcrew.game.Game;
import edu.tcu.cs.frogcrew.game.dto.GameDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class GameToGameDtoConverter implements Converter<Game, GameDto> {
    @Override
    public GameDto convert(Game game) {
        return new GameDto(
                game.getGameId(),
                game.getSportType(),
                game.getDate(),
                game.getTime(),
                game.getVenue(),
                game.getOpponent(),
                game.getCrewPositions(),
                game.isFinalized(),
                game.isPublished()
        );
    }
}
