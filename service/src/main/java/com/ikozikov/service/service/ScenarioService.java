package com.ikozikov.service.service;

import com.ikozikov.service.model.Scenario;
import com.ikozikov.service.repository.ScenarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScenarioService {
  @Autowired
  private ScenarioRepository scenarioRepository;
  
  public List<Scenario> listAllScenarios() {
    return this.scenarioRepository.findAll();
  }
}
