package br.homedeveloper.game.converter;

import br.homedeveloper.game.enums.MeansOfDeathEnum;
import br.homedeveloper.game.model.Game;
import br.homedeveloper.game.model.Kill;
import br.homedeveloper.game.model.Player;

import java.time.ZonedDateTime;

public class KillConverter {

    private KillConverter(){}

    public static Kill build(Game game, Player killer, Player death, MeansOfDeathEnum mean){
        return Kill.builder()
                .game(game)
                .killer(killer)
                .death(death)
                .meanOfDeath(mean)
                .build();
    }

}
