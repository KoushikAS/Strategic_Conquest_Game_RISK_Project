package edu.duke.ece651.team13.server.entity;

import edu.duke.ece651.team13.shared.enums.PlayerStatusEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * This class handles the information of one human player
 */
@Entity
@Table(name="TERRITORY")
@Data
@NoArgsConstructor
public class PlayerEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long Id;

    @Column(name="NAME", length=50, nullable=false, unique=false)
    private String name;

    @Column(name = "Status")
    @Enumerated(EnumType.STRING)
    private PlayerStatusEnum status;

    /**
     * Construct a new Player
     */
    public PlayerEntity(String name) {
        this.name = name;
        this.status = PlayerStatusEnum.PLAYING;
    }

}
