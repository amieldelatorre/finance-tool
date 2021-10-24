package com.financetool.finance.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
