package edu.duke.ece651.team13.server.service;

import edu.duke.ece651.team13.server.entity.*;
import edu.duke.ece651.team13.server.repository.TerritoryViewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static edu.duke.ece651.team13.server.enums.TerritoryDisplayEnum.*;

@Service
@RequiredArgsConstructor
public class TerritoryViewServiceImpl implements TerritoryViewService{
    @Autowired
    private final TerritoryViewRepository repository;

    @Autowired
    private final UnitViewService unitViewService;

    @Override
    @Transactional
    public TerritoryViewEntity initTerritoryView(TerritoryEntity toDisplay, PlayerEntity viewer){
        TerritoryViewEntity territoryView = new TerritoryViewEntity();
        territoryView.setToDisplay(toDisplay);
        toDisplay.getTerritoryViews().add(territoryView);
        territoryView.setViewer(viewer);
        territoryView.setOwnerDisplay(toDisplay.getOwner());
        if(isVisible(toDisplay, viewer)) territoryView.setDisplayType(VISIBLE_NEW);
        else territoryView.setDisplayType(INVISIBLE);
        return repository.save(territoryView);
    }

    /**
     * INVISIBLE to VISIBLE_NEW; VISIBLE_NEW to VISIBLE_OLD; VISIBLE_OLD to VISIBLE_NEW
     * @param territoryView territoryView to be updated
     * @return update-to-update territoryView
     */
    @Override
    @Transactional
    public TerritoryViewEntity updateTerritoryView(TerritoryViewEntity territoryView){
        // from any type of territoryDisplayEnum to VISIBLE_NEW
        if(isVisible(territoryView.getToDisplay(), territoryView.getViewer())){
            territoryView.setDisplayType(VISIBLE_NEW);
            territoryView.setOwnerDisplay(territoryView.getToDisplay().getOwner());
            for(int i=0; i<territoryView.getUnitsDisplay().size(); i++){
                UnitViewEntity unitView = territoryView.getUnitsDisplay().get(i);
                UnitEntity unit = territoryView.getToDisplay().getUnits().get(i);
                unitViewService.updateUnitView(unitView, unit);
            }
        }
        // from VISIBLE_NEW to VISIBLE_OLD
        else if(territoryView.getDisplayType().equals(VISIBLE_NEW)) territoryView.setDisplayType(VISIBLE_OLD);
        return repository.save(territoryView);
    }

    /**
     * check whether the territory is visible to the viewer
     * -> whether territory belongs to or is an immediately adjacent enemy territory to viewer
     * @param territory territory
     * @param viewer viewer
     * @return true if it is, otherwise false
     */
    private boolean isVisible(TerritoryEntity territory, PlayerEntity viewer){
        if(territory.getOwner().equals(viewer)) return true;
        for(TerritoryConnectionEntity t: territory.getConnections()){
            if(t.getDestinationTerritory().getOwner().equals(viewer)){
                return true;
            }
        }
        return false;
    }
}
