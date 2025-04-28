package edu.tcu.cs.frogcrew.crewmember.converter;

import edu.tcu.cs.frogcrew.crewmember.CrewMember;
import edu.tcu.cs.frogcrew.crewmember.dto.CrewMemberDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CrewMemberDtoToCrewMemberConverter implements Converter<CrewMemberDto, CrewMember> {

    @Override
    public CrewMember convert(CrewMemberDto source) {
        CrewMember crewMember = new CrewMember();
        crewMember.setFirstName(source.firstName());
        crewMember.setLastName(source.lastName());
        crewMember.setEmail(source.email());
        crewMember.setPhoneNumber(source.phoneNumber());
        crewMember.setRole(source.role());
        crewMember.setQualifiedPosition(source.qualifiedPosition());
        return crewMember;
    }
}
