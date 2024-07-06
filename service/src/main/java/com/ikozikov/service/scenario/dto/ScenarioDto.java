package com.ikozikov.service.scenario.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ScenarioDto {
  public String name;
  public String description;
}
