package com.ikozikov.service.unit.dto;

import com.ikozikov.service.unit.model.UnitModel;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UnitDto {
  
  private Long id;
  private String unitName;
  private String description;
  private String unitIcon;
  private Integer amount;
  private Long unitType;
  private Long subdivision;
  
  public static UnitDto toDto(UnitModel unitModel) {
    return UnitDto.builder()
        .id(unitModel.getId())
        .unitName(unitModel.getUnitName())
        .description(unitModel.getDescription())
        .unitIcon(unitModel.getUnitIcon())
        .amount(unitModel.getAmount())
        .unitType(unitModel.getUnitType().getId())
        .subdivision(unitModel.getSubdivision().getId())
        .build();
  }

  public static UnitModel toModel(UnitDto unitDto) {
    return UnitModel.builder()
        .unitName(unitDto.getUnitName())
        .description(unitDto.getDescription())
        .unitIcon(unitDto.getUnitIcon())
        .amount(unitDto.getAmount())
        .build();
  }
}
