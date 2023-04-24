package edu.duke.ece651.team13.server.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * SPY Unit
 */
@Entity
@Table(name = "SPY")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SpyUnitEntity {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "unitSeq")
    @SequenceGenerator(name = "spySeq")
    private Long Id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TERRITORY_ID")
    @JsonBackReference
    private TerritoryEntity territory;

    @Column(name = "SPY_NUM")
    private Integer unitNum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PLAYER_ID")
    @JsonBackReference
    private PlayerEntity owner;

    public SpyUnitEntity(Integer unitNum, PlayerEntity player){
        this.unitNum = unitNum;
        this.owner = player;
    }
}
