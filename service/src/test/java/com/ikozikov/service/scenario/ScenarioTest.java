package com.ikozikov.service.scenario;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ikozikov.service.scenario.dto.ScenarioDto;
import com.ikozikov.service.scenario.model.Scenario;
import com.ikozikov.service.scenario.repository.ScenarioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
  
  @Autowired
  private ObjectMapper objectMapper;

  @BeforeEach
  public void setUp() {
    scenarioRepository.deleteAll();

    Scenario scenario1 = new Scenario();
    scenario1.setId(1L);
    scenario1.setName("Scenario 1");
    scenario1.setDescription("Description for Scenario 1");
    scenario1.setDate_created(new Date());
    scenario1.setDate_modified(new Date());

    Scenario scenario2 = new Scenario();
    scenario2.setId(2L);
    scenario2.setName("Scenario 2");
    scenario2.setDescription("Description for Scenario 2");
    scenario2.setDate_created(new Date());
    scenario2.setDate_modified(new Date());

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
  
  @Test
  public void testGetScenarioById() throws Exception {
    var scenario = scenarioRepository.findAll().get(0);
    
    this.mockMvc.perform(get("/scenario/" + scenario.getId()))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", is(scenario.getId().intValue())))
        .andExpect(jsonPath("$.name", is(scenario.getName())))
        .andExpect(jsonPath("$.description", is(scenario.getDescription())))
        .andReturn();
  }
  
  @Test
  public void testPostScenario() throws Exception {
    var scenarioDto = ScenarioDto.builder()
        .name("Post scenario")
        .description("Post Description").build();
    
    this.mockMvc.perform(post("/scenario")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(scenarioDto)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.name", is("Post scenario")))
        .andExpect(jsonPath("$.description", is("Post Description")));
    
    var scenarios = scenarioRepository.findAll();
    Assertions.assertEquals(scenarios.size(), 3);
    var postedScenario = scenarios.get(2);
    Assertions.assertEquals(postedScenario.getName(), "Post scenario");
    Assertions.assertEquals(postedScenario.getDescription(), "Post Description");
  }

  @Test
  public void testPatchScenario() throws Exception {
    var scenario = scenarioRepository.findAll().get(0);

    var scenarioDto = ScenarioDto.builder()
        .name(scenario.getName())
        .description("Updated Description")
        .build();
    Thread.sleep(1000);
    this.mockMvc.perform(patch("/scenario/" + scenario.getId())
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(scenarioDto)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", is(scenario.getId().intValue())))
        .andExpect(jsonPath("$.name", is(scenario.getName())))
        .andExpect(jsonPath("$.description", is("Updated Description")));


    var scenarioUpdated = scenarioRepository.findById(scenario.getId()).get();
    
    Assertions.assertEquals(scenario.getDate_created().getTime(), scenarioUpdated.getDate_created().getTime());
    Assertions.assertTrue(scenario.getDate_modified().getTime() < scenarioUpdated.getDate_modified().getTime());
    
  }

  @Test
  public void testDeleteScenario() throws Exception {
    this.mockMvc.perform(delete("/scenario")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(scenarioRepository.findAll().stream().map(Scenario::getId).toList())))
        .andExpect(status().isOk());

    Assertions.assertEquals(scenarioRepository.findAll().size(), 0);
  }
}
