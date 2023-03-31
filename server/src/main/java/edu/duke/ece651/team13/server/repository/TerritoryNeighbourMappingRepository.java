package edu.duke.ece651.team13.server.repository;

import edu.duke.ece651.team13.server.entity.TerritoryEntity;
import edu.duke.ece651.team13.server.entity.TerritoryNeighbourEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TerritoryNeighbourMappingRepository extends CrudRepository<TerritoryNeighbourEntity, Long> {
}
