package edu.tcu.cs.frogcrew.availability.converter;

import edu.tcu.cs.frogcrew.availability.Availability;
import edu.tcu.cs.frogcrew.availability.dto.AvailabilityDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AvailabilityToAvailabilityDTOConverter implements Converter<Availability, AvailabilityDTO> {

    @Override
    public AvailabilityDTO convert(Availability source) {
        return new AvailabilityDTO(
                source.getCrewMember().getId(),
                source.getGame().getGameId(),
                source.getAvailability(),
                source.getComment()
        );
    }
}
