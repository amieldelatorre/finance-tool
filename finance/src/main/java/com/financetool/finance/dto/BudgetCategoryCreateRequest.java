package com.financetool.finance.dto;

import javax.validation.constraints.NotEmpty;

public class BudgetCategoryCreateRequest {
    @NotEmpty
    private Integer budgetId;
    @NotEmpty
    private String name;
    @NotEmpty
    private String description;
    @NotEmpty
    private Double value;

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
