package edu.duke.ece651.team13.server.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import edu.duke.ece651.team13.server.Game;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "MAP")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MapEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mapSeq")
    @SequenceGenerator(name = "mapSeq")
    private Long Id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GAME_ID")
    @JsonBackReference
    private GameEntity game;

    @OneToMany(mappedBy = "map", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<TerritoryEntity> territories = new ArrayList<>();
}