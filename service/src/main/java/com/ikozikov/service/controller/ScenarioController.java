package com.ikozikov.service.controller;

import com.ikozikov.service.model.Scenario;
import com.ikozikov.service.service.ScenarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
}
