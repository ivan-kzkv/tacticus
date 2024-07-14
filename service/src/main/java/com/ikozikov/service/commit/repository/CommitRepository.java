package com.ikozikov.service.commit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ikozikov.service.commit.model.CommitModel;

@Repository
public interface CommitRepository extends JpaRepository<CommitModel, Long> {}
