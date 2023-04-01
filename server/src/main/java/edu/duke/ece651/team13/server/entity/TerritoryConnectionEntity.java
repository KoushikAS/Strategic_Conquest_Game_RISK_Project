package edu.duke.ece651.team13.server.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;


/**
 * This class handles the owner, neighbors, unit numbers
 * and current attackers of the territory
 */
@Entity
@Table(name="TERRITORY_NEIGHBOUR_MAPPING")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TerritoryConnectionEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "territoryConnectionSeq")
    @SequenceGenerator(name = "territoryConnectionSeq")
    private Long Id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "SOURCE_TERRITORY_ID", referencedColumnName = "id")
    @JsonBackReference
    private TerritoryEntity sourceTerritory;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "DEST_NEIGHBOUR_ID", referencedColumnName = "id")
    @JsonBackReference
    private TerritoryEntity destinationTerritory;

    @Column(name = "DISTANCE")
    private Integer distance;

    public TerritoryConnectionEntity(TerritoryEntity sourceTerritory, TerritoryEntity destinationTerritory, Integer distance ){
        this.sourceTerritory = sourceTerritory;
        this.destinationTerritory = destinationTerritory;
        this.distance = distance;

    }
}
