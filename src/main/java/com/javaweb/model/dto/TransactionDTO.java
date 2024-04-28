package com.javaweb.model.dto;

public class TransactionDTO extends AbstractDTO{
    private static final long serialVersionUID = 8835146939192307340L;

    private String code;


    private String note;

    private Long customerId;



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