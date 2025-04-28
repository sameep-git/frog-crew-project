package edu.tcu.cs.frogcrew.game.dto;

import jakarta.validation.constraints.NotEmpty;

public record GameDto(
        Integer gameId,
        @NotEmpty(message = "Sport type is required.")
        String sportType,
        @NotEmpty(message = "Date is required.")
        String date,
        @NotEmpty(message = "Time is required.")
        String time,
        @NotEmpty(message = "Venue is required.")
        String venue,
        String opponent,
        @NotEmpty(message = "Crew positions are required.")
        String crewPositions,
        boolean finalized,
        boolean published
){
}
