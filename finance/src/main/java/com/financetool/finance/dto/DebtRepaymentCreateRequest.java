package com.financetool.finance.dto;

import com.financetool.finance.model.OccurenceType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class DebtRepaymentCreateRequest {
    private Integer debtId;
    private Double value;
    @Enumerated(EnumType.STRING)
    private OccurenceType occurenceType;


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
