package edu.tcu.cs.frogcrew.crewmember.dto;

public record CrewMemberSimpleDto(
        Integer userId,
        String fullName,
        String email,
        String phoneNumber
) {}