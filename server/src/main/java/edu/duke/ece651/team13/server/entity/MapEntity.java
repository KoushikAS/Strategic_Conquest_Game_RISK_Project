package edu.duke.ece651.team13.server.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This class handles the owner, neighbors, unit numbers
 * and current attackers of the territory
 */
@Entity
@Table(name="MAP")
@Data
@NoArgsConstructor
public class MapEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "mapSeq")
    @SequenceGenerator(name = "mapSeq")
    private Long Id;

    @OneToMany(
            mappedBy = "map",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<TerritoryEntity> territories = new ArrayList<>();
}
