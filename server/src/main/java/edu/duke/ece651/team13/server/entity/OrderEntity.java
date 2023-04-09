package edu.duke.ece651.team13.server.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import edu.duke.ece651.team13.shared.enums.OrderMappingEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name = "GAME_ORDER")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orderSeq")
    @SequenceGenerator(name = "orderSeq")
    private Long Id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PLAYER_ID")
    @JsonManagedReference
    private PlayerEntity player;

    @Column(name = "ORDER_TYPE")
    @Enumerated(EnumType.STRING)
    private OrderMappingEnum orderType;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SOURCE_TERRITORY_ID")
    @JsonIgnore
    private TerritoryEntity source;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DESTINATION_TERRITORY_ID")
    @JsonIgnore
    private TerritoryEntity destination;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UnitEntity> units = new ArrayList<>();

    public void addUnit(UnitEntity unit) {
        units.add(unit);
    }

    public void removeUnit(UnitEntity unit) {
        units.remove(unit);
    }

    public int getTotalUnitNum(){
        int total = 0;
        for(UnitEntity unit: units){
            total += unit.getUnitNum();
        }
        return total;
    }
}