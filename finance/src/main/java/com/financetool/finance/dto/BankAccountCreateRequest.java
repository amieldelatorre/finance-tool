package com.financetool.finance.dto;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

public class BankAccountCreateRequest {
    @NotEmpty
    private String name;
    @NotEmpty
    private Integer userId;
    @NotEmpty
    private Double value;


    public String getName() {
        return name;
    }

    public Integer getUserId() {
        return userId;
    }

    public Double getValue() {
        return value;
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

}
