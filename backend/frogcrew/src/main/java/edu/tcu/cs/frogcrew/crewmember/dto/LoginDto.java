package edu.tcu.cs.frogcrew.crewmember.dto;

public record LoginDto (
        Integer userId,
        String role,
        String token
){
}
