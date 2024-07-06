package com.ikozikov.service.subdivision.service;

import com.ikozikov.service.subdivision.dto.SubdivisionDto;
import com.ikozikov.service.subdivision.model.SubdivisionModel;
import com.ikozikov.service.subdivision.repository.SubdivisionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubdivisionService {
  
  private final SubdivisionRepository subdivisionRepository;
  
  public List<SubdivisionDto> getAllSubdivisions() {
    return subdivisionRepository.findAll().stream().map(SubdivisionDto::toDto).toList();
  }
  
  public SubdivisionDto getSubdivisionById(Long id) {
    return subdivisionRepository.findById(id).map(SubdivisionDto::toDto).orElseThrow();
  }

  public SubdivisionDto createSubdivision(SubdivisionDto subdivisionDto) {
    return Optional.of(subdivisionDto)
        .map(SubdivisionModel::toModel)
        .map(subdivisionRepository::save)
        .map(SubdivisionDto::toDto)
        .orElseThrow();
  }
  
  public List<SubdivisionDto> createSubdivisionFromList(List<SubdivisionDto> subdivisionDtos) {
    return subdivisionRepository.saveAll(subdivisionDtos.stream().map(SubdivisionModel::toModel).toList())
        .stream().map(SubdivisionDto::toDto)
        .toList();
  }


  public SubdivisionDto updateSubdivision(Long subdivionId, SubdivisionDto subdivisionDto) {
    return this.subdivisionRepository.findById(subdivionId)
        .map(subdivision -> {
          subdivision.setName(subdivisionDto.getName());
          subdivision.setDescription(subdivisionDto.getDescription());
          subdivision.setLabel(subdivisionDto.getLabel());
          subdivision.setType(subdivisionDto.getType());
          return this.subdivisionRepository.save(subdivision);
        })
        .map(SubdivisionDto::toDto).orElseThrow();
  }

  public void deleteSubdivisions(List<Long> scenarioIds) {
    this.subdivisionRepository.deleteAllById(scenarioIds);
  }
}
