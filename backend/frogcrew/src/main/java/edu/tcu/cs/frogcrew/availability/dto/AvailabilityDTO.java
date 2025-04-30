package edu.tcu.cs.frogcrew.availability.dto;

import jakarta.validation.constraints.NotNull;

public record AvailabilityDTO(
        Integer crewMemberId,

        @NotNull(message = "Game id is required")
        Integer gameId,

        @NotNull(message = "Availability is required")
        Boolean availability,

        String comment
) {}
