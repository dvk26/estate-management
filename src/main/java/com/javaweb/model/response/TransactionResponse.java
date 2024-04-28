package com.javaweb.model.response;

import com.javaweb.entity.CustomerEntity;
import com.javaweb.model.dto.AbstractDTO;

import javax.persistence.*;

public class TransactionResponse extends AbstractDTO {

    private Long id;


    private String code;


    private String note;

    private Long customerId;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
