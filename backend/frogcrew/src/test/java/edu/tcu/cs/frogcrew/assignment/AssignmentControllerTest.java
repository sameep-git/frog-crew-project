package edu.tcu.cs.frogcrew.assignment;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import edu.tcu.cs.frogcrew.assignment.dto.CrewAssignmentDto;
import edu.tcu.cs.frogcrew.assignment.dto.CrewListDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;


import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;

@SpringBootTest
@AutoConfigureMockMvc
class AssignmentControllerTest {

    @Autowired
    MockMvc mockMvc; // For simulating HTTP requests

    @MockitoBean
    AssignmentService assignmentService;

    @Value("${api.endpoint.base-url}")
    private String baseUrl;

    @Autowired
    ObjectMapper objectMapper; // For JSON conversion

    CrewListDto crewListDto;
    List<CrewAssignmentDto> crewAssignmentDtos;
    CrewAssignmentDto dto1;
    CrewAssignmentDto dto2;

    @BeforeEach
    void setUp() {

        objectMapper.registerModule(new JavaTimeModule());

        dto1 = new CrewAssignmentDto(1, 101, 1, "Camera 1", "Jane Doe", LocalTime.of(15, 0), "Gate A");
        dto2 = new CrewAssignmentDto(2, 102, 1, "Director", "John Smith", LocalTime.of(14, 30), "Control Room");
        crewAssignmentDtos = List.of(dto1, dto2);

        crewListDto = new CrewListDto(
                1,
                "2025-09-15",
                "2025-09-15", //...
                "Home Stadium",
                "Rival U",
                crewAssignmentDtos
        );
    }

    @Test
    void testGetCrewListForGameSuccess() throws Exception {
        // Given
        given(assignmentService.getCrewListForGame(1)).willReturn(crewListDto);

        // When & Then
        mockMvc.perform(get("/"+baseUrl+"/crewList/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.flag", is(true)))
                .andExpect(jsonPath("$.code", is(HttpStatus.OK.value())))
                .andExpect(jsonPath("$.message", is("Find Success")))
                .andExpect(jsonPath("$.data.gameId", is(1)))
                .andExpect(jsonPath("$.data.opponent", is("Rival U")))
                .andExpect(jsonPath("$.data.crewedMembers", hasSize(2)))
                .andExpect(jsonPath("$.data.crewedMembers[0].position", is("Camera 1")))
                .andExpect(jsonPath("$.data.crewedMembers[1].position", is("Director")));
    }

    @Test
    void testGetCrewListForGameEmpty() throws Exception {
        // Given
        CrewListDto emptyCrewListDto = new CrewListDto(
                1,
                "2025-09-15",
                "2025-09-15",
                "Home Stadium",
                "Rival U",
                new ArrayList<>()
        );
        given(assignmentService.getCrewListForGame(1)).willReturn(emptyCrewListDto);

        // When & Then
        mockMvc.perform(get("/"+baseUrl+"/crewList/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.flag", is(true)))
                .andExpect(jsonPath("$.code", is(HttpStatus.OK.value())))
                .andExpect(jsonPath("$.message", is("Find Success")))
                .andExpect(jsonPath("$.data.gameId", is(1)))
                .andExpect(jsonPath("$.data.crewedMembers", hasSize(0))); // Check for empty array
    }


    @Test
    void testAddCrewScheduleSuccess() throws Exception {
        // Given
        // Input DTOs (without IDs, as they would be for creation)
        CrewAssignmentDto inputDto1 = new CrewAssignmentDto(null, 101, 1, "Camera 1", null, LocalTime.of(15, 0), "Gate A");
        CrewAssignmentDto inputDto2 = new CrewAssignmentDto(null, 102, 1, "Director", null, LocalTime.of(14, 30), "Control Room");
        List<CrewAssignmentDto> inputDtos = List.of(inputDto1, inputDto2);


        List<CrewAssignmentDto> resultDtos = crewAssignmentDtos; // Use the ones with IDs from setUp

        given(assignmentService.saveCrewSchedule(eq(1), any(List.class))).willReturn(resultDtos);

        String jsonRequest = objectMapper.writeValueAsString(inputDtos);

        // When & Then
        mockMvc.perform(post("/"+baseUrl+"/crewSchedule/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag", is(true)))
                .andExpect(jsonPath("$.code", is(HttpStatus.OK.value())))
                .andExpect(jsonPath("$.message", is("Add Success")))
                .andExpect(jsonPath("$.data", hasSize(2)))
                .andExpect(jsonPath("$.data[0].crewedUserId", is(1)))
                .andExpect(jsonPath("$.data[0].position", is("Camera 1")))
                .andExpect(jsonPath("$.data[1].crewedUserId", is(2)))
                .andExpect(jsonPath("$.data[1].position", is("Director")));
    }

    @Test
    void testAddCrewScheduleValidationFailure() throws Exception {
        // Given: Create DTOs that violate validation rules
        CrewAssignmentDto invalidDto1 = new CrewAssignmentDto(null, null, 1, "", null, null, " "); // Null userId, blank position, null reportTime, blank location
        List<CrewAssignmentDto> inputDtos = List.of(invalidDto1);

        String jsonRequest = objectMapper.writeValueAsString(inputDtos);

        // When & Then
        // We expect Spring Boot's default validation handling to return a 400 Bad Request

        mockMvc.perform(post("/"+baseUrl+"/crewSchedule/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }

}