package edu.duke.ece651.team13.server.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import edu.duke.ece651.team13.server.enums.TerritoryDisplayEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TERRITORY_VIEW")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TerritoryViewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "territoryViewSeq")
    @SequenceGenerator(name = "territoryViewSeq")
    private Long Id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TERRITORY")
    @JsonBackReference
    private TerritoryEntity territory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VIEWER_ID")
    @JsonManagedReference
    private PlayerEntity viewer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DISPLAY_OWNER_ID")
    @JsonManagedReference
    private PlayerEntity displayOwner;

    @OneToMany(mappedBy = "territoryView", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<UnitViewEntity> unitViews = new ArrayList<>(); //size=7; used to indicate the number of different types of unit (level0~level6);

    /**
     * if INVISIBLE -> only the outline should be displayed
     * if VISIBLE_NEW -> display info stored in this territoryViewEntity using appropriate colors
     * if VISIBLE_OLD -> display info stored in this territoryViewEntity with clear indicate that info is old (e.g., gray coloring)
     */
    @Column(name = "DISPLAY_TYPE")
    @Enumerated(EnumType.STRING)
    private TerritoryDisplayEnum displayType;
}
