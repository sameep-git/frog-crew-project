package edu.tcu.cs.frogcrew.game.dto;

// used for /gameSchedule/games endpoint
public record GeneralGameDto(
        Integer gameId,
        Integer scheduleId,
        String gameDate,
        String venue,
        String opponent,
        boolean isFinalized
) {}