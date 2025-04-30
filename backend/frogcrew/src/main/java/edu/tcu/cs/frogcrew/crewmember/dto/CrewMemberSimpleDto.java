package edu.tcu.cs.frogcrew.crewmember.dto;

public record CrewMemberSimpleDto(
        Long userId,
        String fullName,
        String email,
        String phoneNumber
) {}