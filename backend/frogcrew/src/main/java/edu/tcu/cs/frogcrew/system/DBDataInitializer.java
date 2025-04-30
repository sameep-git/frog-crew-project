package edu.tcu.cs.frogcrew.system;

import edu.tcu.cs.frogcrew.crewmember.CrewMember;
import edu.tcu.cs.frogcrew.crewmember.CrewMemberRepository;
import edu.tcu.cs.frogcrew.crewmember.CrewMemberService;
import edu.tcu.cs.frogcrew.game.Game;
import edu.tcu.cs.frogcrew.game.GameRepository;
import edu.tcu.cs.frogcrew.gameschedule.GameSchedule;
import edu.tcu.cs.frogcrew.gameschedule.GameScheduleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;

@Component
public class DBDataInitializer implements CommandLineRunner {

    private final CrewMemberRepository crewMemberRepository;
    private final GameRepository gameRepository;
    private final GameScheduleRepository gameScheduleRepository;

    public DBDataInitializer(CrewMemberRepository crewMemberRepository, GameRepository gameRepository, GameScheduleRepository gameScheduleRepository) {
        this.crewMemberRepository = crewMemberRepository;
        this.gameRepository = gameRepository;
        this.gameScheduleRepository = gameScheduleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        CrewMember c1 = new CrewMember();
        //c1.setId(1);
        c1.setFirstName("John");
        c1.setLastName("Doe");
        c1.setPhoneNumber("123-456-7890");
        c1.setEmail("john.doe@example.com");
        c1.setPassword("temp");
        c1.setRole("Student");
        c1.setQualifiedPosition(Arrays.asList("Producer", "Director"));

        CrewMember c2 = new CrewMember();
        //c2.setId(2);
        c2.setFirstName("Sameep");
        c2.setLastName("Shah");
        c2.setPhoneNumber("123-456-1234");
        c2.setEmail("sameep.shah@tcu.edu");
        c2.setPassword("temp");
        c2.setRole("Student");
        c2.setQualifiedPosition(Arrays.asList("Producer", "Director"));

        CrewMember c3 = new CrewMember();
        //c3.setId(3);
        c3.setFirstName("An");
        c3.setLastName("Cao");
        c3.setPhoneNumber("123-456-5678");
        c3.setEmail("an.cao@tcu.edu");
        c3.setPassword("temp");
        c3.setRole("Staff");
        c3.setQualifiedPosition(Arrays.asList("Producer", "Director","Audio A1"));

        CrewMember c4 = new CrewMember();
        //c4.setId(4);
        c4.setFirstName("Arscene");
        c4.setLastName("Rubayita");
        c4.setPhoneNumber("987-456-1234");
        c4.setEmail("a.rubayita@tcu.edu");
        c4.setPassword("temp");
        c4.setRole("Student");
        c4.setQualifiedPosition(Arrays.asList("Producer", "Technical Director"));

        crewMemberRepository.save(c1);
        crewMemberRepository.save(c2);
        crewMemberRepository.save(c3);
        crewMemberRepository.save(c4);

        GameSchedule gs1 = new GameSchedule();
        gs1.setSport("Football");
        gs1.setSeason("2024-2025");

        GameSchedule gs2 = new GameSchedule();
        gs2.setSport("Basketball");
        gs2.setSeason("2024-2025");

        gameScheduleRepository.save(gs1);
        gameScheduleRepository.save(gs2);

        Game g1 = new Game();
        //g1.setGameId(123);
        g1.setSportType("Football");
        g1.setDate("2025-09-10");
        g1.setTime("13:00:00");
        g1.setVenue("Amon G. Carter Stadium");
        g1.setOpponent("Texas Longhorns");
        g1.setScheduleId(1);
        g1.setCrewPositions("Camera,Audio");
        g1.setFinalized(true);
        g1.setPublished(true);

        Game g2 = new Game();
        //g2.setGameId(100);
        g2.setSportType("Football");
        g2.setDate("2025-09-22");
        g2.setTime("16:00:00");
        g2.setVenue("Amon G. Carter Stadium");
        g2.setScheduleId(1);
        g2.setOpponent("Texas Tech");
        g2.setFinalized(false);
        g2.setPublished(true);
        g2.setCrewPositions("Camera,Audio");

        gameRepository.save(g1);
        gameRepository.save(g2);
    }
}
