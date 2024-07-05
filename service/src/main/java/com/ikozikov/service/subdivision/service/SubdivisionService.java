package com.ikozikov.service.subdivision.service;

import com.ikozikov.service.subdivision.dto.SubdivisionDto;
import com.ikozikov.service.subdivision.model.SubdivisionModel;
import com.ikozikov.service.subdivision.repository.SubdivisionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubdivisionService {
  
  private final SubdivisionRepository subdivisionRepository;
  
  public List<SubdivisionModel> getAllSubdivisions() {
    return this.subdivisionRepository.findAll();
  }
  
  public SubdivisionModel getSubdivisionById(Long id) {
    return this.subdivisionRepository.findById(id).orElseThrow();
  }

  public SubdivisionModel createSubdivision(SubdivisionDto subdivisionDto) {
    return this.subdivisionRepository.save(subdivisionDto.toModel());
  }
  
  public List<SubdivisionModel> createSubdivisionFromList(List<SubdivisionDto> subdivisionDtos) {
    return this.subdivisionRepository.saveAll(subdivisionDtos.stream().map(SubdivisionDto::toModel).toList());
  }


  public SubdivisionModel updateSubdivision(Long subdivionId, SubdivisionDto subdivisionDto) {
    return this.subdivisionRepository.findById(subdivionId)
        .map(subdivision -> {
          subdivision.setName(subdivisionDto.getName());
          subdivision.setDescription(subdivisionDto.getDescription());
          subdivision.setLabel(subdivisionDto.getLabel());
          subdivision.setType(subdivisionDto.getType());
          return this.subdivisionRepository.save(subdivision);
        }).orElseThrow();
  }

  public void deleteSubdivisions(List<Long> scenarioIds) {
    this.subdivisionRepository.deleteAllById(scenarioIds);
  }
}
