package edu.duke.ece651.team13.server.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import edu.duke.ece651.team13.shared.enums.OrderMappingEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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

    @Column(name = "UNIT_NUM")
    private long unitNum;
}