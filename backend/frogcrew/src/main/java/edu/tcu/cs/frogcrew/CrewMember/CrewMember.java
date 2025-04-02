package edu.tcu.cs.frogcrew.CrewMember;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;

@Entity
public class CrewMember implements Serializable {

    @Id
    private String id;

    private String firstName;
    private String lastName;
    private String email;

    @Pattern(regexp = "^[0-9]{3}-[0-9]{3}-[0-9]{4}", message = "A phone number must be in the form XXX-XXX-XXXX")
    private String phoneNumber;
    private String password;
    private String role;
    private String qualifiedPosition;
}
