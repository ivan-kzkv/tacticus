package com.ikozikov.service.scenario;

import com.ikozikov.service.scenario.model.Scenario;
import com.ikozikov.service.scenario.repository.ScenarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ScenarioTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ScenarioRepository scenarioRepository;


  @BeforeEach
  public void setUp() {
    scenarioRepository.deleteAll();

    Scenario scenario1 = new Scenario();
    scenario1.setName("Scenario 1");
    scenario1.setDescription("Description for Scenario 1");

    Scenario scenario2 = new Scenario();
    scenario2.setName("Scenario 2");
    scenario2.setDescription("Description for Scenario 2");

    scenarioRepository.saveAll(Arrays.asList(scenario1, scenario2));
  }

  @Test
  public void testGetAllScenarios() throws Exception {
    this.mockMvc.perform(get("/scenario"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(2)))
        .andExpect(jsonPath("$[0].name", is("Scenario 1")))
        .andExpect(jsonPath("$[0].description", is("Description for Scenario 1")))
        .andExpect(jsonPath("$[1].name", is("Scenario 2")))
        .andExpect(jsonPath("$[1].description", is("Description for Scenario 2")));
  }
}
