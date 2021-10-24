package com.financetool.finance.model;

import javax.persistence.*;
import java.util.Date;
import javax.validation.constraints.NotEmpty;

@Entity
public class BankTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotEmpty
    private Integer userId;
    @NotEmpty
    private Integer accountId;
    @NotEmpty
    @Enumerated(EnumType.STRING)
    private BankTransactionType bankTransactionType;
    @NotEmpty
    private Date transactionDate;
    @NotEmpty
    private Double value;
    private String category;
    private OccurenceType occurenceType;
    private Date dateOfNextRecurrence;

    public Integer getId() {
        return id;
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

    public OccurenceType getOccurenceType() {
        return occurenceType;
    }

    public Date getDateOfNextRecurrence() {
        return dateOfNextRecurrence;
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

    public void setOccurenceType(OccurenceType occurenceType) {
        this.occurenceType = occurenceType;
    }

    public void setDateOfNextRecurrence(Date dateOfNextRecurrence) {
        this.dateOfNextRecurrence = dateOfNextRecurrence;
    }
}
