package edu.tcu.cs.frogcrew.assignment.converter;

import edu.tcu.cs.frogcrew.assignment.Assignment;
import edu.tcu.cs.frogcrew.assignment.dto.CrewAssignmentDto;
import edu.tcu.cs.frogcrew.crewmember.CrewMember;
import edu.tcu.cs.frogcrew.crewmember.CrewMemberRepository;
import edu.tcu.cs.frogcrew.game.Game;
import edu.tcu.cs.frogcrew.game.GameRepository;
import edu.tcu.cs.frogcrew.system.exception.ObjectNotFoundException;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CrewAssignmentDtoToCrewAssignmentConverter implements Converter<CrewAssignmentDto, Assignment> {

    private final CrewMemberRepository crewMemberRepository;
    private final GameRepository gameRepository;

    public CrewAssignmentDtoToCrewAssignmentConverter(CrewMemberRepository crewMemberRepository,
                                                      GameRepository gameRepository) {
        this.crewMemberRepository = crewMemberRepository;
        this.gameRepository = gameRepository;
    }

    @Override
    public Assignment convert(CrewAssignmentDto source) {
        Assignment assignment = new Assignment();

        CrewMember crewMember = crewMemberRepository.findById(String.valueOf(source.userId()))
                .orElseThrow(() -> new ObjectNotFoundException("CrewMember", String.valueOf(source.userId())));

        Game game = gameRepository.findById(Math.toIntExact(source.gameId()))
                .orElseThrow(() -> new ObjectNotFoundException("Game", String.valueOf(source.gameId())));

        assignment.setId(source.crewedUserId());
        assignment.setCrewMember(crewMember);
        assignment.setGame(game);
        assignment.setPosition(source.position());
        assignment.setReportTime(source.reportTime());
        assignment.setReportLocation(source.reportLocation());

        return assignment;
    }
}