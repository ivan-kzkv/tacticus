package com.ikozikov.service.service;

import com.ikozikov.service.model.Scenario;
import com.ikozikov.service.repository.ScenarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ScenarioService {
  @Autowired
  private ScenarioRepository scenarioRepository;
  
  public List<Scenario> listAllScenarios() {
    return this.scenarioRepository.findAll();
  }
  
  public Scenario getOneScenario(Long scenarioId) {
    return this.scenarioRepository.findById(scenarioId).orElseThrow();
  }
  
  public Scenario createScenario(Scenario scenario) {
    return this.scenarioRepository.save(scenario);
  }
  
  public List<Scenario> createScenarioFromList(List<Scenario> scenarios) {
    return this.scenarioRepository.saveAll(scenarios);
  }
  
  public Scenario updateScenario(Scenario newScenario) {
    return this.scenarioRepository.findById(newScenario.getId())
        .map(scenario -> {
          scenario.setName(newScenario.getName());
          scenario.setDate_modified(new Date());
          return this.scenarioRepository.save(scenario);
        }).orElseThrow();
  }
  
  public void deleteScenario(List<Scenario> scenarios) {
    this.scenarioRepository.deleteAll(scenarios);
  }
}
