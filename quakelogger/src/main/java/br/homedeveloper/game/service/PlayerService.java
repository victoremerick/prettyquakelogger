package br.homedeveloper.game.service;

import br.homedeveloper.game.model.Player;

public interface PlayerService {

    Player getPlayer(String player);

    Player createPlayer(String player);
}
