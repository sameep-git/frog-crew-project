package edu.tcu.cs.frogcrew.crewmember.converter;

import edu.tcu.cs.frogcrew.crewmember.CrewMember;
import edu.tcu.cs.frogcrew.crewmember.dto.LoginDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CrewMemberToLoginDtoConverter implements Converter<CrewMember, LoginDto> {
    @Override
    public LoginDto convert(CrewMember crewMember) {
        return new LoginDto(crewMember.getId(), crewMember.getRole(), null);
    }
}
