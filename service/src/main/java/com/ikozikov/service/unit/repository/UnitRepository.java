package com.ikozikov.service.unit.repository;

import com.ikozikov.service.unit.model.UnitModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitRepository extends JpaRepository<UnitModel, Long> {
}
