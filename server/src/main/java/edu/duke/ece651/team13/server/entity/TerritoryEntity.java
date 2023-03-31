package edu.duke.ece651.team13.server.entity;

import edu.duke.ece651.team13.shared.territory.Territory;
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

}
