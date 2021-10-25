package com.financetool.finance.repository;

import com.financetool.finance.model.BudgetCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BudgetCategoryRepository extends JpaRepository<BudgetCategory, Integer> {
    List<BudgetCategory> findByBudgetId(Integer budgetId);
}
