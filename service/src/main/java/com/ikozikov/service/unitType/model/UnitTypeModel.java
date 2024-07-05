package com.ikozikov.service.unitType.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
public class UnitTypeModel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "unit_type_id")
  private Long id;
  
  private String type_name;
  
  private String description;
  
  private String icon_name;
}
