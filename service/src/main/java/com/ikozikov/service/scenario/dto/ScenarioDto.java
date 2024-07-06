package com.ikozikov.service.scenario.dto;

import com.ikozikov.service.scenario.model.Scenario;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ScenarioDto {
  public Long id;
  public String name;
  public String description;
  private Date date_created;
  private Date date_modified;
  
  public static ScenarioDto toDto(Scenario scenario) {
    return ScenarioDto.builder()
        .id(scenario.getId())
        .name(scenario.getName())
        .description(scenario.getDescription())
        .date_created(scenario.getDate_created())
        .date_modified(scenario.getDate_modified())
        .build();
  }
}
