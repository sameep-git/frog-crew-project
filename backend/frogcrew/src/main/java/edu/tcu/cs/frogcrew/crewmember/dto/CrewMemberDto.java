package edu.tcu.cs.frogcrew.crewmember.dto;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record CrewMemberDto (
        Long userId,

        @NotEmpty(message = "First Name is required.")
     String firstName,

        @NotEmpty(message = "Last Name is required.")
     String lastName,

        @NotEmpty(message = "Email is required.")
     String email,

        @NotEmpty(message = "Phone Number is required.")
     String phoneNumber,

        @NotEmpty(message = "Role is required.")
     String role,

        @NotEmpty(message = "Qualified Position is required.")
     List<String> qualifiedPosition
) {}
