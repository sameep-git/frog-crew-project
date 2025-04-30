package edu.tcu.cs.frogcrew.crewmember;

public class CrewMemberNotFoundException extends RuntimeException {
    public CrewMemberNotFoundException(long id) {
        super("Could not find crew member with Id " + id + " :(");
    }
}
