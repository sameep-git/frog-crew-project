package edu.tcu.cs.frogcrew.assignment.converter;

import edu.tcu.cs.frogcrew.assignment.Assignment;
import edu.tcu.cs.frogcrew.assignment.dto.CrewAssignmentDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CrewAssignmentToCrewAssignmentDtoConverter implements Converter<Assignment, CrewAssignmentDto> {

    @Override
    public CrewAssignmentDto convert(Assignment source) {
        return new CrewAssignmentDto(
                source.getId(),
                source.getCrewMember().getId(),
                source.getGame().getGameId(),
                source.getPosition(),
                source.getCrewMember().getFirstName() + " " + source.getCrewMember().getLastName(),
                source.getReportTime(),
                source.getReportLocation()
        );
    }
}
