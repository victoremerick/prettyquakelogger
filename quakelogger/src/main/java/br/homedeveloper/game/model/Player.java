package br.homedeveloper.game.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "player")
public class Player {
    @Id
    private Long id;

    private String name;

    @OneToMany(mappedBy = "killer")
    private List<Kill> kills = new ArrayList<>();

    @OneToMany(mappedBy = "death")
    private List<Kill> deaths = new ArrayList<>();

}

