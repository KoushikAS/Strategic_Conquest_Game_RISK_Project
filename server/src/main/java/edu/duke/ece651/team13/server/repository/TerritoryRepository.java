package edu.duke.ece651.team13.server.repository;

import edu.duke.ece651.team13.server.entity.AttackerEntity;
import edu.duke.ece651.team13.server.entity.MapEntity;
import edu.duke.ece651.team13.server.entity.PlayerEntity;
import edu.duke.ece651.team13.server.entity.TerritoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TerritoryRepository extends CrudRepository<TerritoryEntity, Long> {

    List<TerritoryEntity> findByOwner(PlayerEntity owner);
}
