package edu.tcu.cs.frogcrew.assignment;

import edu.tcu.cs.frogcrew.assignment.converter.CrewAssignmentDtoToCrewAssignmentConverter;
import edu.tcu.cs.frogcrew.assignment.converter.CrewAssignmentToCrewAssignmentDtoConverter;
import edu.tcu.cs.frogcrew.assignment.dto.CrewAssignmentDto;
import edu.tcu.cs.frogcrew.assignment.dto.CrewListDto;
import edu.tcu.cs.frogcrew.game.Game;
import edu.tcu.cs.frogcrew.game.GameRepository;
import edu.tcu.cs.frogcrew.system.exception.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class AssignmentService {
    private final AssignmentRepository assignmentRepository;
    private final CrewAssignmentToCrewAssignmentDtoConverter crewAssignmentToCrewAssignmentDtoConverter;
    private final CrewAssignmentDtoToCrewAssignmentConverter crewAssignmentDtoToCrewAssignmentConverter;
    private final GameRepository gameRepository;

    public AssignmentService(AssignmentRepository assignmentRepository, CrewAssignmentToCrewAssignmentDtoConverter crewAssignmentToCrewAssignmentDtoConverter, CrewAssignmentDtoToCrewAssignmentConverter crewAssignmentDtoToCrewAssignmentConverter, GameRepository gameRepository) {
        this.assignmentRepository = assignmentRepository;
        this.crewAssignmentToCrewAssignmentDtoConverter = crewAssignmentToCrewAssignmentDtoConverter;
        this.crewAssignmentDtoToCrewAssignmentConverter = crewAssignmentDtoToCrewAssignmentConverter;
        this.gameRepository = gameRepository;
    }

    public CrewListDto getCrewListForGame(Integer gameId) {
        Game game = gameRepository.findById(Math.toIntExact(gameId))
                .orElseThrow(() -> new ObjectNotFoundException("Game", String.valueOf(gameId)));

        List<Assignment> assignments = assignmentRepository.findByGame_GameId(gameId);

        List<CrewAssignmentDto> crewedMembers = assignments.stream()
                .map(crewAssignmentToCrewAssignmentDtoConverter::convert)
                .toList();

        return new CrewListDto(
                game.getGameId(),
                game.getDate().toString(), //based on docs , i have no clue where game time comes from .
                game.getDate().toString(),
                game.getVenue(),
                game.getOpponent(),
                crewedMembers
        );
    }

    public List<CrewAssignmentDto> saveCrewSchedule(Integer gameId, List<CrewAssignmentDto> assignments) {
        Game game = gameRepository.findById(Math.toIntExact(gameId))
                .orElseThrow(() -> new ObjectNotFoundException("Game", String.valueOf(gameId)));

        return assignments.stream()
                .map(dto -> {
                    Assignment assignment = crewAssignmentDtoToCrewAssignmentConverter.convert(dto);
                    assignment.setGame(game);
                    return assignmentRepository.save(assignment);
                })
                .map(crewAssignmentToCrewAssignmentDtoConverter::convert)
                .toList();
    }

}
