package edu.tcu.cs.frogcrew.availability;

import edu.tcu.cs.frogcrew.crewmember.CrewMember;
import edu.tcu.cs.frogcrew.game.Game;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.aspectj.bridge.IMessage;

import java.io.Serializable;

@Entity
public class Availability implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @NotNull(message = "crewMemberId is required.")
    private CrewMember crewMember;

    @ManyToOne
    private Game game;

    private Boolean availability;

    private String comment;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CrewMember getCrewMember() {
        return crewMember;
    }

    public void setCrewMember(CrewMember crewMember) {
        this.crewMember = crewMember;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Boolean getAvailability() {
        return availability;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
