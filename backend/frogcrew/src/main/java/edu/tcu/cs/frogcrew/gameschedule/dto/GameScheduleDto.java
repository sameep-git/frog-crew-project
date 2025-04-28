package edu.tcu.cs.frogcrew.gameschedule.dto;

import jakarta.validation.constraints.NotEmpty;

public record GameScheduleDto(
        @NotEmpty(message = "Sport is required")
        String sport,

        @NotEmpty(message = "Season is required")
        String season
) {
}
