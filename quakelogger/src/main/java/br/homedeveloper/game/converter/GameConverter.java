package br.homedeveloper.game.converter;

import br.homedeveloper.game.dto.GameDTO;
import br.homedeveloper.game.model.Game;
import br.homedeveloper.game.model.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class GameConverter {

    private GameConverter(){}

    public static GameDTO parse(Game game){
        GameDTO dto = new GameDTO();
        dto.setTotalKills(game.getKills().size());
        dto.setKills(calculateKills(game));
        dto.setPlayers(getPlayers(game.getPlayers()));
        return dto;
    }

    private static HashMap<String, Long> calculateKills(Game game){
        HashMap<String, Long> kills = new HashMap<>();
        game.getPlayers().stream()
                .filter(player -> !player.getName().equals("<world>"))
                .forEach(player -> {
                    long countWorldDeaths = player.getDeaths().stream()
                            .filter(kill -> kill.getGame().equals(game))
                            .filter(kill -> kill.getKiller().getName().equals("<world>")).count();
                    long countKills = player.getKills().size();
                    kills.put(player.getName(), countKills-countWorldDeaths);
                });
        return kills;
    }

    private static List<String> getPlayers(Set<Player> players){
        return players.stream()
                .map(Player::getName)
                .filter(s -> !s.equals("<world>"))
                .collect(Collectors.toList());
    }
}
