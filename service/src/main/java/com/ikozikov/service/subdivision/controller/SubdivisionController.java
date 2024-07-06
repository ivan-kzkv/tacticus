package com.ikozikov.service.subdivision.controller;

import com.ikozikov.service.subdivision.dto.SubdivisionDto;
import com.ikozikov.service.subdivision.service.SubdivisionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SubdivisionController {
  private final SubdivisionService subdivisionService;
  
  @GetMapping("/subdivision")
  public List<SubdivisionDto> getAllSubdivisions() {
    return this.subdivisionService.getAllSubdivisions();
  }
  
  @GetMapping("/subdivision/{id}")
  public SubdivisionDto getSubdivisionById(@PathVariable("id") Long subdivisionId) {
    return this.subdivisionService.getSubdivisionById(subdivisionId);
  }
  
  @PostMapping("/subdivision")
  @ResponseStatus(HttpStatus.CREATED)
  public SubdivisionDto createSubdivision(@RequestBody SubdivisionDto subdivisionDto) {
    return this.subdivisionService.createSubdivision(subdivisionDto);
  }
  
  @PatchMapping("/subdivision/{id}")
  public SubdivisionDto updateSubdivision(@PathVariable("id") Long subdivisionId, @RequestBody SubdivisionDto subdivisionDto) {
    return this.subdivisionService.updateSubdivision(subdivisionId, subdivisionDto);
  }
  
  @DeleteMapping("/subdivision")
  public void deleteSubdivisions(@RequestBody List<Long> subdivisionsIds) {
    this.subdivisionService.deleteSubdivisions(subdivisionsIds);
  }
}
