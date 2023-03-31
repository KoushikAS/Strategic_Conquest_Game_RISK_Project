package edu.duke.ece651.team13.server.entity;

import edu.duke.ece651.team13.shared.territory.TerritoryRO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

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
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long Id;

    @Column(name="NAME", length=50, nullable=false, unique=false)
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "OWNER_ID", referencedColumnName = "id")
    private PlayerEntity owner;

    @Column(name="UNIT_NUM")
    private int unitNum;

    @ManyToMany
    private Set<TerritoryEntity> neighbours = new HashSet<>();


    public TerritoryEntity(String name) {
        this.name = name;
        this.owner = null;
        this.unitNum = 0;
        this.neighbours = new HashSet<>();
    }

    public TerritoryEntity(String name, int unitNum) {
        this.name = name;
        this.owner = null;
        this.unitNum = unitNum;
        this.neighbours = new HashSet<>();
    }

    private TerritoryEntity(TerritoryEntity toCopy) {
        this(toCopy.name, toCopy.owner, toCopy.unitNum);
    }

    private TerritoryEntity(String name,
                            PlayerEntity owner,
                            int unitNum) {
        this.name = name;
        this.owner = owner;
        this.unitNum = unitNum;
        this.neighbours = new HashSet<>();
    }


}
