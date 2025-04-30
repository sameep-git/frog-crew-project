package edu.tcu.cs.frogcrew.gameschedule;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.tcu.cs.frogcrew.gameschedule.converter.GameScheduleDtoToGameScheduleConverter;
import edu.tcu.cs.frogcrew.gameschedule.dto.GameScheduleDto;
import edu.tcu.cs.frogcrew.system.StatusCode;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class GameScheduleControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockitoBean
    GameScheduleService gameScheduleService;

    @MockitoBean
    GameScheduleDtoToGameScheduleConverter gameScheduleDtoToGameScheduleConverter;

    GameSchedule gameSchedule;

    @AfterEach
    void tearDown() {
    }

    @BeforeEach
    void setUp() {
        this.gameSchedule = new GameSchedule();
        gameSchedule.setId(1);
        gameSchedule.setSport("Football");
        gameSchedule.setSeason("2024-2025");
    }

    @Test
    void testAddGameScheduleSuccess() throws Exception {
        GameScheduleDto dto = new GameScheduleDto("Football", "2024-2025");

        // Given
        given(this.gameScheduleDtoToGameScheduleConverter.convert(dto)).willReturn(gameSchedule);
        given(this.gameScheduleService.save(gameSchedule)).willReturn(gameSchedule);

        String json = this.objectMapper.writeValueAsString(dto);

        // When and then
        this.mockMvc.perform(post("/frogcrew/api/v1/gameSchedule")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(StatusCode.SUCCESS))
                .andExpect(jsonPath("$.message").value("Add Success"))
                .andExpect(jsonPath("$.data.id").value(1))
                .andExpect(jsonPath("$.data.sport").value("Football"))
                .andExpect(jsonPath("$.data.season").value("2024-2025"));
    }

    @Test
    void testAddGameScheduleMissingSport() throws Exception {
        String json = """
        {
          "season": "2024-2025"
        }
        """;

        this.mockMvc.perform(post("/frogcrew/api/v1/gameSchedule")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(false))
                .andExpect(jsonPath("$.code").value(400))
                .andExpect(jsonPath("$.message").value("Provided arguments are invalid, see data for details."))
                .andExpect(jsonPath("$.data.sport").value("Sport is required"));
    }

    @Test
    void testAddGameScheduleMissingSeason() throws Exception {
        String json = """
        {
          "sport": "Football"
        }
        """;

        this.mockMvc.perform(post("/frogcrew/api/v1/gameSchedule")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(false))
                .andExpect(jsonPath("$.code").value(400))
                .andExpect(jsonPath("$.message").value("Provided arguments are invalid, see data for details."))
                .andExpect(jsonPath("$.data.season").value("Season is required"));
    }
}