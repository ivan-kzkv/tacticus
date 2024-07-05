package com.ikozikov.service.unitType.service;

import com.ikozikov.service.unitType.model.UnitTypeModel;
import com.ikozikov.service.unitType.repository.UnitTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UnitTypeService {
  private final UnitTypeRepository unitTypeRepository;
  
  public List<UnitTypeModel> getUnitTypes() {
    return this.unitTypeRepository.findAll();
  }
  
  public UnitTypeModel getUnitTypeById(Long unitTypeId) {
    return this.unitTypeRepository.findById(unitTypeId).orElseThrow();
  }
}
