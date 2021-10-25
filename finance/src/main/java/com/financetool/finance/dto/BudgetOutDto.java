package com.financetool.finance.dto;

public class BudgetOutDto {
    private Integer budgetId;
    private Integer userId;
    private String name;
    private String description;

    public Integer getId() {
        return budgetId;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setBudgetId(Integer budgetId) {
        this.budgetId = budgetId;
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
