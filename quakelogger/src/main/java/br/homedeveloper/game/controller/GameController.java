package br.homedeveloper.game.controller;

import br.homedeveloper.game.dto.GameDTO;
import br.homedeveloper.game.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    @CrossOrigin
    @GetMapping
    public ResponseEntity<HashMap<String, GameDTO>> getAllLogs(){
        return ResponseEntity.ok(gameService.getAllLogs());
    }
}
