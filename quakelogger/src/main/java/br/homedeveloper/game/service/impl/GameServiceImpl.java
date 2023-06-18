package br.homedeveloper.game.service.impl;

import br.homedeveloper.game.converter.GameConverter;
import br.homedeveloper.game.converter.KillConverter;
import br.homedeveloper.game.dto.GameDTO;
import br.homedeveloper.game.enums.MeansOfDeathEnum;
import br.homedeveloper.game.model.Game;
import br.homedeveloper.game.model.Kill;
import br.homedeveloper.game.model.Player;
import br.homedeveloper.game.repository.GameRepository;
import br.homedeveloper.game.repository.KillRepository;
import br.homedeveloper.game.repository.PlayerRepository;
import br.homedeveloper.game.service.FileService;
import br.homedeveloper.game.service.GameService;
import br.homedeveloper.game.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

    private String regexKill = "([\\d]+:[\\d]+) Kill: .+: (.+) killed (.+) by ([\\w_]+)";

    private final FileService fileService;
    private final GameRepository gameRepository;
    private final KillRepository killRepository;
    private final PlayerService playerService;

    @Override
    public GameDTO processLog(MultipartFile file) {
        try {
            List<String> events = fileService.readFile(file);
            Game result = processLog(events);
            return GameConverter.parse(result);
        }catch (IOException e){
            throw new RuntimeException("The log file is invalid. It's not possible to process.");
        }
    }

    @Override
    public HashMap<String, GameDTO> getAllLogs() {
        List<Game> games = gameRepository.findAll();
        HashMap<String, GameDTO> returns = new LinkedHashMap<>();
        games.stream().forEach(game -> {
            returns.put(game.getName(), GameConverter.parse(game));
        });

        return returns;
    }

    @Override
    public Game processLog(Game game, List<String> events) {
        processGame(game, events);
        return game;
    }

    @Override
    public Game processLog(List<String> events) {
        Game game = createGame();
        processGame(game, events);
        return game;
    }

    @Override
    public Game getLastGame() {
        long count = gameRepository.count();
        if(count > 0){
            return gameRepository.findFirstByOrderByCreatedAtDesc();
        }
        return createGame();
    }

    private void processGame(Game game, List<String> gameEvents){
        Pattern pattern = Pattern.compile(regexKill);
        gameEvents
                .stream()
                .forEach(event -> this.processLine(game, event, pattern));
    }

    private void processLine(Game game, String line, Pattern pattern){
        Matcher matcher = pattern.matcher(line);
        boolean matchFound = matcher.find();
        if(matchFound) {
            MatchResult result = matcher.toMatchResult();
            String killerNick = result.group(2);
            String deathNick = result.group(3);
            String mean = result.group(4);
            Player killer = playerService.getPlayer(killerNick);
            Player death = playerService.getPlayer(deathNick);
            game.getPlayers().add(killer);
            game.getPlayers().add(death);
            long count = killRepository.count();
            Kill k = KillConverter.build(game, killer, death, MeansOfDeathEnum.valueOf(mean));
            k.setId(count+1);
            killRepository.save(k);
            gameRepository.save(game);
        }
    }

    private Game createGame(){
        String gameName = "game_";
        Long id = 0L;
        Game game = new Game();

        long count = gameRepository.count();
        if(count > 0){
            Game ultimoGame = gameRepository.findFirstByOrderByCreatedAtDesc();
            id = ultimoGame.getId()+1;
            gameName += ultimoGame.getId();
        }else{
            gameName+= "0";
            id = 1L;
        }

        game.setName(gameName);
        game.setId(id);
        game.setCreatedAt(ZonedDateTime.now());
        gameRepository.save(game);
        return game;
    }
}

