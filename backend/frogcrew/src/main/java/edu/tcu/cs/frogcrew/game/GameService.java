package edu.tcu.cs.frogcrew.game;

import edu.tcu.cs.frogcrew.game.converter.GameDtoToGameConverter;
import edu.tcu.cs.frogcrew.game.converter.GameToGameDtoConverter;
import edu.tcu.cs.frogcrew.game.dto.GameDto;
import edu.tcu.cs.frogcrew.gameschedule.GameSchedule;
import edu.tcu.cs.frogcrew.gameschedule.GameScheduleRepository;
import edu.tcu.cs.frogcrew.system.exception.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class GameService {

    private final GameScheduleRepository gameScheduleRepository;

    private final GameDtoToGameConverter gameDtoToGameConverter;

    private final GameRepository gameRepository;

    public GameService(GameScheduleRepository gameScheduleRepository, GameDtoToGameConverter gameDtoToGameConverter, GameRepository gameRepository) {
        this.gameScheduleRepository = gameScheduleRepository;
        this.gameDtoToGameConverter = gameDtoToGameConverter;
        this.gameRepository = gameRepository;
    }

    public List<Game> findAll() {
        return gameRepository.findAll();
    }

    public Game findById(Integer gameId) {
        return gameRepository.findById(gameId)
                .orElseThrow(() -> new ObjectNotFoundException("game", gameId));
    }

    public Game addGameToSchedule(Integer scheduleId, GameDto gameDto) {
        GameSchedule gameSchedule = gameScheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new ObjectNotFoundException("GameSchedule", scheduleId));

        Game game = gameDtoToGameConverter.convert(gameDto);
        game.setScheduleId(gameSchedule.getId());

        return gameRepository.save(game);
    }

}
