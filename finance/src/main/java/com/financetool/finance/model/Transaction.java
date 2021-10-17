package com.financetool.finance.model;

import javax.persistence.*;
import java.util.Date;
import javax.validation.constraints.NotEmpty;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotEmpty
    private Integer userId;
    @NotEmpty
    private Integer accountId;
    @NotEmpty
    @Enumerated(EnumType.STRING)
    private TransactionType type;
    @NotEmpty
    private Date transactionDate;
    @NotEmpty
    private Double amount;

    public Integer getId() {
        return id;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public TransactionType getType() {
        return type;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public Double getAmount() {
        return amount;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
