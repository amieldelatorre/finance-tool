package com.financetool.finance.dto;

public class AssetCreateRequest {
    private Integer userId;
    private String name;
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

