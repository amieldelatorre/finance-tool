package com.financetool.finance.dto;

public class DebtCreateRequest {
    private Integer userId;
    private Double value;
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
