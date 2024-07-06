package com.ikozikov.service.subdivision;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ikozikov.service.shared.utils.TroopsSide;
import com.ikozikov.service.subdivision.dto.SubdivisionDto;
import com.ikozikov.service.subdivision.model.SubdivisionLabel;
import com.ikozikov.service.subdivision.model.SubdivisionModel;
import com.ikozikov.service.subdivision.repository.SubdivisionRepository;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class SubdivisionControllerTest {
  @Autowired
  private MockMvc mockMvc;
  
  @Autowired
  private SubdivisionRepository subdivisionRepository;

  @Autowired
  private ObjectMapper objectMapper;
  
  @BeforeEach
  public void setUp() {
    subdivisionRepository.deleteAll();
    
    var subdivision1 = SubdivisionModel.builder()
        .description("Description subdivision 1")
        .name("Subdivision_1")
        .label(SubdivisionLabel.WHITE)
        .type(TroopsSide.FRIENDLY)
        .build();
    
    var subdivision2 = SubdivisionModel.builder()
        .description("Description subdivision 2")
        .name("Subdivision_1")
        .label(SubdivisionLabel.GREY)
        .type(TroopsSide.ENEMY)
        .build();
    
    subdivisionRepository.saveAll(Arrays.asList(subdivision1, subdivision2));
  }
  
  @Test
  public void getAllSubdivisions() throws Exception {
    this.mockMvc.perform(get("/subdivision"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(2)))
        .andExpect(jsonPath("$[0].name", is("Subdivision_1")))
        .andExpect(jsonPath("$[0].description", is("Description subdivision 1")))
        .andExpect(jsonPath("$[0].label", is(SubdivisionLabel.WHITE.toString())))
        .andExpect(jsonPath("$[0].type", is(TroopsSide.FRIENDLY.toString())))
        .andExpect(jsonPath("$[1].name", is("Subdivision_1")))
        .andExpect(jsonPath("$[1].label", is(SubdivisionLabel.GREY.toString())))
        .andExpect(jsonPath("$[1].type", is(TroopsSide.ENEMY.toString())))
        .andExpect(jsonPath("$[1].description", is("Description subdivision 2")));
  }

  @Test
  public void getSubdivisionById() throws Exception {
    var subdivision = subdivisionRepository.findAll().getFirst();
    this.mockMvc.perform(get("/subdivision/" + subdivision.getId()))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name", is("Subdivision_1")))
        .andExpect(jsonPath("$.description", is("Description subdivision 1")))
        .andExpect(jsonPath("$.label", is(SubdivisionLabel.WHITE.toString())))
        .andExpect(jsonPath("$.type", is(TroopsSide.FRIENDLY.toString())));
  }

  @Test
  public void saveSubdivision() throws Exception {
    var subdivisionDto = SubdivisionDto.builder()
        .description("Description subdivision 3")
        .name("Subdivision_3")
        .label(SubdivisionLabel.PURPLE)
        .type(TroopsSide.NEUTRAL)
        .build();

    this.mockMvc.perform(post("/subdivision")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(subdivisionDto)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.name", is("Subdivision_3")))
        .andExpect(jsonPath("$.description", is("Description subdivision 3")))
        .andExpect(jsonPath("$.label", is(SubdivisionLabel.PURPLE.toString())))
        .andExpect(jsonPath("$.type", is(TroopsSide.NEUTRAL.toString())));
    
    
    var subdivisions = subdivisionRepository.findAll();
    Assertions.assertEquals(subdivisions.size(), 3);
        
  }
  
  @Test
  public void updateSubdivision() throws Exception {
    var oldSubdivision = subdivisionRepository.findAll().getFirst();
    var newSubdivisionDto = SubdivisionDto.builder()
        .description("Description updated")
        .name(oldSubdivision.getName())
        .label(oldSubdivision.getLabel())
        .type(TroopsSide.UNKNOWN)
        .build();
    
    
    this.mockMvc.perform(patch("/subdivision/" + oldSubdivision.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(newSubdivisionDto)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", is(oldSubdivision.getId().intValue())))
        .andExpect(jsonPath("$.name", is(oldSubdivision.getName())))
        .andExpect(jsonPath("$.description", is("Description updated")))
        .andExpect(jsonPath("$.label", is(oldSubdivision.getLabel().toString())))
        .andExpect(jsonPath("$.type", is(TroopsSide.UNKNOWN.toString())));
    
    var newSubdivision = subdivisionRepository.findById(oldSubdivision.getId()).get();
    
    Assertions.assertEquals(newSubdivision.getDescription(), newSubdivisionDto.getDescription());
    Assertions.assertEquals(newSubdivision.getType(), newSubdivisionDto.getType());
  }

  @Test
  public void deleteSubdivision() throws Exception {
    var subdivisionsIds = subdivisionRepository.findAll().stream().map(SubdivisionModel::getId).toList();
    this.mockMvc.perform(delete("/subdivision")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(subdivisionsIds)))
        .andExpect(status().isOk());
    Assertions.assertEquals(subdivisionRepository.findAll().size(), 0);
  }
}
