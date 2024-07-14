package com.ikozikov.service.commit.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CommitDto {
  private Long id;
  private String commitName;
  private String description;
  private int stage;
  private Long previousCommit;
  private Long nextCommit;
  private Long scenario;
}
