package edu.duke.ece651.team13.server.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="TERRITORY")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TerritoryEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "territorySeq")
    @SequenceGenerator(name = "territorySeq")
    private Long Id;

    @Column(name="NAME", length=50, nullable=false, unique=false)
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "OWNER_ID", referencedColumnName = "id")
    private PlayerEntity owner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MAP_ID")
    @JsonBackReference
    private MapEntity map;

    @Column(name="UNIT_NUM")
    private int unitNum;
}