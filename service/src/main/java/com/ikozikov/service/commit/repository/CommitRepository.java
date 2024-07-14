package com.ikozikov.service.commit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ikozikov.service.commit.model.CommitModel;


@Repository
public interface CommitRepository extends JpaRepository<CommitModel, Long> {
    @Query(value = "WITH RECURSIVE commit_chain AS (" +
            "  SELECT c.commit_id, c.commit_name, c.description, c.stage, c.previous_commit_id, c.next_commit_id, c.scenario_id " +
            "  FROM commit c " +
            "  WHERE c.commit_id = :startCommitId " +
            "  UNION ALL " +
            "  SELECT c.commit_id, c.commit_name, c.description, c.stage, c.previous_commit_id, c.next_commit_id, c.scenario_id " +
            "  FROM commit c " +
            "  INNER JOIN commit_chain cc ON cc.next_commit_id = c.commit_id" +
            ") " +
            "SELECT * FROM commit_chain", nativeQuery = true)
    List<CommitModel> findCommitChain(@Param("startCommitId") Long startCommitId);

    List<CommitModel> findByScenarioIdOrderByStageAsc(Long scenarioId);
}
