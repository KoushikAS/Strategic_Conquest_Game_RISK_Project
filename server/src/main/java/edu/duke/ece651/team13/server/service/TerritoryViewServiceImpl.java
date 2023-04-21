package edu.duke.ece651.team13.server.service;

import edu.duke.ece651.team13.server.entity.PlayerEntity;
import edu.duke.ece651.team13.server.entity.TerritoryConnectionEntity;
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
    public TerritoryViewEntity initTerritoryView(TerritoryEntity toDisplay, PlayerEntity viewer){
        TerritoryViewEntity territoryView = new TerritoryViewEntity();
        territoryView.setToDisplay(toDisplay);
        toDisplay.getTerritoryViews().add(territoryView);
        territoryView.setViewer(viewer);
        territoryView.setOwnerDisplay(toDisplay.getOwner());
        if(isVisible(toDisplay, viewer)) territoryView.setDisplayType(TerritoryDisplayEnum.VISIBLE_NEW);
        else territoryView.setDisplayType(TerritoryDisplayEnum.INVISIBLE);
        return repository.save(territoryView);
    }

    /**
     * check whether the territory is visible to the viewer
     * -> territory is an immediately adjacent enemy territory to the viewer
     * @param territory territory
     * @param viewer viewer
     * @return true if it is, otherwise false
     */
    private boolean isVisible(TerritoryEntity territory, PlayerEntity viewer){
        for(TerritoryConnectionEntity t: territory.getConnections()){
            if(t.getSourceTerritory().getOwner().equals(viewer)) return true;
        }
        return false;
    }
}
