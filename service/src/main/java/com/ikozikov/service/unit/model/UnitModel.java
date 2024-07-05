package com.ikozikov.service.unit.model;

import com.ikozikov.service.subdivision.model.SubdivisionModel;
import com.ikozikov.service.unitType.model.UnitTypeModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "unit")
@Getter
@Setter
@NoArgsConstructor
@Builder
public class UnitModel {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "unit_id")
  private Long id;

  private String unitName;
  private String description;
  private String unitIcon;
  private Integer amount;
  
  @ManyToOne
  @JoinColumn(name = "unit_type_id", nullable = false)
  private UnitTypeModel unitType;
  
  @ManyToOne
  @JoinColumn(name = "subdivision")
  private SubdivisionModel subdivision;
}
