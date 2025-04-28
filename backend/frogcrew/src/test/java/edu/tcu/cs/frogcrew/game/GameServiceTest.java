package edu.tcu.cs.frogcrew.game;

import edu.tcu.cs.frogcrew.game.converter.GameDtoToGameConverter;
import edu.tcu.cs.frogcrew.game.dto.GameDto;
import edu.tcu.cs.frogcrew.gameschedule.GameSchedule;
import edu.tcu.cs.frogcrew.gameschedule.GameScheduleRepository;
import edu.tcu.cs.frogcrew.gameschedule.GameScheduleService;
import edu.tcu.cs.frogcrew.system.exception.ObjectNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class GameServiceTest {
    @Mock
    GameRepository gameRepository;

    @Mock
    GameScheduleRepository gameScheduleRepository;

    @Mock
    GameDtoToGameConverter gameDtoToGameConverter;

    @InjectMocks
    GameService gameService;

    @InjectMocks
    GameScheduleService gameScheduleService;

    List<Game> games;

    @BeforeEach
    void setUp() {
        this.games = new ArrayList<>();

        Game g1 = new Game();
        g1.setGameId(123);
        g1.setDate("2025-08-03");
        g1.setTime("20:00:00");
        g1.setVenue("Amon G. Carter Stadium");
        g1.setOpponent("UT");
        g1.setFinalized(false);

        Game g2 = new Game();
        g2.setGameId(100);
        g2.setDate("2025-09-22");
        g2.setTime("16:00:00");
        g2.setVenue("Amon G. Carter Stadium");
        g2.setOpponent("Texas Tech");
        g2.setFinalized(false);

        games.add(g1);
        games.add(g2);
    }

    @AfterEach
    void tearDown() {}

    @Test
    void testFindAllGamesSuccess() {
        // Given
        given(this.gameRepository.findAll()).willReturn(games);

        // When
        List<Game> actualGames = this.gameService.findAll();

        // Then
        assertThat(actualGames.size()).isEqualTo(games.size());

        verify(this.gameRepository, times(1)).findAll();
    }

    @Test
    void testFindGameByIdSuccess() {
        // Given
        Game g1 = new Game();
        g1.setGameId(123);
        g1.setDate("2025-08-03");
        g1.setTime("20:00:00");
        g1.setVenue("Amon G. Carter Stadium");
        g1.setOpponent("UT");
        g1.setFinalized(false);
        given(gameRepository.findById(123)).willReturn(Optional.of(g1));

        // When
        Game foundGame = this.gameService.findById(123);

        // Then
        assertThat(foundGame.getGameId()).isEqualTo(123);
        assertThat(foundGame.getDate()).isEqualTo("2025-08-03");
        assertThat(foundGame.getTime()).isEqualTo("20:00:00");
        assertThat(foundGame.getVenue()).isEqualTo("Amon G. Carter Stadium");
        assertThat(foundGame.isFinalized()).isFalse();

        verify(this.gameRepository, times(1)).findById(123);

    }

    @Test
    void testFindGameByIdNotFound() {
        // Given
        given(this.gameRepository.findById(Mockito.any(Integer.class))).willReturn(Optional.empty());

        // When
        Throwable thrown = catchThrowable(() -> {
            Game game = this.gameService.findById(1);
        });

        // Then
        assertThat(thrown)
                .isInstanceOf(ObjectNotFoundException.class)
                .hasMessage("Could not find game with id 1");
        verify(gameRepository, times(1)).findById(1);
    }

    @Test
    void testAddGameToScheduleSuccess() {
        // Given
        GameSchedule schedule = new GameSchedule();
        schedule.setId(1);
        schedule.setSport("Football");
        schedule.setSeason("2025-2026");

        GameDto gameDto = new GameDto(1, "Football", "2025-10-10", "20:00:00", "Amon G. Carter Stadium", "Baylor", "Director,Producer", true, false);

        Game game = new Game();
        game.setOpponent("Baylor");
        game.setDate("2025-10-10");

        given(gameScheduleRepository.findById(1)).willReturn(Optional.of(schedule));
        given(gameDtoToGameConverter.convert(gameDto)).willReturn(game);
        given(gameRepository.save(game)).willReturn(game);

        // When
        Game savedGame = gameService.addGameToSchedule(1, gameDto);

        // Then
        assertThat(savedGame).isNotNull();
        assertThat(savedGame.getScheduleId()).isEqualTo(1);
        assertThat(savedGame.getOpponent()).isEqualTo("Baylor");
        assertThat(savedGame.getDate()).isEqualTo("2025-10-10");

        verify(gameScheduleRepository, times(1)).findById(1);
        verify(gameDtoToGameConverter, times(1)).convert(gameDto);
        verify(gameRepository, times(1)).save(game);
    }

    @Test
    void testAddGameToScheduleThrowsExceptionWhenScheduleNotFound() {
        // Given
        GameDto gameDto = new GameDto(1, "Football", "2025-10-10", "20:00:00", "Amon G. Carter Stadium", "Baylor", "Director,Producer", true, false);

        given(this.gameScheduleRepository.findById(99)).willReturn(Optional.empty());

        // When
        Throwable thrown = catchThrowable(() -> {
            this.gameService.addGameToSchedule(99, gameDto);
        });

        // Then
        assertThat(thrown)
                .isInstanceOf(ObjectNotFoundException.class)
                .hasMessage("Could not find GameSchedule with id 99");

        verify(this.gameScheduleRepository, times(1)).findById(99);
    }

}
