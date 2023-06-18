package br.homedeveloper.game.repository;

import br.homedeveloper.game.model.Game;
import br.homedeveloper.game.model.Kill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    Game findFirstByOrderByCreatedAtDesc();
}

