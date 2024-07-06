package com.ikozikov.service.scenario.controller;

import com.ikozikov.service.scenario.dto.ScenarioDto;
import com.ikozikov.service.scenario.model.Scenario;
import com.ikozikov.service.scenario.service.ScenarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ScenarioController {
  
  @Autowired
  private ScenarioService scenarioService;
  
  @GetMapping(value = "/scenario")
  public List<ScenarioDto> getAllScenarios() {
    return this.scenarioService.listAllScenarios();
  }
  
  @GetMapping(value = "/scenario/{id}")
  public ScenarioDto getScenarioById(@PathVariable(value = "id") Long scenarioId) {
    return this.scenarioService.getOneScenario(scenarioId);
  }
  
  @PostMapping(value = "/scenario")
  @ResponseStatus(HttpStatus.CREATED)
  public ScenarioDto createScenario(@RequestBody ScenarioDto scenarioDto) {
    return this.scenarioService.createScenario(scenarioDto);
  }
  
  @PatchMapping(value = "/scenario/{id}")
  public ScenarioDto updateScenario(@PathVariable(value = "id") Long scenarioId, @RequestBody ScenarioDto scenarioDto) {
    return this.scenarioService.updateScenario(scenarioId, scenarioDto);
  }
  
  @DeleteMapping(value = "/scenario")
  public void deleteScenarios(@RequestBody List<Long> scenariosIds) {
    this.scenarioService.deleteScenario(scenariosIds);
  }
  
}
