package edu.duke.ece651.team13.server.repository;

import edu.duke.ece651.team13.server.entity.AttackerEntity;
import edu.duke.ece651.team13.server.entity.TerritoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttackerRepository extends CrudRepository<AttackerEntity, Long> {

    List<AttackerEntity> findByTerritory(TerritoryEntity territory);

    void deleteByTerritory(TerritoryEntity territory);
}
