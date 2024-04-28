package com.javaweb.service;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.ResponseDTO;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface BuildingService {
    public ResponseDTO listStaffs();
    public List<BuildingSearchResponse> findAll(BuildingSearchRequest buildingSearchRequest);

    public List<BuildingSearchResponse> findDividedAll(BuildingSearchRequest buildingSearchRequest, Pageable pageable);

    public BuildingDTO findById(Long id);

    public ResponseDTO listStaffs(Long buildingId);
    public void updateAssignmentBuilding(AssignmentBuildingDTO assignmentBuildingDTO);

    public void addOrUpdateBuilding(BuildingDTO buildingDTO);
    public void deleteBuildings(List<Long> ids);
    public int countTotalItems(BuildingSearchRequest buildingSearchRequest);

    public void saveThumbnail(BuildingDTO buildingDTO,BuildingEntity buildingEntity);


}

