package com.ikozikov.service.unit.dto;

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
}
