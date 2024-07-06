package com.ikozikov.service.subdivision.dto;

import com.ikozikov.service.shared.utils.TroopsSide;
import com.ikozikov.service.subdivision.model.SubdivisionLabel;
import com.ikozikov.service.subdivision.model.SubdivisionModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class SubdivisionDto {
  private Long id;
  private String name;
  private String description;
  private SubdivisionLabel label;
  private TroopsSide type;

  public static SubdivisionDto toDto(SubdivisionModel subdivisionModel) {
    return SubdivisionDto.builder()
        .id(subdivisionModel.getId())
        .name(subdivisionModel.getName())
        .description(subdivisionModel.getDescription())
        .label(subdivisionModel.getLabel())
        .type(subdivisionModel.getType())
        .build();
  }
}
