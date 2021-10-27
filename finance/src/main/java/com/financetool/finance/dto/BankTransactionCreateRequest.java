package com.financetool.finance.dto;

import com.financetool.finance.model.BankTransactionType;
import com.financetool.finance.model.OccurrenceType;

import javax.persistence.*;
import java.util.Date;

public class BankTransactionCreateRequest {
    private Integer userId;
    private Integer accountId;
    @Enumerated(EnumType.STRING)
    private BankTransactionType bankTransactionType;
    private Double value;
    private String category;
    @Enumerated(EnumType.STRING)
    private OccurrenceType occurrenceType;

    public Integer getUserId() {
        return userId;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public BankTransactionType getBankTransactionType() {
        return bankTransactionType;
    }

    public Double getValue() {
        return value;
    }

    public String getCategory() {
        return category;
    }

    public OccurrenceType getOccurrenceType() {
        return occurrenceType;
    }


    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public void setBankTransactionType(BankTransactionType bankTransactionType) {
        this.bankTransactionType = bankTransactionType;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setOccurrenceType(OccurrenceType occurrenceType) {
        this.occurrenceType = occurrenceType;
    }
}
