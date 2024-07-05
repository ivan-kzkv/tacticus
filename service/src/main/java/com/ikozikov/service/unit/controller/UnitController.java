package com.ikozikov.service.unit.controller;

import com.ikozikov.service.unit.dto.UnitDto;
import com.ikozikov.service.unit.service.UnitService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UnitController {
  private final UnitService unitService;
  
  @GetMapping("/unit")
  public List<UnitDto> getUnits() {
    return unitService.getUnitsList();
  }
  
  @GetMapping("/unit/{id}")
  public UnitDto getUnitById(@PathVariable Long id) {
    return unitService.getUnitsById(id);
  }
  
  @PostMapping("/unit")
  public UnitDto createUnit(@RequestBody UnitDto unitDto) {
    return unitService.saveUnit(unitDto);
  }
}
