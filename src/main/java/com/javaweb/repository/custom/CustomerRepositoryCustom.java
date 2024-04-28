package com.javaweb.repository.custom;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.request.CustomerSearchRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerRepositoryCustom {
    public List<CustomerEntity> findAll(CustomerSearchRequest customerSearchRequest);
    public List<CustomerEntity> findDevidedAll(CustomerSearchRequest customerSearchRequest, Pageable pageable);
    public int countTotalItems(CustomerSearchRequest customerSearchRequest);
}