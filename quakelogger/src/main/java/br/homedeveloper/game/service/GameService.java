package br.homedeveloper.game.service;

import br.homedeveloper.game.dto.GameDTO;
import br.homedeveloper.game.model.Game;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;

public interface GameService {
    GameDTO processLog(MultipartFile file);
    HashMap<String, GameDTO> getAllLogs();
    Game processLog(List<String> events);
    Game getLastGame();
    Game processLog(Game game, List<String> events);
}

