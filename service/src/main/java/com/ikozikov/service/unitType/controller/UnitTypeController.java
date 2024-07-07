package com.ikozikov.service.unitType.controller;

import com.ikozikov.service.unitType.dto.UnitTypeDto;
import com.ikozikov.service.unitType.service.UnitTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/unit-type")
@RequiredArgsConstructor
public class UnitTypeController {
  
  private final UnitTypeService unitTypeService;
  
  @GetMapping("/unit-type")
  public List<UnitTypeDto> getAllUnitTypes() {
    return unitTypeService.getUnitTypes();
  }

  @GetMapping("/unit-type/{id}")
  public UnitTypeDto getUnitTypeById(@PathVariable("id") Long unitTypeId) {
    return unitTypeService.getUnitTypeById(unitTypeId);
  }
}
