package com.financetool.finance.dto;

import javax.validation.constraints.NotEmpty;

public class InvestmentCreateRequest {
    @NotEmpty
    private Integer userId;
    @NotEmpty
    private String name;
    @NotEmpty
    private Double value;

    public Integer getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public Double getValue() {
        return value;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
