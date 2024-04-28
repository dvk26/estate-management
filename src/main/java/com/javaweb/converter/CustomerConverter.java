package com.javaweb.converter;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.enums.District;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.CustomerSearchResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CustomerConverter {
    @Autowired
    private ModelMapper modelMapper;

    public CustomerSearchResponse convertBuildingSearchResponse(CustomerEntity customerEntity) {
        CustomerSearchResponse customer=modelMapper.map(customerEntity,CustomerSearchResponse.class);
        return customer;
    }

    public CustomerEntity convertToCustomerEntity(CustomerDTO customerDTO){
        CustomerEntity customer=modelMapper.map(customerDTO,CustomerEntity.class);
        return customer;
    }

    public CustomerDTO convertToCustomerDTO(CustomerEntity customerEntity){
        CustomerDTO customerDTO=modelMapper.map(customerEntity,CustomerDTO.class);
        return customerDTO;
    }
}
