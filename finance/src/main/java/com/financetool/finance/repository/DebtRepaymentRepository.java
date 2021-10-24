package com.financetool.finance.repository;

import com.financetool.finance.model.DebtRepayment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DebtRepaymentRepository extends JpaRepository<DebtRepayment, Integer> {
    List<DebtRepayment> findByDebtId(Integer debtId);
}
