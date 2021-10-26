package com.financetool.finance.model;

import javax.persistence.*;

@Entity
public class DebtRepayment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer debtRepaymentId;
    private Integer debtId;
    private Double value;
    @Enumerated(EnumType.STRING)
    private OccurrenceType occurrenceType;

    public Integer getId() {
        return debtRepaymentId;
    }

    public Integer getDebtId() {
        return debtId;
    }

    public Double getValue() {
        return value;
    }

    public OccurrenceType getOccurrenceType() {
        return occurrenceType;
    }

    public void setDebtId(Integer debtId) {
        this.debtId = debtId;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public void setOccurrenceType(OccurrenceType occurrenceType) {
        this.occurrenceType = occurrenceType;
    }
}
