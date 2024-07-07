package com.ikozikov.service.unitType.service;

import com.ikozikov.service.unitType.dto.UnitTypeDto;
import com.ikozikov.service.unitType.model.UnitTypeModel;
import com.ikozikov.service.unitType.repository.UnitTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UnitTypeService {
  private final UnitTypeRepository unitTypeRepository;
  
  public List<UnitTypeDto> getUnitTypes() {
    return this.unitTypeRepository.findAll().stream().map(UnitTypeDto::modelToDto).toList();
  }
  
  public UnitTypeDto getUnitTypeById(Long unitTypeId) {
    return this.unitTypeRepository.findById(unitTypeId).map(UnitTypeDto::modelToDto).orElseThrow();
  }
}
