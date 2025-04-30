package edu.tcu.cs.frogcrew.availability;

import edu.tcu.cs.frogcrew.crewmember.CrewMember;
import edu.tcu.cs.frogcrew.game.Game;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@AutoConfigureMockMvc
public class AvailabilityServiceTest {

    @Mock
    AvailabilityRepository availabilityRepository;

    @InjectMocks
    AvailabilityService availabilityService;

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void testAddAvailability() {
        // Given

        Game game = new Game();
        game.setGameId(1);

        CrewMember crewMember = new CrewMember();
        crewMember.setId(1L);

        Availability availability = new Availability();
        availability.setGame(game);
        availability.setCrewMember(crewMember);
        availability.setAvailability(true);
        availability.setComment("I will be a little late!");

        given(this.availabilityRepository.save(availability)).willReturn(availability);

        // When
        Availability newAvailability = availabilityService.addAvailability(availability);

        // Then
        assertThat(newAvailability.getCrewMember().getId()).isEqualTo(1);
        assertThat(newAvailability.getGame().getGameId()).isEqualTo(1);
        assertThat(newAvailability.getAvailability()).isTrue();
        assertThat(newAvailability.getComment()).isEqualTo("I will be a little late!");
        verify(this.availabilityRepository, times(1)).save(availability);

    }
}
