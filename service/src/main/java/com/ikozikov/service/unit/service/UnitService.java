package com.ikozikov.service.unit.service;

import com.ikozikov.service.unit.dto.UnitDto;
import com.ikozikov.service.unit.repository.UnitRepository;
import com.ikozikov.service.unit.utils.UnitUtilsMapper;
import com.ikozikov.service.unitType.repository.UnitTypeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UnitService {
  
  private final UnitRepository unitRepository;
  private final UnitTypeRepository unitTypeRepository;
  
  public List<UnitDto> getUnitsList() {
    return this.unitRepository.findAll()
        .stream()
        .map(UnitUtilsMapper::modelToDto)
        .toList();
  }

  public UnitDto getUnitsById(Long unitId) {
    return this.unitRepository.findById(unitId)
        .map(UnitUtilsMapper::modelToDto).orElseThrow();
  }
  
  public UnitDto saveUnit(UnitDto unitDto) {
    var unitModel = UnitUtilsMapper.dtoToModel(unitDto);
    
    var unitType = unitTypeRepository.findById(unitDto.getUnitType())
        .orElseThrow(() -> new EntityNotFoundException("Unit type not found"));
    unitModel.setUnitType(unitType);
    
    var savedModel = this.unitRepository.save(unitModel);
    return UnitUtilsMapper.modelToDto(savedModel);
    
  }

  public UnitDto updateUnit(Long id, UnitDto unitDto) {
    return unitRepository.findById(id)
        .map(unitModel -> {
          unitModel.setUnitName(unitDto.getUnitName());
          unitModel.setDescription(unitDto.getDescription());
          return unitRepository.save(unitModel);
        })
        .map(UnitUtilsMapper::modelToDto)
        .orElseThrow();
  }
  
  public void deleteUnits(List<Long> unitIds) {
    unitRepository.deleteAllById(unitIds);
  }
}
