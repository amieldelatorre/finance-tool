package com.financetool.finance.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer bankAccountId;
    @NotEmpty
    private String name;
    @NotEmpty
    private Integer userId;
    @NotEmpty
    private Double value;
    @NotEmpty
    private Date dateOpened;

    public Integer getId() {
        return bankAccountId;
    }

    public String getName() {
        return name;
    }

    public Integer getUserId() {
        return userId;
    }

    public Double getValue() {
        return value;
    }

    public Date getDateOpened() {
        return dateOpened;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public void setDateOpened(Date dateOpened) {
        this.dateOpened = dateOpened;
    }
}
