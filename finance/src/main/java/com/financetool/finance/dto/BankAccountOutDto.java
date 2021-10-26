package com.financetool.finance.dto;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

public class BankAccountOutDto {
    private Integer bankAccountId;
    private String name;
    private Integer userId;
    private Double value;
    private Date dateOpened;

    public Integer getId() {
        return bankAccountId;
    }

    public String getName() {
        return name;
    }

    public Integer getUserId() {
        return userId;
    }

    public Double getValue() {
        return value;
    }

    public Date getDateOpened() {
        return dateOpened;
    }

    public void setBankAccountId(Integer bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public void setDateOpened(Date dateOpened) {
        this.dateOpened = dateOpened;
    }
}
