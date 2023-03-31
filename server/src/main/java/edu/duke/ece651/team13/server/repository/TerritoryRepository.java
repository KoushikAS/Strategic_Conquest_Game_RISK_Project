package edu.duke.ece651.team13.server.repository;

import edu.duke.ece651.team13.server.entity.TerritoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TerritoryRepository extends CrudRepository<TerritoryEntity, Long> {
}
