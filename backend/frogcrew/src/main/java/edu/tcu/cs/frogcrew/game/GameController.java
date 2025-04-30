package edu.tcu.cs.frogcrew.game;

import edu.tcu.cs.frogcrew.game.converter.GameToGameDtoConverter;
import edu.tcu.cs.frogcrew.game.converter.GameToGeneralGameDtoConverter;
import edu.tcu.cs.frogcrew.game.dto.GameDto;
import edu.tcu.cs.frogcrew.game.dto.GeneralGameDto;
import edu.tcu.cs.frogcrew.system.Result;
import edu.tcu.cs.frogcrew.system.StatusCode;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/frogcrew/api/v1/gameSchedule")
public class GameController {

    private final GameService gameService;
    private final GameToGameDtoConverter gameToGameDtoConverter;
    private final GameToGeneralGameDtoConverter gameToGeneralGameDtoConverter;

    public GameController(GameService gameService, GameToGameDtoConverter gameToGameDtoConverter, GameToGeneralGameDtoConverter gameToGeneralGameDtoConverter) {
        this.gameService = gameService;
        this.gameToGameDtoConverter = gameToGameDtoConverter;
        this.gameToGeneralGameDtoConverter = gameToGeneralGameDtoConverter;
    }

    @GetMapping("/games")
    public Result getGeneralGameSchedule() {
        List<Game> foundGames = gameService.findAll();
        List<GeneralGameDto> gameDtos = foundGames.stream()
                .map(gameToGeneralGameDtoConverter::convert)
                .toList();

        if (gameDtos.isEmpty()) {
            return new Result(true, StatusCode.NO_CONTENT, "No upcoming games available right now.", gameDtos);
        }

        return new Result(true, StatusCode.SUCCESS, "Find Success", gameDtos);
    }

    @GetMapping("/game/{gameId}")
    public Result getGameById(@PathVariable Integer gameId) {
        Game foundGame =this.gameService.findById(gameId);
        GeneralGameDto dto = gameToGeneralGameDtoConverter.convert(foundGame);
        return new Result(true, StatusCode.SUCCESS, "Find Success", dto);
    }

    @PostMapping("/{scheduleId}/games")
    public Result addGameToSchedule(@PathVariable Integer scheduleId,
                                    @RequestBody @Valid GameDto gameDto) {
        Game newGame = this.gameService.addGameToSchedule(scheduleId, gameDto);
        GameDto savedDto = this.gameToGameDtoConverter.convert(newGame);
        return new Result(true, StatusCode.SUCCESS, "Add Success", savedDto);
    }
}
