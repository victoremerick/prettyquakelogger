package br.homedeveloper.game.service.impl;

import br.homedeveloper.game.model.Player;
import br.homedeveloper.game.repository.PlayerRepository;
import br.homedeveloper.game.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;

    @Override
    public Player getPlayer(String playerNick) {
        Optional<Player> playerOpt = playerRepository.findByName(playerNick);
        Player player = null;
        if(playerOpt.isEmpty()){
            player = createPlayer(playerNick);
        }else{
            player = playerOpt.get();
        }
        return player;
    }

    @Override
    public Player createPlayer(String playerNick) {
        Optional<Player> playerOpt = playerRepository.findByName(playerNick);
        Player player = null;
        if(playerOpt.isEmpty()){
            player = Player.builder()
                    .id(playerRepository.count()+1)
                    .name(playerNick)
                    .build();
            playerRepository.save(player);
        }else{
            player = playerOpt.get();
        }
        return player;
    }


}
