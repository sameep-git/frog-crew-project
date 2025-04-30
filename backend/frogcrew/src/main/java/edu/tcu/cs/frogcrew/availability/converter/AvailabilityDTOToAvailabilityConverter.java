package edu.tcu.cs.frogcrew.availability.converter;

import edu.tcu.cs.frogcrew.availability.Availability;
import edu.tcu.cs.frogcrew.availability.dto.AvailabilityDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AvailabilityDTOToAvailabilityConverter implements Converter<AvailabilityDTO, Availability> {
    @Override
    public Availability convert(AvailabilityDTO source) {
        Availability memberAvailability = new Availability();
        memberAvailability.setAvailability(source.availability());
        memberAvailability.setComment(source.comment());
        return memberAvailability;
    }
}
