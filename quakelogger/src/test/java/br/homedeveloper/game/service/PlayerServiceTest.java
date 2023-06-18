package br.homedeveloper.game.service;

import br.homedeveloper.game.model.Player;
import br.homedeveloper.game.repository.PlayerRepository;
import br.homedeveloper.game.service.impl.PlayerServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.when;

class PlayerServiceTest {

    @Mock
    private PlayerRepository playerRepository;

    private PlayerServiceImpl playerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        playerService = new PlayerServiceImpl(playerRepository);
    }

    @Test
    void testGetPlayer_PlayerExists() {
        String playerNick = "player1";
        Player player = Player.builder().id(1L).name(playerNick).build();
        when(playerRepository.findByName(playerNick)).thenReturn(Optional.of(player));
        Player result = playerService.getPlayer(playerNick);
        Assertions.assertEquals(player, result);
    }

    @Test
    void testGetPlayer_PlayerDoesNotExist() {
        String playerNick = "player1";
        when(playerRepository.findByName(playerNick)).thenReturn(Optional.empty());
        Player result = playerService.getPlayer(playerNick);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(playerNick, result.getName());
    }

    @Test
    void testCreatePlayer_PlayerExists() {
        String playerNick = "player1";
        Player existingPlayer = Player.builder().id(1L).name(playerNick).build();
        when(playerRepository.findByName(playerNick)).thenReturn(Optional.of(existingPlayer));
        Player result = playerService.createPlayer(playerNick);
        Assertions.assertEquals(existingPlayer, result);
    }

    @Test
    void testCreatePlayer_PlayerDoesNotExist() {
        String playerNick = "player1";
        when(playerRepository.findByName(playerNick)).thenReturn(Optional.empty());
        Player result = playerService.createPlayer(playerNick);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(playerNick, result.getName());
    }
}

