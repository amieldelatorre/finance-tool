package com.financetool.finance.dto;

import com.financetool.finance.model.OccurenceType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class DebtRepaymentOutDto {
    private Integer debtRepaymentId;
    private Integer debtId;
    private Double value;
    @Enumerated(EnumType.STRING)
    private OccurenceType occurenceType;

    public Integer getId() {
        return debtRepaymentId;
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

    public void setDebtRepaymentId(Integer debtRepaymentId) {
        this.debtRepaymentId = debtRepaymentId;
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
