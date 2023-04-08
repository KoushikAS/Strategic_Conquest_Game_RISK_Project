package edu.duke.ece651.team13.server.repository;

import edu.duke.ece651.team13.server.entity.GameEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface GameRepository extends CrudRepository<GameEntity, Long> {

    List<GameEntity> findByRoundNo(Long roundNo);
}
