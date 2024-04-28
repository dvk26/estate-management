package com.javaweb.converter;

import com.javaweb.entity.TransactionEntity;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.dto.TransactionDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionConverter {
    @Autowired
    private ModelMapper modelMapper;
    public  TransactionDTO convertToDTO(TransactionEntity transactionEntity){
        return modelMapper.map(transactionEntity,TransactionDTO.class);
    }
    public  TransactionEntity convertToEntity(TransactionDTO transactionDTO){
        return modelMapper.map(transactionDTO,TransactionEntity.class);
    }
}
