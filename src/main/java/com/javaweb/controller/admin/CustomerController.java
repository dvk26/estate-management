package com.javaweb.controller.admin;


import com.javaweb.constant.SystemConstant;
import com.javaweb.converter.CustomerConverter;
import com.javaweb.converter.TransactionConverter;
import com.javaweb.entity.TransactionEntity;
import com.javaweb.enums.CustomerStatus;
import com.javaweb.enums.District;
import com.javaweb.enums.TransactionType;
import com.javaweb.enums.TypeCode;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.model.response.CustomerSearchResponse;
import com.javaweb.repository.TransactionRepository;
import com.javaweb.security.utils.SecurityUtils;
import com.javaweb.service.CustomerService;
import com.javaweb.service.IUserService;
import com.javaweb.utils.DisplayTagUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller(value = "customerControllerOfAdmin")
public class CustomerController {

    @Autowired
    private IUserService userService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionConverter transactionConverter;


    @RequestMapping(value="/admin/customer-list", method= RequestMethod.GET)
    public ModelAndView customerList(@ModelAttribute(SystemConstant.MODEL) CustomerSearchRequest customerSearchRequest, HttpServletRequest request){
        ModelAndView mav=new ModelAndView("admin/customer/list");
        String url=request.toString();
        DisplayTagUtils.of(request,customerSearchRequest);
        if(SecurityUtils.getAuthorities().contains("ROLE_STAFF")){
            Long staffId=SecurityUtils.getPrincipal().getId();
            customerSearchRequest.setStaffId(staffId);
        }

        List<CustomerSearchResponse> responseList= customerService.findActiveDividedAll(customerSearchRequest,PageRequest.of(customerSearchRequest.getPage() - 1, customerSearchRequest.getMaxPageItems()));
        customerSearchRequest.setListResult(responseList);
        customerSearchRequest.setTotalItems(customerService.countTotalActiveItems(customerSearchRequest));
        mav.addObject(SystemConstant.MODEL,customerSearchRequest);

        mav.addObject("modelSearch",customerSearchRequest);
        mav.addObject("staffList",userService.getStaffs());
        return mav;
    }

    @RequestMapping(value="/admin/customer-edit", method= RequestMethod.GET)
    public ModelAndView customerAdd(@ModelAttribute("customerEdit") CustomerDTO customerDTO, HttpServletRequest request){
        ModelAndView mav=new ModelAndView("admin/customer/edit");
        mav.addObject("customerStatus", CustomerStatus.type());

        return mav;
    }
    @RequestMapping(value="/admin/customer-edit-{id}",method=RequestMethod.GET)
    public ModelAndView customerEdit(@PathVariable("id") Long id, HttpServletRequest request){
        ModelAndView mav=new ModelAndView("admin/customer/edit");
        CustomerDTO customerDTO= customerService.findDTObyId(id);


        mav.addObject("customerEdit",customerDTO);
        mav.addObject("customerStatus", CustomerStatus.type());
        mav.addObject("transactionType", TransactionType.transactionType());

        List<TransactionDTO> CSKHTransactionDTOS=new ArrayList<>();
        List<TransactionEntity> CSKHTransactionEntities= transactionRepository.findByCodeAndCustomerId("CSKH",customerDTO.getId());
        for(TransactionEntity it: CSKHTransactionEntities) CSKHTransactionDTOS.add(transactionConverter.convertToDTO(it));
        mav.addObject("CSKHTransactions", CSKHTransactionDTOS);

        List<TransactionDTO> DDXTransactionDTOS=new ArrayList<>();
        List<TransactionEntity> DDXTransactionEntities= transactionRepository.findByCodeAndCustomerId("DDX",customerDTO.getId());
        for(TransactionEntity it: DDXTransactionEntities) DDXTransactionDTOS.add(transactionConverter.convertToDTO(it));
        mav.addObject("DDXTransactions", DDXTransactionDTOS);

        return mav;
    }
}
