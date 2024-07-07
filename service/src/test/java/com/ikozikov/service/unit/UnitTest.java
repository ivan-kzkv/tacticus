package com.ikozikov.service.unit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ikozikov.service.unit.dto.UnitDto;
import com.ikozikov.service.unit.model.UnitModel;
import com.ikozikov.service.unit.repository.UnitRepository;
import com.ikozikov.service.unit.utils.UnitUtilsMapper;
import com.ikozikov.service.unitType.repository.UnitTypeRepository;
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

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UnitTest {
  @Autowired
  private MockMvc mockMvc;
  
  @Autowired
  private UnitRepository unitRepository;
  
  @Autowired
  private ObjectMapper objectMapper;


  @Autowired
  private UnitTypeRepository unitTypeRepository;
  
  
  @BeforeEach
  public void setUp() {
    unitRepository.deleteAll();
    
    setUnitInRepository();
  }
  
  @Test
  public void testGetAllUnits() throws Exception {
    this.mockMvc.perform(get("/unit"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(2)))
        .andExpect(jsonPath("$[0].unitName", is("Test Unit 1")))
        .andExpect(jsonPath("$[0].description", is("Unit 1 for testing purpose")))
        .andExpect(jsonPath("$[0].unitIcon", is("unit_1.svg")))
        .andExpect(jsonPath("$[0].unitType", is(1)))
        .andExpect(jsonPath("$[1].unitName", is("Test Unit 2")))
        .andExpect(jsonPath("$[1].description", is("Unit 2 for testing purpose")))
        .andExpect(jsonPath("$[1].unitIcon", is("unit_2.svg")))
        .andExpect(jsonPath("$[1].unitType", is(2)));
  }
  
  @Test
  public void testGetUnitById() throws Exception {
    var unit = unitRepository.findAll().getFirst();
    
    this.mockMvc.perform(get("/unit/" + unit.getId()))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.unitName", is("Test Unit 1")))
        .andExpect(jsonPath("$.description", is("Unit 1 for testing purpose")))
        .andExpect(jsonPath("$.unitIcon", is("unit_1.svg")))
        .andExpect(jsonPath("$.unitType", is(1)));
  }
  
  @Test
  public void testCreateUnit() throws Exception {
    var newUnit = UnitDto.builder()
        .unitName("Test Unit 3")
        .description("Unit 3 for testing purpose")
        .unitIcon("unit_3.svg")
        .amount(4)
        .unitType(3L)
        .build();

    this.mockMvc.perform(post("/unit")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(newUnit)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.unitName", is("Test Unit 3")))
        .andExpect(jsonPath("$.description", is("Unit 3 for testing purpose")))
        .andExpect(jsonPath("$.unitIcon", is("unit_3.svg")))
        .andExpect(jsonPath("$.amount", is(4)))
        .andExpect(jsonPath("$.unitType", is(3)));
    
    var createdUnit = unitRepository.findAll().get(2);

    Assertions.assertEquals(createdUnit.getUnitName(), "Test Unit 3");
    Assertions.assertEquals(createdUnit.getUnitType().getId(), 3);
    Assertions.assertEquals(createdUnit.getUnitType().getType_name(), "техніка");
  }

  @Test
  public void testUpdateUnit() throws Exception {
    var oldUnit = unitRepository.findAll().get(1);
    
    var dto = UnitUtilsMapper.modelToDto(oldUnit);
    dto.setUnitName("updated unit 2 name");

    this.mockMvc.perform(patch("/unit/" + oldUnit.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(dto)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.unitName", is("updated unit 2 name")))
        .andExpect(jsonPath("$.description", is("Unit 2 for testing purpose")))
        .andExpect(jsonPath("$.unitIcon", is("unit_2.svg")))
        .andExpect(jsonPath("$.amount", is(2)))
        .andExpect(jsonPath("$.unitType", is(2)));
    
    unitRepository.findById(oldUnit.getId())
        .map(unitModel -> {
              Assertions.assertEquals(unitModel.getUnitName(), "updated unit 2 name");
              Assertions.assertEquals(unitModel.getUnitType().getId(), 2);
              Assertions.assertEquals(unitModel.getUnitType().getType_name(), "групова зброя");
              Assertions.assertEquals(unitModel.getAmount(), 2);
              return "";
            }).orElseThrow();
  }


  @Test
  public void testDeleteUnit() throws Exception {
    var units = unitRepository.findAll().stream().map(UnitModel::getId).toList();

    this.mockMvc.perform(delete("/unit").contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(units)))
        .andExpect(status().isNoContent());
    
    Assertions.assertEquals(unitRepository.findAll().size(), 0);
  }
  
  private void setUnitInRepository() {
    var unit1 = UnitModel.builder()
        .unitName("Test Unit 1")
        .description("Unit 1 for testing purpose")
        .unitIcon("unit_1.svg")
        .amount(1)
        .unitType(unitTypeRepository.getById(1L))
        .build();

    var unit2 = UnitModel.builder()
        .unitName("Test Unit 2")
        .description("Unit 2 for testing purpose")
        .unitIcon("unit_2.svg")
        .amount(2)
        .unitType(unitTypeRepository.getById(2L))
        .build();
    
    unitRepository.saveAll(Arrays.asList(unit1, unit2));
  }

}
