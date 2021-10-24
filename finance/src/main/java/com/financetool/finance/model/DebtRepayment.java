package com.financetool.finance.model;

import javax.persistence.*;

@Entity
public class DebtRepayment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer debtId;
    private Double value;
    @Enumerated(EnumType.STRING)
    private OccurenceType occurenceType;

    public Integer getId() {
        return id;
    }

    public Integer getDebtId() {
        return debtId;
    }

    public Double getValue() {
        return value;
    }

    public OccurenceType getOccurenceType() {
        return occurenceType;
    }

    public void setDebtId(Integer debtId) {
        this.debtId = debtId;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public void setOccurenceType(OccurenceType occurenceType) {
        this.occurenceType = occurenceType;
    }
}
