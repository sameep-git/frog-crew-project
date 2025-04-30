package edu.tcu.cs.frogcrew.availability;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.tcu.cs.frogcrew.availability.converter.AvailabilityToAvailabilityDTOConverter;
import edu.tcu.cs.frogcrew.availability.dto.AvailabilityDTO;
import edu.tcu.cs.frogcrew.crewmember.CrewMember;
import edu.tcu.cs.frogcrew.game.Game;
import edu.tcu.cs.frogcrew.system.StatusCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class AvailabilityControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockitoBean
    AvailabilityService availabilityService;

    @MockitoBean
    AvailabilityToAvailabilityDTOConverter availabilityToAvailabilityDTOConverter;

    ArrayList<Availability> availabilities;

    @BeforeEach
    public void setUp() {}

    @Test
    public void testAddAvailability() throws Exception {
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

        AvailabilityDTO availabilityDTO = new AvailabilityDTO(1L, 1, Boolean.TRUE, "I will be a little late!");
        String json = objectMapper.writeValueAsString(availabilityDTO);

        given(availabilityService.addAvailability(Mockito.any(Availability.class))).willReturn(availability);
        given(this.availabilityToAvailabilityDTOConverter.convert(availability)).willReturn(availabilityDTO);

        // When and Then

        this.mockMvc.perform(
                post("/frogcrew/api/v1/availability")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(StatusCode.SUCCESS))
                .andExpect(jsonPath("$.message").value("New availability added"))
                .andExpect(jsonPath("$.data").isNotEmpty());
    }
}
