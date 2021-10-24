package com.financetool.finance.dto;

public class DebtOutDto {
    private Integer id;
    private Integer userId;
    private Double value;
    private String description;

    public Integer getId() {
        return id;
    }

    public Integer getUserId() {
        return userId;
    }

    public Double getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public void setId(Integer id) {
        this.id = id;
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
