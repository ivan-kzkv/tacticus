package com.ikozikov.service.unitType.controller;

import com.ikozikov.service.unitType.model.UnitTypeModel;
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
  
  @GetMapping
  public List<UnitTypeModel> getAllUnitTypes() {
    return unitTypeService.getUnitTypes();
  }

  @GetMapping("/{id}")
  public UnitTypeModel getUnitTypeById(@PathVariable("id") Long unitTypeId) {
    return unitTypeService.getUnitTypeById(unitTypeId);
  }
}
