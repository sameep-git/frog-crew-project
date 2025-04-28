package edu.tcu.cs.frogcrew.game.converter;

import edu.tcu.cs.frogcrew.game.Game;
import edu.tcu.cs.frogcrew.game.dto.GameDto;
import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;

@Component
public class GameDtoToGameConverter implements Converter<GameDto, Game> {
    @Override
    public Game convert(GameDto dto) {
        Game game = new Game();
        game.setGameId(dto.gameId());
        game.setSportType(dto.sportType());
        game.setDate(dto.date());
        game.setTime(dto.time());
        game.setVenue(dto.venue());
        game.setOpponent(dto.opponent());
        game.setCrewPositions(dto.crewPositions());
        game.setFinalized(dto.finalized());
        game.setPublished(dto.published());
        return game;
    }
}
