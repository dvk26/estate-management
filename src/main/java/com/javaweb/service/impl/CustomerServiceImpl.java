package com.javaweb.service.impl;

import com.javaweb.converter.CustomerConverter;
import com.javaweb.converter.TransactionConverter;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.TransactionEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.AssignmentCustomerDTO;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.model.response.CustomerSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.repository.TransactionRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.repository.custom.CustomerRepositoryCustom;
import com.javaweb.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerConverter customerConverter;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private TransactionConverter transactionConverter;

    @Override
    public List<CustomerSearchResponse> findActiveDividedAll(CustomerSearchRequest customerSearchRequest, Pageable pageable) {
        List<CustomerEntity> customerList=customerRepository.findActiveDevidedAll(customerSearchRequest,pageable);
        List<CustomerSearchResponse> result= new ArrayList<>();
        for(CustomerEntity item: customerList){
            result.add(customerConverter.convertBuildingSearchResponse(item));
        }
        return result;
    }

    @Override
    public int countTotalActiveItems(CustomerSearchRequest customerSearchRequest) {
        return customerRepository.countTotalItems(customerSearchRequest);
    }

    @Override
    public ResponseDTO listStaffs(Long customerId) {
        System.out.println("da vao duoc ham listStaffs");
        List<UserEntity> staffs=userRepository.findByStatusAndRoles_Code(1,"STAFF");
        List<UserEntity> assignedStaffs= userRepository.getAllCustomerAssignedStaff(customerId);
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

    @Override
    public void updateAssignmentCustomer(AssignmentCustomerDTO assignmentCustomerDTO) {
        CustomerEntity customerEntity= customerRepository.findById(assignmentCustomerDTO.getCustomerId()).get();
        List<UserEntity> userEntities= userRepository.findByIdIn(assignmentCustomerDTO.getStaffs());
        customerEntity.setUsers(userEntities);
        customerRepository.save(customerEntity);
    }

    @Override
    public void addOrUpdateCustomer(CustomerDTO customerDTO) {
        if(customerDTO.getId()!=null){
            CustomerEntity foundCustomerEntity=customerRepository.findById(customerDTO.getId()).get();
            customerConverter.convertToCustomerEntity(foundCustomerEntity,customerDTO);
            customerRepository.save(foundCustomerEntity);
        }
        CustomerEntity customerEntity=customerConverter.convertCustomerEntity(customerDTO);
        customerRepository.save(customerEntity);

    }



    @Override
    public CustomerDTO findDTObyId(Long id) {
        CustomerEntity customerEntity=customerRepository.findById(id).get();
        return customerConverter.convertToCustomerDTO(customerEntity);
    }

    @Override
    public void addOrUpdateTransaction(TransactionDTO transactionDTO) {
        TransactionEntity transactionEntity=new TransactionEntity();
        if(transactionDTO.getId()!=null){
            transactionEntity= transactionRepository.findById(transactionDTO.getId()).get();
            transactionEntity.setNote(transactionDTO.getNote());
            transactionRepository.save(transactionEntity);
            return;
        }
        CustomerEntity customerEntity=customerRepository.findById(transactionDTO.getCustomerId()).get();
        transactionEntity=transactionConverter.convertToEntity(transactionDTO);
        transactionEntity.setCustomer(customerEntity);
        transactionRepository.save(transactionEntity);

    }

    @Override
    @Transactional
    public void virtualDeleteCustomers(List<Long> ids) {
        List<CustomerEntity> customerEntities= customerRepository.findAllById(ids);
        for(int i=0;i<ids.size();i++) customerEntities.get(i).setIsActive(0);
        customerRepository.saveAll(customerEntities);
    }
}
