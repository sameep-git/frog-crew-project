package edu.tcu.cs.frogcrew.crewmember.dto;

public record CrewMemberSimpleDto(
        String userId,
        String fullName,
        String email,
        String phoneNumber
) {}