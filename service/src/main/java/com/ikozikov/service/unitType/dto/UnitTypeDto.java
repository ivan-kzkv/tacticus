package com.ikozikov.service.unitType.dto;

import com.ikozikov.service.unitType.model.UnitTypeModel;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UnitTypeDto {
  private Long id;
  private String typeName;
  private String description;
  private String iconName;
  
  public static UnitTypeDto modelToDto(UnitTypeModel unitTypeModel) {
    return UnitTypeDto.builder()
        .id(unitTypeModel.getId())
        .typeName(unitTypeModel.getType_name())
        .description(unitTypeModel.getDescription())
        .iconName(unitTypeModel.getIcon_name())
        .build();
  }
}
