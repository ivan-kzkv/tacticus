package com.ikozikov.service.controller;

import com.ikozikov.service.dto.ScenarioDto;
import com.ikozikov.service.model.Scenario;
import com.ikozikov.service.service.ScenarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ScenarioController {
  
  @Autowired
  private ScenarioService scenarioService;
  
  @GetMapping(value = "/scenario")
  public List<Scenario> getAllScenarios() {
    return this.scenarioService.listAllScenarios();
  }
  
  @GetMapping(value = "/scenario/{id}")
  public Scenario getScenarioById(@PathVariable(value = "id") Long scenarioId) {
    return this.scenarioService.getOneScenario(scenarioId);
  }
  
  @PostMapping(value = "/scenario")
  public Scenario createScenario(@RequestBody ScenarioDto newScenario) {
    System.out.print("Start controller");
    System.out.print(newScenario);
    return this.scenarioService.createScenario(newScenario);
  }
  
  @PatchMapping(value = "/scenario/{id}")
  public Scenario updateScenario(@PathVariable(value = "id") Long scenarioId, @RequestBody ScenarioDto scenarioDto) {
    return this.scenarioService.updateScenario(scenarioId, scenarioDto);
  }
  
  @DeleteMapping(value = "/scenario")
  public void deleteScenarios(@RequestBody List<Long> scenariosIds) {
    this.scenarioService.deleteScenario(scenariosIds);
  }
  
}
