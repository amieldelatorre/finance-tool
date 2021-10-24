package com.financetool.finance.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Investment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
