package edu.tcu.cs.frogcrew.crewmember.converter;

import edu.tcu.cs.frogcrew.crewmember.CrewMember;
import edu.tcu.cs.frogcrew.crewmember.dto.CrewMemberDto;
import edu.tcu.cs.frogcrew.crewmember.dto.CrewMemberSimpleDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CrewMemberToCrewMemberSimpleDtoConverter implements Converter<CrewMember, CrewMemberSimpleDto> {

    @Override
    public CrewMemberSimpleDto convert(CrewMember source) {
        String fullName = source.getFirstName() + " " + source.getLastName();
        return new CrewMemberSimpleDto(
                source.getId(),
                fullName,
                source.getEmail(),
                source.getPhoneNumber()
        );
    }
}
