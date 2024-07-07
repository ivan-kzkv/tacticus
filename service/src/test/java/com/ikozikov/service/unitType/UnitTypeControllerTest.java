package com.ikozikov.service.unitType;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UnitTypeControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @Test
  public void testGetAllUnitTypes() throws Exception {
    mockMvc.perform(get("/unit-type"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(3)))
        .andExpect(jsonPath("$[0].typeName", is("бійці")))
        .andExpect(jsonPath("$[1].typeName", is("групова зброя")))
        .andExpect(jsonPath("$[2].typeName", is("техніка")));
  }
  
  @Test
  public void testGetUnitTypeById() throws Exception {
    mockMvc.perform(get("/unit-type/1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.typeName", is("бійці")))
        .andExpect(jsonPath("$.description", is("окремі бійці, як елементи складу груп та взводів. Як приклад стрілець, гранатометник, кулеметник і т.д.")))
        .andExpect(jsonPath("$.iconName", is("humans.svg")));
  }
}
