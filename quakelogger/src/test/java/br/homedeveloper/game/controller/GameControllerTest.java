package br.homedeveloper.game.controller;

import br.homedeveloper.game.dto.GameDTO;
import br.homedeveloper.game.service.GameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class GameControllerTest {

    @Mock
    private GameService gameService;

    private GameController gameController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        gameController = new GameController(gameService);
    }

    @Test
    void testGetAllLogs() {
        HashMap<String, GameDTO> logs = new HashMap<>();
        logs.put("log1", new GameDTO());
        logs.put("log2", new GameDTO());
        when(gameService.getAllLogs()).thenReturn(logs);
        ResponseEntity<HashMap<String, GameDTO>> result = gameController.getAllLogs();
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(logs, result.getBody());
    }
}
