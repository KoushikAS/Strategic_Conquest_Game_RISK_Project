package edu.duke.ece651.team13.server.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import edu.duke.ece651.team13.server.enums.UnitMappingEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "UNIT_VIEW")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UnitViewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "unitViewSeq")
    @SequenceGenerator(name = "unitViewSeq")
    private Long Id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TERRITORY_VIEW_ID")
    @JsonBackReference
    private TerritoryViewEntity territoryView;

    @Column(name = "UNIT_TYPE")
    @Enumerated(EnumType.STRING)
    private UnitMappingEnum unitType;

    @Column(name = "UNIT_NUM")
    private Integer unitNum;
}
