package edu.duke.ece651.team13.server.dto;

import edu.duke.ece651.team13.server.enums.UnitMappingEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UnitDTO {
    private UnitMappingEnum unitType;
    private int unitNum;
}
