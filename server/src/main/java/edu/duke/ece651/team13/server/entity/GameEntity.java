package edu.duke.ece651.team13.server.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import edu.duke.ece651.team13.server.enums.GameStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Entity
@Table(name = "GAME")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GameEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gameSeq")
    @SequenceGenerator(name = "gameSeq")
    private Long Id;

    @OneToMany(mappedBy = "game", fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JsonManagedReference
    private List<PlayerEntity> players = new ArrayList<>();

    @OneToOne(mappedBy = "game", fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JsonManagedReference
    private MapEntity map;

    @Column(name = "ROUND_NO")
    private int roundNo;

    @Column(name = "Status")
    @Enumerated(EnumType.STRING)
    private GameStatusEnum status = GameStatusEnum.PLAYING;

    public PlayerEntity getPlayerEntityById(Long Id) {
        return players.stream().filter(playerEntity -> Objects.equals(playerEntity.getId(), Id))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }
}