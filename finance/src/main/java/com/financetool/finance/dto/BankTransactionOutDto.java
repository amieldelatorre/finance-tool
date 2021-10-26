package com.financetool.finance.dto;

import com.financetool.finance.model.BankTransactionType;
import com.financetool.finance.model.OccurrenceType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;

public class BankTransactionOutDto {
    private Integer bankTransactionId;
    private Integer userId;
    private Integer accountId;
    @Enumerated(EnumType.STRING)
    private BankTransactionType bankTransactionType;
    private Date transactionDate;
    private Double value;
    private String category;
    @Enumerated(EnumType.STRING)
    private OccurrenceType occurrenceType;
    private Date dateOfNextRecurrence;

    public Integer getBankTransactionId() {
        return bankTransactionId;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public BankTransactionType getBankTransactionType() {
        return bankTransactionType;
    }

    public Date getTransactionDate() {
        return transactionDate;
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

    public Date getDateOfNextRecurrence() {
        return dateOfNextRecurrence;
    }

    public void setBankTransactionId(Integer bankTransactionId) {
        this.bankTransactionId = bankTransactionId;
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

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
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

    public void setDateOfNextRecurrence(Date dateOfNextRecurrence) {
        this.dateOfNextRecurrence = dateOfNextRecurrence;
    }
}
