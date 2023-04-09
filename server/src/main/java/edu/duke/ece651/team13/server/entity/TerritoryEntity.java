package edu.duke.ece651.team13.server.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
}