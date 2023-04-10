package edu.duke.ece651.team13.server.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TERRITORY_NEIGHBOUR_MAPPING")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TerritoryConnectionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "territoryConnectionSeq")
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

    @Column(name = "DEST_NEIGHBOUR_ID", insertable = false, updatable = false)
    private Long destinationTerritoryId;

    @Column(name = "DISTANCE")
    private Integer distance;

    public TerritoryConnectionEntity(TerritoryEntity sourceTerritory, TerritoryEntity destinationTerritory, Integer distance) {
        this.sourceTerritory = sourceTerritory;
        this.destinationTerritory = destinationTerritory;
        this.distance = distance;

    }
}
