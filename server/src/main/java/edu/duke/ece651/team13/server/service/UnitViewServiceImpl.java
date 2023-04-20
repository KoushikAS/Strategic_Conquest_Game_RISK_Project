package edu.duke.ece651.team13.server.service;

import edu.duke.ece651.team13.server.entity.TerritoryViewEntity;
import edu.duke.ece651.team13.server.entity.UnitViewEntity;
import edu.duke.ece651.team13.server.enums.UnitMappingEnum;
import edu.duke.ece651.team13.server.repository.UnitViewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UnitViewServiceImpl implements UnitViewService{
    @Autowired
    private final UnitViewRepository repository;

    @Override
    @Transactional
    public UnitViewEntity createUnitView(TerritoryViewEntity territoryView, UnitMappingEnum unitType, int unitNum) {
        UnitViewEntity unitView = new UnitViewEntity();
        unitView.setTerritoryView(territoryView);
        territoryView.getUnitViews().add(unitView);
        unitView.setUnitType(unitType);
        unitView.setUnitNum(unitNum);
        return repository.save(unitView);
    }
}
