package com.financetool.finance.dto;

import javax.validation.constraints.NotEmpty;

public class DebtCreateRequest {
    @NotEmpty
    private Integer userId;
    @NotEmpty
    private Double value;
    @NotEmpty
    private String description;

    public Integer getUserId() {
        return userId;
    }

    public Double getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
