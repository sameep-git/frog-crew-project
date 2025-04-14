package edu.tcu.cs.frogcrew.system;

import edu.tcu.cs.frogcrew.crewmember.CrewMember;
import edu.tcu.cs.frogcrew.crewmember.CrewMemberRepository;
import edu.tcu.cs.frogcrew.crewmember.CrewMemberService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBDataInitializer implements CommandLineRunner {

    private final CrewMemberRepository crewMemberRepository;

    public DBDataInitializer(CrewMemberRepository crewMemberRepository) {
        this.crewMemberRepository = crewMemberRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        CrewMember c1 = new CrewMember();
        c1.setId("1250808601744904191");
        c1.setFirstName("John");
        c1.setLastName("Doe");
        c1.setPhoneNumber("123-456-7890");
        c1.setEmail("john.doe@example.com");
        c1.setPassword("temp");
        c1.setRole("Student");
        c1.setQualifiedPosition("Producer");

        CrewMember c2 = new CrewMember();
        c2.setId("1250808601744904192");
        c2.setFirstName("Sameep");
        c2.setLastName("Shah");
        c2.setPhoneNumber("123-456-1234");
        c2.setEmail("sameep.shah@tcu.edu");
        c2.setPassword("temp");
        c2.setRole("Student");
        c2.setQualifiedPosition("Director");

        CrewMember c3 = new CrewMember();
        c3.setId("1250808601744904193");
        c3.setFirstName("An");
        c3.setLastName("Cao");
        c3.setPhoneNumber("123-456-5678");
        c3.setEmail("an.cao@tcu.edu");
        c3.setPassword("temp");
        c3.setRole("Staff");
        c3.setQualifiedPosition("Audio A1");

        CrewMember c4 = new CrewMember();
        c4.setId("1250808601744904194");
        c4.setFirstName("Arscene");
        c4.setLastName("Rubayita");
        c4.setPhoneNumber("987-456-1234");
        c4.setEmail("a.rubayita@tcu.edu");
        c4.setPassword("temp");
        c4.setRole("Student");
        c4.setQualifiedPosition("Technical Director");

        crewMemberRepository.save(c1);
        crewMemberRepository.save(c2);
        crewMemberRepository.save(c3);
        crewMemberRepository.save(c4);
    }
}
