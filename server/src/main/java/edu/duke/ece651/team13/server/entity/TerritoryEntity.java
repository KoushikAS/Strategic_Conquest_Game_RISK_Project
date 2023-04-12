package edu.duke.ece651.team13.server.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import edu.duke.ece651.team13.server.enums.UnitMappingEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Territory
 */
@Entity
@Table(name = "TERRITORY")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TerritoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "territorySeq")
    @SequenceGenerator(name = "territorySeq")
    private Long Id;

    @Column(name = "NAME", length = 50, nullable = false, unique = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OWNER_ID")
    @JsonManagedReference
    private PlayerEntity owner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MAP_ID")
    @JsonBackReference
    private MapEntity map;

    @OneToMany(mappedBy = "sourceTerritory", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<TerritoryConnectionEntity> connections = new ArrayList<>();

    @Column(name = "FOOD_PRODUCTION")
    private int foodProduction; //food resource production this territory generate each round

    @Column(name = "TECH_PRODUCTION")
    private int techProduction; //tech resource production this territory generate each round

    @OneToMany(mappedBy = "territory", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<UnitEntity> units = new ArrayList<>();

    /**
     * Adds a UnitEntity object to the collection of units.
     * @param unit the UnitEntity object to be added
     */
    public void addUnit(UnitEntity unit) {
        units.add(unit);
    }

    /**
     * This is a helper function that gets the UnitEntity with the specified unit type in the
     * specified territory
     *
     * @return the UnitEntity of the unitType
     */
    public UnitEntity getUnitForType(UnitMappingEnum unitType) {
        return this.units.
                stream()
                .filter(t -> t.getUnitType().equals(unitType))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }
}