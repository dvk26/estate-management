package com.javaweb.converter;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RentAreaConverter {
    public  List<RentAreaEntity> toRentAreaEntityList(String rentArea, BuildingEntity buildingEntity){
        String[] rentAreas=rentArea.split(",");
        List<RentAreaEntity> rentAreaEntities=new ArrayList<>();
        for(String item: rentAreas){
            RentAreaEntity rentAreaEntity= new RentAreaEntity();
            rentAreaEntity.setValue(Long.parseLong(item));
            rentAreaEntity.setBuilding(buildingEntity);
            rentAreaEntities.add(rentAreaEntity);
        }
        return rentAreaEntities;
    }
}
