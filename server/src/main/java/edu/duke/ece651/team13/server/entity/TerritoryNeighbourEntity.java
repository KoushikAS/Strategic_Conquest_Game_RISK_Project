package edu.duke.ece651.team13.server.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

class CompositeKey implements Serializable {
    private TerritoryEntity territory;
    private TerritoryEntity neighbour;
}

/**
 * This class handles the owner, neighbors, unit numbers
 * and current attackers of the territory
 */
@Entity
@Table(name="TERRITORY_NEIGHBOUR_MAPPING")
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(CompositeKey.class)
public class TerritoryNeighbourEntity {

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "TERRITORY_ID", referencedColumnName = "id")
    private TerritoryEntity territory;

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "TERRITORY_ID", referencedColumnName = "id")
    private TerritoryEntity neighbour;
}
