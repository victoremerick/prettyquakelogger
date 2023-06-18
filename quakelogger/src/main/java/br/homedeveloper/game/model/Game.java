package br.homedeveloper.game.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "game")
@EqualsAndHashCode
public class Game {
    @Id
    private Long id;

    private String name;

    private ZonedDateTime createdAt;

    @OneToMany(mappedBy = "game")
    private List<Kill> kills = new ArrayList<>();

    @ManyToMany
    private Set<Player> players = new HashSet<>();
}

