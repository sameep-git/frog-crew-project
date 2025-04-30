package edu.tcu.cs.frogcrew.assignment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;

public record CrewAssignmentDto(
        Integer crewedUserId,
        @NotNull(message = "UserId is required")
        Integer userId,
        @NotNull(message = "GameId is required")
        Integer gameId,
        @NotBlank(message = "Position is required")
        String position,
        String fullName,
        @NotNull(message = "Report time is required")
        LocalTime reportTime,
        @NotBlank(message = "Report location is required")
        String reportLocation
) {
}

