package edu.duke.ece651.team13.server.service;

import edu.duke.ece651.team13.server.entity.PlayerEntity;
import edu.duke.ece651.team13.server.entity.TerritoryEntity;
import edu.duke.ece651.team13.server.entity.TerritoryViewEntity;
import edu.duke.ece651.team13.server.enums.TerritoryDisplayEnum;
import edu.duke.ece651.team13.server.repository.TerritoryViewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class TerritoryViewServiceImpl implements TerritoryViewService{
    @Autowired
    private final TerritoryViewRepository repository;

    @Override
    @Transactional
    public TerritoryViewEntity createTerritoryView(TerritoryEntity territory, PlayerEntity viewer, PlayerEntity displayOwner, TerritoryDisplayEnum displayType){
        TerritoryViewEntity territoryView = new TerritoryViewEntity();
        territoryView.setTerritory(territory);
        territory.getTerritoryViews().add(territoryView);
        territoryView.setViewer(viewer);
        territoryView.setDisplayOwner(displayOwner);
        territoryView.setDisplayType(displayType);
        return repository.save(territoryView);
    }
}
