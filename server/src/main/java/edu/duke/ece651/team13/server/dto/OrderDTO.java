package edu.duke.ece651.team13.server.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

    private Long sourceTerritoryId;
    private Long destinationTerritoryId;
    //TODO Update to indicate type of units and add value
    private int units;
    private String orderType;
}
