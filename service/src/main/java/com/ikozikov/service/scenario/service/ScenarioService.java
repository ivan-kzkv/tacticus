package com.ikozikov.service.scenario.service;

import com.ikozikov.service.scenario.dto.ScenarioDto;
import com.ikozikov.service.scenario.model.Scenario;
import com.ikozikov.service.scenario.repository.ScenarioRepository;
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
  
  public Scenario createScenario(ScenarioDto scenarioDto) {
    var scenario = new Scenario();
    scenario.setDate_created(new Date());
    scenario.setDate_modified(new Date());
    scenario.setName(scenarioDto.getName());
    scenario.setDescription(scenarioDto.getDescription());
    return this.scenarioRepository.save(scenario);
  }
  
  public List<Scenario> createScenarioFromList(List<Scenario> scenarios) {
    return this.scenarioRepository.saveAll(scenarios);
  }
  
  public Scenario updateScenario(Long scenarioId, ScenarioDto scenarioDto) {
    return this.scenarioRepository.findById(scenarioId)
        .map(scenario -> {
          scenario.setName(scenarioDto.getName());
          scenario.setDate_modified(new Date());
          scenario.setDescription(scenarioDto.getDescription());
          return this.scenarioRepository.save(scenario);
        }).orElseThrow();
  }
  
  public void deleteScenario(List<Long> scenarioIds) {
    this.scenarioRepository.deleteAllById(scenarioIds);
  }
}
