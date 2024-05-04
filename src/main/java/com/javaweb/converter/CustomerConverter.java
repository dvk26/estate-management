package com.javaweb.converter;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.enums.CustomerStatus;
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
        if (customerEntity.getStatus()!=null) customer.setStatus(CustomerStatus.type().get(customerEntity.getStatus()));
        return customer;
    }

    public void convertToCustomerEntity(CustomerEntity customerEntity,CustomerDTO customerDTO){
        customerEntity.setPhone(customerDTO.getPhone());
        customerEntity.setStatus(customerDTO.getStatus());
        customerEntity.setFullName(customerDTO.getFullName());
        customerEntity.setEmail(customerDTO.getEmail());
        customerEntity.setDemand(customerDTO.getDemand());
        if(customerEntity.getStatus()==null) customerEntity.setStatus("CHUA_XU_LI");
    }

    public CustomerDTO convertToCustomerDTO(CustomerEntity customerEntity){
        CustomerDTO customerDTO=modelMapper.map(customerEntity,CustomerDTO.class);
        return customerDTO;
    }

    public CustomerEntity convertCustomerEntity(CustomerDTO customerDTO){
        CustomerEntity customerEntity=modelMapper.map(customerDTO,CustomerEntity.class);
        if(customerEntity.getStatus()==null) customerEntity.setStatus("CHUA_XU_LI");
        customerEntity.setIsActive(1);
        return customerEntity;
    }
}
