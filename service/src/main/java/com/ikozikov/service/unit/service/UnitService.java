package com.ikozikov.service.unit.service;

import com.ikozikov.service.subdivision.repository.SubdivisionRepository;
import com.ikozikov.service.unit.dto.UnitDto;
import com.ikozikov.service.unit.repository.UnitRepository;
import com.ikozikov.service.unitType.repository.UnitTypeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UnitService {
  
  private final UnitRepository unitRepository;
  private final UnitTypeRepository unitTypeRepository;
  private final SubdivisionRepository subdivisionRepository;
  
  public List<UnitDto> getUnitsList() {
    return this.unitRepository.findAll()
        .stream()
        .map(UnitDto::toDto)
        .toList();
  }

  public UnitDto getUnitsById(Long unitId) {
    return this.unitRepository.findById(unitId)
        .map(UnitDto::toDto).orElseThrow();
  }
  
  public UnitDto saveUnit(UnitDto unitDto) {
    var unitModel = UnitDto.toModel(unitDto);
    
    var unitType = unitTypeRepository.findById(unitDto.getUnitType())
        .orElseThrow(() -> new EntityNotFoundException("Unit type not found"));
    unitModel.setUnitType(unitType);
    
    var subdivision = Optional.ofNullable(unitDto.getSubdivision())
        .flatMap(subdivisionRepository::findById)
        .get();
    unitModel.setSubdivision(subdivision);
    
    var savedModel = this.unitRepository.save(unitModel);
    return UnitDto.toDto(savedModel);
    
  }
}
