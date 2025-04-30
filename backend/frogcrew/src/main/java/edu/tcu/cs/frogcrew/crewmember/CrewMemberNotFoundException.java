package edu.tcu.cs.frogcrew.crewmember;

public class CrewMemberNotFoundException extends RuntimeException {
    public CrewMemberNotFoundException(int id) {
        super("Could not find crew member with Id " + id + " :(");
    }
}
