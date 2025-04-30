package edu.tcu.cs.frogcrew.crewmember.dto;

public record LoginDto (
        Long userId,
        String role,
        String token
){
}
