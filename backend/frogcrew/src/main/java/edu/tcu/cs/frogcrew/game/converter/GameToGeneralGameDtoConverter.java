package edu.tcu.cs.frogcrew.game.converter;

import edu.tcu.cs.frogcrew.game.Game;
import edu.tcu.cs.frogcrew.game.dto.GeneralGameDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class GameToGeneralGameDtoConverter implements Converter<Game, GeneralGameDto> {
    @Override
    public GeneralGameDto convert(Game game) {
        return new GeneralGameDto(
                game.getGameId(),
                game.getScheduleId(), // scheduleId placeholder
                game.getDate(),
                game.getVenue(),
                game.getOpponent(),
                game.isFinalized()
        );
    }
}