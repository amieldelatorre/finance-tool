package com.financetool.finance.dto;

public class BudgetCategoryOutDto {
    private  Integer budgetCategoryId;
    private Integer budgetId;
    private String name;
    private String description;
    private Double value;

    public Integer getId() {
        return budgetCategoryId;
    }

    public Integer getBudgetId() {
        return budgetId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getValue() {
        return value;
    }

    public void setBudgetCategoryId(Integer budgetCategoryId) {
        this.budgetCategoryId = budgetCategoryId;
    }

    public void setBudgetId(Integer budgetId) {
        this.budgetId = budgetId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
