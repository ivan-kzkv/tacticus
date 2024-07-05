package com.ikozikov.service.subdivision.repository;

import com.ikozikov.service.subdivision.model.SubdivisionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubdivisionRepository extends JpaRepository<SubdivisionModel, Long> {
}
