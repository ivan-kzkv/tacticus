package com.ikozikov.service.unit;

import com.ikozikov.service.subdivision.model.SubdivisionLabel;
import com.ikozikov.service.subdivision.model.SubdivisionModel;
import com.ikozikov.service.subdivision.repository.SubdivisionRepository;
import com.ikozikov.service.unit.model.UnitModel;
import com.ikozikov.service.unit.repository.UnitRepository;
import com.ikozikov.service.unitType.repository.UnitTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
  private UnitTypeRepository unitTypeRepository;
  
  @Autowired
  private SubdivisionRepository subdivisionRepository;
  
  @BeforeEach
  public void setUp() {
    subdivisionRepository.deleteAll();
    unitRepository.deleteAll();
    
    setUnitInRepository();
    setSubdivisionInRepository();
    
  }
  
  @Test
  public void testGetAllUnits() throws Exception {
    this.mockMvc.perform(get("/unit"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(2)));
  }
  

  private void setSubdivisionInRepository() {
    var subdivision = SubdivisionModel.builder()
        .name("subdivision 1")
        .description("Subdivision 1 for test")
//        .label(SubdivisionLabel.WHITE)
        .type(SubdivisionLabel.WHITE)
        .build();
    
    subdivisionRepository.save(subdivision);
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
