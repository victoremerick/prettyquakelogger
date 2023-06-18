package br.homedeveloper.game.service;

import br.homedeveloper.game.dto.GameDTO;
import br.homedeveloper.game.model.Game;
import br.homedeveloper.game.model.Player;
import br.homedeveloper.game.repository.GameRepository;
import br.homedeveloper.game.repository.KillRepository;
import br.homedeveloper.game.service.impl.GameServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class GameServiceTest {

    @Mock
    private FileService fileService;
    @Mock
    private GameRepository gameRepository;
    @Mock
    private KillRepository killRepository;
    @Mock
    private PlayerService playerService;

    private GameServiceImpl gameService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        gameService = new GameServiceImpl(fileService, gameRepository, killRepository, playerService);
    }

    @Test
    void testProcessLog_ValidFile() throws IOException {
        String fileContent = "1:23 Kill: Player1 killed Player2 by MOD_GUN";
        List<String> events = List.of(fileContent);
        when(fileService.readFile(any())).thenReturn(events);
        Game savedGame = new Game();
        when(gameRepository.save(any(Game.class))).thenReturn(savedGame);
        Player player1 = Player.builder().id(1L).name("Player1").build();
        Player player2 = Player.builder().id(2L).name("Player2").build();
        when(playerService.getPlayer("Player1")).thenReturn(player1);
        when(playerService.getPlayer("Player2")).thenReturn(player2);
        GameDTO result = gameService.processLog(new MockMultipartFile("test.log", fileContent.getBytes(StandardCharsets.UTF_8)));
        assertNotNull(result);
    }

    @Test
    void testProcessLog_InvalidFile() throws IOException {
        when(fileService.readFile(any())).thenThrow(IOException.class);
        assertThrows(RuntimeException.class, () -> gameService.processLog(new MockMultipartFile("test.log", "invalid".getBytes(StandardCharsets.UTF_8))));
    }

    @Test
    void testGetAllLogs() {
        List<Game> games = List.of(
                new Game(1L, "game1", ZonedDateTime.now(), new ArrayList<>(), new HashSet<>()),
                new Game(2L, "game2", ZonedDateTime.now(), new ArrayList<>(), new HashSet<>())
        );
        when(gameRepository.findAll()).thenReturn(games);
        HashMap<String, GameDTO> result = gameService.getAllLogs();
        assertEquals(2, result.size());
        assertNotNull(result.get("game1"));
        assertNotNull(result.get("game2"));
    }
}
