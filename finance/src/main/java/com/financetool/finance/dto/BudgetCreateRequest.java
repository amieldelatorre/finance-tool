package com.financetool.finance.dto;

import javax.validation.constraints.NotEmpty;

public class BudgetCreateRequest {
    @NotEmpty
    private Integer userId;
    @NotEmpty
    private String name;
    @NotEmpty
    private String description;

    public Integer getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
