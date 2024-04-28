package com.javaweb.repository;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.repository.custom.RentAreaCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentAreaRepository extends JpaRepository<RentAreaEntity,Long>, RentAreaCustom {
    void deleteByIdIn(List<Long> ids);
    List<RentAreaEntity> findByBuildingId(Long buildingId);

    void deleteByBuildingId(Long buildingId);
}
