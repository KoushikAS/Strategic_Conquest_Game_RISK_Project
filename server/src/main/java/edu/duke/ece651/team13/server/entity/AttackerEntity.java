package edu.duke.ece651.team13.server.entity;

import edu.duke.ece651.team13.server.enums.UnitMappingEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "ATTACKER")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AttackerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "attackerSeq")
    @SequenceGenerator(name = "attackerSeq")
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "TERRITORY_ID", nullable = false)
    private TerritoryEntity territory;

    @ManyToOne
    @JoinColumn(name = "ATTACKER_PLAYER_ID", nullable = false)
    private PlayerEntity attacker;

    @Column(name = "UNIT_TYPE")
    @Enumerated(EnumType.STRING)
    private UnitMappingEnum unitType;

    @Column(name = "units")
    private Integer units;

    public AttackerEntity(TerritoryEntity territory, PlayerEntity attacker, UnitMappingEnum unitType, Integer units) {
        this.territory = territory;
        this.attacker = attacker;
        this.unitType = unitType;
        this.units = units;
    }
}
