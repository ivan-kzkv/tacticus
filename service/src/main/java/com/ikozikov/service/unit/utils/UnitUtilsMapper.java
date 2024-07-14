package com.ikozikov.service.unit.utils;

import com.ikozikov.service.unit.dto.UnitDto;
import com.ikozikov.service.unit.model.UnitModel;

public class UnitUtilsMapper {
  
  public static UnitDto modelToDto(UnitModel unitModel) {
    return UnitDto.builder()
        .id(unitModel.getId())
        .unitName(unitModel.getUnitName())
        .description(unitModel.getDescription())
        .unitIcon(unitModel.getUnitIcon())
        .amount(unitModel.getAmount())
        .unitType(unitModel.getUnitType().getId())
        .build();
  }


  public static UnitModel dtoToModel(UnitDto unitDto) {
    return UnitModel.builder()
        .unitName(unitDto.getUnitName())
        .description(unitDto.getDescription())
        .unitIcon(unitDto.getUnitIcon())
        .amount(unitDto.getAmount())
        .build();
  }
}
