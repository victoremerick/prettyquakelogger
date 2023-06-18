package br.homedeveloper.game.repository;

import br.homedeveloper.game.model.Kill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KillRepository extends JpaRepository<Kill, Long> {
}

