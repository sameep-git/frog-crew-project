package edu.tcu.cs.frogcrew.crewmember;

import com.fasterxml.jackson.databind.ObjectMapper;
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

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class CrewMemberControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    CrewMemberService crewMemberService;

    @Autowired
    ObjectMapper objectMapper;

    List<CrewMember> crewMembers;

    @BeforeEach
    void setup() {
        crewMembers = new ArrayList<>();
        crewMembers = new ArrayList<>();

        CrewMember c1 = new CrewMember();
        c1.setId("1250808601744904191");
        c1.setFirstName("John");
        c1.setLastName("Doe");
        c1.setEmail("john.doe@example.com");
        c1.setPassword("temp");
        c1.setRole("Student");
        c1.setQualifiedPosition("Producer");

        CrewMember c2 = new CrewMember();
        c2.setId("1250808601744904192");
        c2.setFirstName("Sameep");
        c2.setLastName("Shah");
        c2.setEmail("sameep.shah@tcu.edu");
        c2.setPassword("temp");
        c2.setRole("Student");
        c2.setQualifiedPosition("Director");

        crewMembers.add(c1);
        crewMembers.add(c2);
    }

    @AfterEach
    void tearDown() {}

    @Test
    void testFindCrewMemberByIdSuccess() throws Exception {
        // Given
        given(this.crewMemberService.findById("1250808601744904191"))
                .willReturn(this.crewMembers.get(0));

        // When and Then
        this.mockMvc.perform(get("/frogcrew/api/v1/crew/1250808601744904191").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(StatusCode.SUCCESS))
                .andExpect(jsonPath("$.message").value("Find One Success"))
                .andExpect(jsonPath("$.data.id").value("1250808601744904191"))
                .andExpect(jsonPath("$.data.role").value("Student"));
    }

    @Test
    void testFindCrewMemberByIdNotFound() throws Exception {
        // Given
        given(this.crewMemberService.findById("1250808601744904191"))
                .willThrow(new CrewMemberNotFoundException("1250808601744904191"));

        // When and Then
        this.mockMvc.perform(get("/frogcrew/api/v1/crew/1250808601744904191").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(false))
                .andExpect(jsonPath("$.code").value(StatusCode.NOT_FOUND))
                .andExpect(jsonPath("$.message").value("Could not find crew member with Id 1250808601744904191 :("))
                .andExpect(jsonPath("$.data").isEmpty());
    }
}
