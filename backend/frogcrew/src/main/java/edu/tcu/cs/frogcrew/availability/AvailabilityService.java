package edu.tcu.cs.frogcrew.availability;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AvailabilityService {
    private final AvailabilityRepository availabilityRepository;

    public AvailabilityService(AvailabilityRepository availabilityRepository) {
        this.availabilityRepository = availabilityRepository;
    }

    public Availability addAvailability(Availability availability) {
        return this.availabilityRepository.save(availability);
    }
}
