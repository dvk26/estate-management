package com.javaweb.api.admin;

import com.javaweb.entity.CustomerEntity;
import com.javaweb.model.dto.AssignmentCustomerDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value="customerAPIOfAdmin")
@RequestMapping("/api/customer")
public class CustomerAPI {

    @Autowired
    private CustomerService customerService;


    @GetMapping("/{id}/staffs")
    public ResponseDTO loadStaffs(@PathVariable Long id){
        System.out.println("da call dc api");
        ResponseDTO responseDTO= customerService.listStaffs(id);
        return responseDTO;
    }
    @PostMapping("/assignment")
    public void updateAssignmentCustomer(@RequestBody AssignmentCustomerDTO assignmentCustomerDTO){
        customerService.updateAssignmentCustomer(assignmentCustomerDTO);
    }
    @PostMapping
    public ResponseEntity addOrUpdateCustomer(@RequestBody CustomerDTO customerDTO){
        customerService.addOrUpdateCustomer(customerDTO);
        return ResponseEntity.ok("ok");
    }
    @DeleteMapping("/{ids}")
    public ResponseEntity deleteCustomers(@PathVariable List<Long> ids){
        customerService.virtualDeleteCustomers(ids);
        System.out.println("da xoa"+ids);
        return ResponseEntity.ok("ok");
    }

    @PostMapping("/transaction")
    public ResponseEntity addOrUpdateTransaction(@RequestBody TransactionDTO transactionDTO){
        customerService.addOrUpdateTransaction(transactionDTO);
        return ResponseEntity.ok("ok");
    }
}
