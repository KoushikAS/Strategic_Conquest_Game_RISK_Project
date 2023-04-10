package edu.duke.ece651.team13.server.repository;

import edu.duke.ece651.team13.server.entity.PlayerEntity;
import edu.duke.ece651.team13.server.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface CustomPlayerRepository {

    PlayerEntity getDetachedPlayer(Long id);

}
