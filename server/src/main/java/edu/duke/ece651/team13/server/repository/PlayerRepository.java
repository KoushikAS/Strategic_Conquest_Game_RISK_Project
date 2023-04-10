package edu.duke.ece651.team13.server.repository;

import edu.duke.ece651.team13.server.entity.PlayerEntity;
import edu.duke.ece651.team13.server.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface PlayerRepository extends CrudRepository<PlayerEntity, Long>, CustomPlayerRepository{

    List<PlayerEntity> findByUser(UserEntity user);

}
