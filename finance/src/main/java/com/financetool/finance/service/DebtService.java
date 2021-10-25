package com.financetool.finance.service;

import com.financetool.finance.dto.DebtCreateRequest;
import com.financetool.finance.dto.DebtRepaymentCreateRequest;
import com.financetool.finance.model.Debt;
import com.financetool.finance.model.DebtRepayment;
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

    DebtRepayment createDebtRepayment(DebtRepaymentCreateRequest debtRepayment);
    List<DebtRepayment> getAllDebtRepayment();
    Optional<DebtRepayment> getDebtRepaymentById(Integer debtRepaymentId);
    List<DebtRepayment> getDebtRepaymentsByDebtId(Integer debtId);
    void deleteDebtRepayment(Integer debtRepaymentId);
    Optional<DebtRepayment> updateDebtRepayment(Integer debtRepaymentId, DebtRepaymentCreateRequest debtRepaymentRequest);
}
