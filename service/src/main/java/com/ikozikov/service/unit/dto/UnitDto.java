package com.ikozikov.service.unit.dto;

import com.ikozikov.service.unit.model.UnitModel;
import lombok.Builder;
import lombok.Data;

import java.util.Optional;

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
    var unit = UnitDto.builder()
        .id(unitModel.getId())
        .unitName(unitModel.getUnitName())
        .description(unitModel.getDescription())
        .unitIcon(unitModel.getUnitIcon())
        .amount(unitModel.getAmount())
        .build();
    Optional.ofNullable(unitModel.getSubdivision()).ifPresent(subdivisionModel -> unit.setSubdivision(subdivisionModel.getId()));
    Optional.ofNullable(unitModel.getUnitType()).ifPresent(unitTypeModel -> unit.setSubdivision(unitTypeModel.getId()));
    return unit;
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
