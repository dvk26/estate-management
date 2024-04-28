package com.javaweb.service.impl;

import com.javaweb.converter.BuildingConverter;
import com.javaweb.converter.RentAreaConverter;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.BuildingService;
import com.javaweb.utils.UploadFileUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.domain.Pageable;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class BuildingServiceImpl implements BuildingService {

    @Autowired
    private BuildingRepository buildingRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RentAreaRepository rentAreaRepository;
    @Autowired
    private UploadFileUtils uploadFileUtils;
    @Autowired
    private RentAreaConverter rentAreaConverter;



    @Override
    public ResponseDTO listStaffs() {
        return null;
    }

    @Autowired
    private BuildingConverter buildingCoverter;
    @Override
    public List<BuildingSearchResponse> findAll(BuildingSearchRequest buildingSearchRequest) {
        List<BuildingEntity> buildingList=buildingRepository.findAll(buildingSearchRequest);
        List<BuildingSearchResponse> result= new ArrayList<>();
        for(BuildingEntity item: buildingList){
            result.add(buildingCoverter.convertBuildingSearchResponse(item));

        }
        return result;
    }

    @Override
    public List<BuildingSearchResponse> findDividedAll(BuildingSearchRequest buildingSearchRequest, Pageable pageable) {
        List<BuildingEntity> buildingList=buildingRepository.findDevidedAll(buildingSearchRequest,pageable);
        List<BuildingSearchResponse> result= new ArrayList<>();
        for(BuildingEntity item: buildingList){
            result.add(buildingCoverter.convertBuildingSearchResponse(item));
        }
        return result;
    }

    @Override
    public BuildingDTO findById(Long id) {
        BuildingEntity buildingEntity= buildingRepository.findById(id).get();
        return buildingCoverter.convertBuildingDTO(buildingEntity);
    }

    @Override
    public ResponseDTO listStaffs(Long buildingId) {
        System.out.println("da vao duoc ham listStaffs");
        List<UserEntity> staffs=userRepository.findByStatusAndRoles_Code(1,"STAFF");
        List<UserEntity> assignedStaffs= userRepository.getAllBuildingAssignedStaff(buildingId);
        List<StaffResponseDTO> staffResponseDTOS = new ArrayList<>();
        ResponseDTO responseDTO = new ResponseDTO();
        for(UserEntity it: staffs){
            StaffResponseDTO staffResponseDTO=new StaffResponseDTO();
            staffResponseDTO.setFullName(it.getFullName());
            staffResponseDTO.setStaffId(it.getId());
            if(assignedStaffs.contains(it)){
                staffResponseDTO.setChecked("checked");
            }
            else staffResponseDTO.setChecked("");
            staffResponseDTOS.add(staffResponseDTO);
        }
        responseDTO.setData(staffResponseDTOS);
        responseDTO.setMessage("success");
        return responseDTO;
    }

    @Transactional
    @Override
    public void updateAssignmentBuilding(AssignmentBuildingDTO assignmentBuildingDTO){
        //update assignmentbuilding
        BuildingEntity building=buildingRepository.findById(assignmentBuildingDTO.getBuildingId()).get();
        List<UserEntity> users=userRepository.findByIdIn(assignmentBuildingDTO.getStaffs());
        building.setUsers(users);
        buildingRepository.saveAndFlush(building);

    }

    @Override
    @Transactional
    public void addOrUpdateBuilding(BuildingDTO buildingDTO)  {
        //Add or update building
        BuildingEntity newBuildingEntity= buildingCoverter.convertBuildingEntity(buildingDTO);
        List<RentAreaEntity> rentAreaEntities= rentAreaConverter.toRentAreaEntityList(buildingDTO.getRentArea(),newBuildingEntity);
        newBuildingEntity.setRentAreas(rentAreaEntities);
        if(buildingDTO.getId()!=null){
            BuildingEntity foundBuildingEntity=buildingRepository.findById(buildingDTO.getId()).get();
            newBuildingEntity.setImage(foundBuildingEntity.getImage());
        }
        saveThumbnail(buildingDTO,newBuildingEntity);
        buildingRepository.save(newBuildingEntity);
    }
    @Override
    @Transactional
    public void deleteBuildings(List<Long> ids) {
        buildingRepository.deleteByIdIn(ids);
    }

    @Override
    public int countTotalItems(BuildingSearchRequest buildingSearchRequest) {
        return buildingRepository.countTotalItems(buildingSearchRequest);
    }

    @Override
    public void saveThumbnail(BuildingDTO buildingDTO, BuildingEntity buildingEntity) {
        String path="/building/"+buildingDTO.getImageName();
        if(buildingDTO.getImageName()!=null){
            if(buildingEntity.getImage()!=""){
                File file=new File("C://home/office/"+buildingEntity.getImage());
                file.delete();
            }

            byte[] bytes = Base64.decodeBase64(buildingDTO.getImageBase64().getBytes());
            uploadFileUtils.writeOrUpdate(path,bytes);
            System.out.println(bytes.length);
            buildingEntity.setImage(path);
        }



    }



}