package com.ikozikov.service.commit.model;

import com.ikozikov.service.scenario.model.Scenario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "commit")
@Builder
@NoArgsConstructor
@Data
@AllArgsConstructor
public class CommitModel {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "commit_id")
  private Long id;

  @Column(name = "commit_name", nullable = false, length = 50)
  private String commitName;

  @Column(name = "description", length = 256)
  private String description;

  @Column(name = "stage", nullable = false)
  private int stage;

  @ManyToOne
  @JoinColumn(name = "previous_commit_id")
  private CommitModel previousCommit;

  @ManyToOne
  @JoinColumn(name = "next_commit_id")
  private CommitModel nextCommit;

  @ManyToOne
  @JoinColumn(name = "scenario_id", nullable = false)
  private Scenario scenario;
}

