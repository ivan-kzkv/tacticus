package com.ikozikov.service.subdivision.model;

import com.ikozikov.service.shared.utils.TroopsSide;
import com.ikozikov.service.subdivision.dto.SubdivisionDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "subdivision")
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class SubdivisionModel {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "subdivision_id")
  private Long id;

  private String name;
  private String description;
  
  @Enumerated(EnumType.STRING)
  private SubdivisionLabel label;
  
  @Enumerated(EnumType.STRING)
  @Column(name = "troops_type")
  private TroopsSide type;
  
  
  public static SubdivisionModel toModel(SubdivisionDto subdivisionDto) {
    return SubdivisionModel.builder()
        .id(subdivisionDto.getId())
        .name(subdivisionDto.getName())
        .description(subdivisionDto.getDescription())
        .label(subdivisionDto.getLabel())
        .type(subdivisionDto.getType())
        .build();
  }
}
