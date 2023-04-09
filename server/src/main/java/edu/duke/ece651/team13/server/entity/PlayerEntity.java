package edu.duke.ece651.team13.server.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.duke.ece651.team13.server.enums.PlayerStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

/**
 * This class handles the information of one human player
 */
@Entity
@Table(name = "PLAYER")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlayerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "playerSeq")
    @SequenceGenerator(name = "playerSeq")
    private Long Id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GAME_ID")
    @JsonBackReference
    private GameEntity game;

    @Column(name = "NAME", length = 50, nullable = false, unique = false)
    private String name;

    @Column(name = "Status")
    @Enumerated(EnumType.STRING)
    private PlayerStatusEnum status;

    @Column(name = "FOOD_RESOURCE")
    private int foodResource; //food resource totals of this player

    @Column(name = "TECH_RESOURCE")
    private int techResource; //tech resource totals of this player

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    @JsonIgnore
    private UserEntity user;

    /**
     * Construct a new Player
     */
    public PlayerEntity(String name) {
        this.name = name;
        this.status = PlayerStatusEnum.PLAYING;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerEntity that = (PlayerEntity) o;
        return Id.equals(that.Id) && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, name);
    }
}
