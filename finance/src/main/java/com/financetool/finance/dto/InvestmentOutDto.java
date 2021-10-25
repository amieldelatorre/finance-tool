package com.financetool.finance.dto;

public class InvestmentOutDto {
    private Integer investmentId;
    private Integer userId;
    private String name;
    private Double value;

    public Integer getId() {
        return investmentId;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public Double getValue() {
        return value;
    }

    public void setInvestmentId(Integer investmentId) {
        this.investmentId = investmentId;
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
