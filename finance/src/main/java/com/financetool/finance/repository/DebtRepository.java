package com.financetool.finance.repository;

import com.financetool.finance.model.Debt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DebtRepository extends JpaRepository<Debt, Integer> {
    List<Debt> findByUserId(Integer userId);
}
