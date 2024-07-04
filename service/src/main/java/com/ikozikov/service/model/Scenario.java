package com.ikozikov.service.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Scenario {
  
  @Id
  @GeneratedValue
  private Long id;

  private String name;
  private String description;
  
  @Column(updatable = false)
  private Date date_created;
  
  private Date date_modified;

}
