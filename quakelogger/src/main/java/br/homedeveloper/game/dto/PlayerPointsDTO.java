package br.homedeveloper.game.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlayerPointsDTO {
    private String player;
    private Long points;
}
