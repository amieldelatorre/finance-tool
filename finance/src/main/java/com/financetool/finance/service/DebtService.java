package com.financetool.finance.service;

import com.financetool.finance.dto.DebtCreateRequest;
import com.financetool.finance.model.Debt;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface DebtService {
    Debt createDebt(DebtCreateRequest debt);
    List<Debt> getAllDebt();
    Optional<Debt> getDebtById(Integer debtId);
    List<Debt> getDebtByUserId(Integer userId);
    Optional<Debt> updateDebt(Integer debtId, DebtCreateRequest debtRequest);
    void deleteDebt(Integer debtId);
}
