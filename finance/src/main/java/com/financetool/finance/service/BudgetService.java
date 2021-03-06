package com.financetool.finance.service;

import com.financetool.finance.dto.BudgetCategoryCreateRequest;
import com.financetool.finance.dto.BudgetCreateRequest;
import com.financetool.finance.model.Budget;
import com.financetool.finance.model.BudgetCategory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface BudgetService {
    Budget createBudget(BudgetCreateRequest budgetCreateRequest);
    List<Budget> getAllBudgets();
    Budget getBudgetById(Integer budgetId);
    List<Budget> getBudgetByUserId(Integer userId);
    Budget updateBudget(Integer budgetId, BudgetCreateRequest budgetCreateRequest);
    void deleteBudget(Integer budgetId);

    BudgetCategory createBudgetCategory(BudgetCategoryCreateRequest budgetCategoryCreateRequest);
    List<BudgetCategory> getAllBudgetCategories();
    BudgetCategory getBudgetCategoryById(Integer budgetCategoryId);
    List<BudgetCategory> getBudgetCategoryByBudgetId(Integer budgetId);
    BudgetCategory updateBudgetCategory(Integer budgetCategoryId, BudgetCategoryCreateRequest budgetCategoryCreateRequest);
    void deleteBudgetCategory(Integer budgetCategoryId);
}
