package edu.tcu.cs.frogcrew.crewmember.converter;

import edu.tcu.cs.frogcrew.crewmember.CrewMember;
import edu.tcu.cs.frogcrew.crewmember.dto.CrewMemberDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CrewMemberToCrewMemberDtoConverter implements Converter<CrewMember, CrewMemberDto> {

    @Override
    public CrewMemberDto convert(CrewMember source) {
        return new CrewMemberDto(
                source.getId(),
                source.getFirstName(),
                source.getLastName(),
                source.getEmail(),
                source.getPhoneNumber(),
                source.getRole(),
                source.getQualifiedPosition()
        );
    }
}
