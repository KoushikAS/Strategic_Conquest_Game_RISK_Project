package edu.duke.ece651.team13.server.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * This class handles the information of one human player
 */
@Entity
@Table(name = "ATTACKER")
@Data
@NoArgsConstructor
public class AttackerEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "attackerSeq")
    @SequenceGenerator(name = "attackerSeq")
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "TERRITORY_ID", nullable = false)
    private TerritoryEntity territory;

    @ManyToOne
    @JoinColumn(name = "ATTACKER_PLAYER_ID", nullable = false)
    private PlayerEntity attacker;

    @Column(name = "units")
    private Integer units;

    public AttackerEntity(TerritoryEntity territory, PlayerEntity attacker, Integer units){
        this.territory = territory;
        this.attacker = attacker;
        this.units = units;
    }
}
