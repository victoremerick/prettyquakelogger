package br.homedeveloper.game.model;

import br.homedeveloper.game.enums.MeansOfDeathEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "kill")
public class Kill {
    @Id
    private Long id;

    @ManyToOne
    private Game game;

    @ManyToOne
    private Player killer;

    @ManyToOne
    private Player death;

    private MeansOfDeathEnum meanOfDeath;
}

