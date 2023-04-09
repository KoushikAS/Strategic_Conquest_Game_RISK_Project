package edu.duke.ece651.team13.server.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Entity
@Table(name = "MAP")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MapEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mapSeq")
    @SequenceGenerator(name = "mapSeq")
    private Long Id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GAME_ID")
    @JsonBackReference
    private GameEntity game;

    @OneToMany(mappedBy = "map", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<TerritoryEntity> territories = new ArrayList<>();

    public TerritoryEntity getTerritoryEntityById(Long Id){
        return territories.stream().filter(territoryEntity -> Objects.equals(territoryEntity.getId(), Id))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }
}