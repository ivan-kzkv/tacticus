package com.ikozikov.service.subdivision.model;

import com.ikozikov.service.shared.utils.TroopsSide;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "subdivision")
@Getter
@Setter
@NoArgsConstructor
public class SubdivisionModel {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "subdivision_id")
  private Long id;

  private String name;
  private String description;
  private SubdivisionLabel label;
  private TroopsSide type;
  
}
