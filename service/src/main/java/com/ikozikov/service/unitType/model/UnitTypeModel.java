package com.ikozikov.service.unitType.model;

import com.ikozikov.service.unitType.dto.UnitTypeDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "unit_type")
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class UnitTypeModel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "unit_type_id")
  private Long id;
  
  private String type_name;
  
  private String description;
  
  private String icon_name;
  
  public static UnitTypeModel dtoToModel(UnitTypeDto unitTypeDto) {
    return UnitTypeModel.builder()
        .id(unitTypeDto.getId())
        .type_name(unitTypeDto.getTypeName())
        .description(unitTypeDto.getDescription())
        .icon_name(unitTypeDto.getIconName())
        .build();
  }
}
