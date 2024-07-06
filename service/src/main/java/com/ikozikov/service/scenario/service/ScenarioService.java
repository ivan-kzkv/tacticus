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
  
  public List<ScenarioDto> listAllScenarios() {
    return this.scenarioRepository.findAll().stream()
        .map(ScenarioDto::toDto)
        .toList();
  }
  
  public ScenarioDto getOneScenario(Long scenarioId) {
    return this.scenarioRepository.findById(scenarioId)
        .map(ScenarioDto::toDto)
        .orElseThrow();
  }
  
  public ScenarioDto createScenario(ScenarioDto scenarioDto) {
    var scenario = new Scenario();
    scenario.setDate_created(new Date());
    scenario.setDate_modified(new Date());
    scenario.setName(scenarioDto.getName());
    scenario.setDescription(scenarioDto.getDescription());
    return ScenarioDto.toDto(this.scenarioRepository.save(scenario));
  }
  
  public List<ScenarioDto> createScenarioFromList(List<ScenarioDto> scenarios) {
    return this.scenarioRepository.saveAll(scenarios.stream().map(Scenario::toModel).toList())
        .stream()
        .map(ScenarioDto::toDto)
        .toList();
  }
  
  public ScenarioDto updateScenario(Long scenarioId, ScenarioDto scenarioDto) {
    return this.scenarioRepository.findById(scenarioId)
        .map(scenario -> {
          scenario.setName(scenarioDto.getName());
          scenario.setDate_modified(new Date());
          scenario.setDescription(scenarioDto.getDescription());
          return this.scenarioRepository.save(scenario);
        }).map(ScenarioDto::toDto)
        .orElseThrow();
  }
  
  public void deleteScenario(List<Long> scenarioIds) {
    this.scenarioRepository.deleteAllById(scenarioIds);
  }
}
