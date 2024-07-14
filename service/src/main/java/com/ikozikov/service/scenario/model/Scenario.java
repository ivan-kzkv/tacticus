package com.ikozikov.service.scenario.model;

import com.ikozikov.service.commit.model.CommitModel;
import com.ikozikov.service.scenario.dto.ScenarioDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Scenario {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private String description;
  
  @Column(updatable = false)
  private Date date_created;
  
  private Date date_modified;

  @OneToMany(mappedBy = "scenario", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<CommitModel> commits;

  
  public static Scenario toModel(ScenarioDto scenarioDto) {
    return Scenario.builder()
        .id(scenarioDto.getId())
        .name(scenarioDto.getName())
        .description(scenarioDto.getDescription())
        .date_created(scenarioDto.getDate_created())
        .date_modified(scenarioDto.getDate_modified())
        .build();
  }
}
