package br.homedeveloper.game.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data
public class GameDTO {
    @JsonProperty("total_kills")
    private int totalKills;
    private List<String> players;
    private HashMap<String, Long> kills = new HashMap<>();
}
