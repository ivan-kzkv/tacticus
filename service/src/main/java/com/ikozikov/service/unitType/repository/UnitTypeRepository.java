package com.ikozikov.service.unitType.repository;

import com.ikozikov.service.unitType.model.UnitTypeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitTypeRepository extends JpaRepository<UnitTypeModel, Long> {
}
