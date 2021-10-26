package com.financetool.finance.dto;

import com.financetool.finance.model.OccurrenceType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;

public class DebtRepaymentCreateRequest {
    @NotEmpty
    private Integer debtId;
    @NotEmpty
    private Double value;
    @NotEmpty
    @Enumerated(EnumType.STRING)
    private OccurrenceType occurrenceType;


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
