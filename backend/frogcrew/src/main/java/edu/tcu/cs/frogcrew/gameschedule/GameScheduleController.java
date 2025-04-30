package edu.tcu.cs.frogcrew.gameschedule;

import edu.tcu.cs.frogcrew.gameschedule.converter.GameScheduleDtoToGameScheduleConverter;
import edu.tcu.cs.frogcrew.gameschedule.dto.GameScheduleDto;
import edu.tcu.cs.frogcrew.gameschedule.dto.GameScheduleResponseDto;
import edu.tcu.cs.frogcrew.system.Result;
import edu.tcu.cs.frogcrew.system.StatusCode;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/frogcrew/api/v1/gameSchedule")
public class GameScheduleController {

    private final GameScheduleService gameScheduleService;
    private final GameScheduleDtoToGameScheduleConverter gameScheduleDtoToGameScheduleConverter;

    public GameScheduleController(GameScheduleService gameScheduleService, GameScheduleDtoToGameScheduleConverter gameScheduleDtoToGameScheduleConverter) {
        this.gameScheduleService = gameScheduleService;
        this.gameScheduleDtoToGameScheduleConverter = gameScheduleDtoToGameScheduleConverter;
    }

    @PostMapping
    public Result addGameSchedule(@Valid @RequestBody GameScheduleDto gameScheduleDto) {
        GameSchedule gameSchedule = this.gameScheduleDtoToGameScheduleConverter.convert(gameScheduleDto);
        GameSchedule savedGameSchedule = this.gameScheduleService.save(gameSchedule);

        GameScheduleResponseDto responseDto = new GameScheduleResponseDto(
                savedGameSchedule.getId(),
                savedGameSchedule.getSport(),
                savedGameSchedule.getSeason()
        );

        return new Result(true, StatusCode.SUCCESS, "Add Success", responseDto);
    }
}
