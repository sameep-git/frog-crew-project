package edu.tcu.cs.frogcrew.availability;

import edu.tcu.cs.frogcrew.crewmember.CrewMember;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.aspectj.bridge.IMessage;

import java.io.Serializable;

@Entity
public class Availability implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(optional = false)
    @NotNull(message = "crewMemberId is required.")
    @JoinColumn(name = "crew_member_id")
    private CrewMember crewMember;
}
