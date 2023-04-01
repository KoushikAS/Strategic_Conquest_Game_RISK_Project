package edu.duke.ece651.team13.server.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * This class handles the owner, neighbors, unit numbers
 * and current attackers of the territory
 */
@Entity
@Table(name="TERRITORY")
@Data
@NoArgsConstructor
public class TerritoryEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "territorySeq")
    @SequenceGenerator(name = "territorySeq")
    private Long Id;

    @Column(name="NAME", length=50, nullable=false, unique=false)
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "OWNER_ID", referencedColumnName = "id")
    private PlayerEntity owner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "map_id")
    private MapEntity map;

    @Column(name="UNIT_NUM")
    private int unitNum;

}
