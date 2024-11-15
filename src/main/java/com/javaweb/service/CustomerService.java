package com.javaweb.service;


import com.javaweb.model.dto.AssignmentCustomerDTO;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.CustomerSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StaffResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerService {
    public List<CustomerSearchResponse> findActiveDividedAll(CustomerSearchRequest customerSearchRequest, Pageable pageable);
    public int countTotalActiveItems(CustomerSearchRequest customerSearchRequest);

    public ResponseDTO listStaffs(Long customerId);

    public void updateAssignmentCustomer(AssignmentCustomerDTO assignmentCustomerDTO);

    public void addOrUpdateCustomer(CustomerDTO customerDTO);

    public CustomerDTO findDTObyId(Long id);
    public void addOrUpdateTransaction(TransactionDTO transactionDTO);
    public void virtualDeleteCustomers(List<Long> ids);
}
