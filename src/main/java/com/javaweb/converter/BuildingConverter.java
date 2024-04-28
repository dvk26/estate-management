package com.javaweb.converter;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.enums.District;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BuildingConverter {
    @Autowired
    private ModelMapper modelMapper;

    public BuildingSearchResponse convertBuildingSearchResponse(BuildingEntity buildingEntity) {
        BuildingSearchResponse building=modelMapper.map(buildingEntity,BuildingSearchResponse.class);
        if(buildingEntity.getDistrict()!=null)
            building.setAddress(buildingEntity.getStreet()+","+buildingEntity.getWard()+","+ District.type().get(buildingEntity.getDistrict()));
        if(buildingEntity.getRentAreas()!=null)
            building.setRentArea(buildingEntity.getRentAreas().stream().map(s->(s.getValue()).toString()).sorted().collect(Collectors.joining(",")));
        return building;
    }

    public BuildingDTO convertBuildingDTO(BuildingEntity buildingEntity){
        BuildingDTO buildingDTO=modelMapper.map(buildingEntity,BuildingDTO.class);
        String typeCode=buildingEntity.getType();
        String[] typeCodeArr= typeCode.split(",");
        if(typeCodeArr.length>0){
            List<String> typeCodeList=new ArrayList<>();
            Arrays.stream(typeCodeArr).forEach(typeCodeList::add);
            buildingDTO.setTypeCode(typeCodeList);
        }
        String rentArea = buildingEntity.getRentAreas().stream().map(s->s.getValue().toString()).sorted().collect(Collectors.joining(","));
        buildingDTO.setRentArea(rentArea);
        return buildingDTO;
    }

    public BuildingEntity convertBuildingEntity(BuildingDTO buildingDTO){
        BuildingEntity buildingEntity=modelMapper.map(buildingDTO,BuildingEntity.class);
        buildingEntity.setType(buildingDTO.getTypeCode().stream().collect(Collectors.joining(",")));
        return buildingEntity;
    }
}
