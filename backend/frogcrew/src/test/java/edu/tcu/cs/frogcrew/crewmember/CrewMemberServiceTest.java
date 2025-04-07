package edu.tcu.cs.frogcrew.crewmember;

import edu.tcu.cs.frogcrew.crewmember.utils.IdWorker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CrewMemberServiceTest {

    @Mock
    CrewMemberRepository crewMemberRepository;

    @Mock
    IdWorker idWorker;

    @InjectMocks
    CrewMemberService crewMemberService;

    List<CrewMember> crewMembers;

    @BeforeEach
    void setUp() {
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
    void tearDown() {

    }

    @Test
    void testFindCrewMemberByIdSuccess() {

        // Given
        CrewMember c = new CrewMember();
        c.setId("1250808601744904191");
        c.setFirstName("John");
        c.setLastName("Doe");
        c.setEmail("john.doe@example.com");
        c.setPassword("temp");
        c.setRole("Student");
        c.setQualifiedPosition("Producer");

        given(crewMemberRepository.findById("1250808601744904191")).willReturn(Optional.of(c));

        // When
        CrewMember returnedCrewMember = crewMemberService.findById("1250808601744904191");

        // Then
        assertThat(returnedCrewMember.getId()).isEqualTo(c.getId());
        assertThat(returnedCrewMember.getFirstName()).isEqualTo(c.getFirstName());
        assertThat(returnedCrewMember.getLastName()).isEqualTo(c.getLastName());
        assertThat(returnedCrewMember.getEmail()).isEqualTo(c.getEmail());
        assertThat(returnedCrewMember.getRole()).isEqualTo(c.getRole());
        verify(crewMemberRepository, times(1)).findById("1250808601744904191");
    }

    @Test
    void testFindCrewMemberByIdNotFound() {
        // Given
        given(crewMemberRepository.findById("1250808601744904192")).willReturn(Optional.empty());

        // When
        Throwable thrown = catchThrowable(() -> {
            CrewMember crewMember = crewMemberService.findById("1250808601744904192");
        });

        // Then
        assertThat(thrown)
                .isInstanceOf(CrewMemberNotFoundException.class)
                .hasMessage("Could not find crew member with Id 1250808601744904192 :(");
        verify(crewMemberRepository, times(1)).findById("1250808601744904192");
    }

    @Test
    void testFindAllCrewMemberSuccess() {
        // Given
        given(crewMemberRepository.findAll()).willReturn(this.crewMembers);

        // When
        List<CrewMember> actualCrew = crewMemberService.findAll();

        // Then
        assertThat(actualCrew.size()).isEqualTo(this.crewMembers.size());
        verify(crewMemberRepository, times(1)).findAll();
    }

    @Test
    void testSaveCrewMemberSuccess() {
        // Given
        CrewMember c = new CrewMember();
        c.setFirstName("John");
        c.setLastName("Doe");
        c.setEmail("john.doe@example.com");
        c.setPassword("temp");
        c.setRole("Student");
        c.setQualifiedPosition("Producer");

        given(idWorker.nextId()).willReturn(12345678L);
        given(crewMemberRepository.save(c)).willReturn(c);

        // When
        CrewMember savedCrewMember = crewMemberService.save(c);

        // Then
        assertThat(savedCrewMember.getId()).isEqualTo("12345678");
        assertThat(savedCrewMember.getFirstName()).isEqualTo(c.getFirstName());
        assertThat(savedCrewMember.getLastName()).isEqualTo(c.getLastName());
        assertThat(savedCrewMember.getEmail()).isEqualTo(c.getEmail());
        verify(crewMemberRepository, times(1)).save(c);
    }

    @Test
    void testDeleteCrewMemberSuccess() {
        // Given
        CrewMember c = new CrewMember();
        c.setFirstName("John");
        c.setLastName("Doe");
        c.setEmail("john.doe@example.com");
        c.setPassword("temp");
        c.setRole("Student");
        c.setQualifiedPosition("Producer");

        given(crewMemberRepository.findById("12345678")).willReturn(Optional.of(c));
        doNothing().when(crewMemberRepository).deleteById("12345678");

        // When
        crewMemberService.delete("12345678");

        // Then
        verify(crewMemberRepository, times(1)).deleteById("12345678");
    }

    @Test
    void testDeleteCrewMemberNotFound() {
        // Given
        given(crewMemberRepository.findById("12345678")).willReturn(Optional.empty());

        // When
        assertThrows(CrewMemberNotFoundException.class, () -> {
            crewMemberService.delete("12345678");
        });

        // Then
        verify(crewMemberRepository, times(1)).findById("12345678");
    }
}
