package com.financetool.finance.service;

import com.financetool.finance.dto.BudgetCategoryCreateRequest;
import com.financetool.finance.dto.BudgetCreateRequest;
import com.financetool.finance.exception.ResourceNotFoundException;
import com.financetool.finance.model.Budget;
import com.financetool.finance.model.BudgetCategory;
import com.financetool.finance.repository.BudgetCategoryRepository;
import com.financetool.finance.repository.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Service
public class BudgetServiceImpl implements BudgetService {
    @Autowired
    private BudgetRepository budgetRepository;
    @Autowired
    private BudgetCategoryRepository budgetCategoryRepository;

    @Override
    public Budget createBudget(BudgetCreateRequest budgetCreateRequest) {
        Budget newBudget = new Budget();

        newBudget.setUserId(budgetCreateRequest.getUserId());
        newBudget.setName(budgetCreateRequest.getName());
        newBudget.setDescription(budgetCreateRequest.getDescription());
        budgetRepository.save(newBudget);

        return newBudget;

    }

    @Override
    public List<Budget> getAllBudgets() {
        return budgetRepository.findAll();
    }

    @Override
    public Optional<Budget> getBudgetById(Integer budgetId) {
        Optional<Budget> budget = budgetRepository.findById(budgetId);

        if (!budget.isPresent()) {
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpServletRequest request = requestAttributes.getRequest();

            throw new ResourceNotFoundException("Budget id " + budgetId + " cannot be found", request);
        }

        return budget;
    }

    @Override
    public List<Budget> getBudgetByUserId(Integer userId) {
        return budgetRepository.findByUserId(userId);
    }

    @Override
    public Optional<Budget> updateBudget(Integer budgetId, BudgetCreateRequest budgetCreateRequest) {
        Optional<Budget> budget = budgetRepository.findById(budgetId);

        if (!budget.isPresent()) {
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpServletRequest request = requestAttributes.getRequest();

            throw new ResourceNotFoundException("Budget id " + budgetId + " cannot be found", request);
        }

        budget.get().setUserId(budgetCreateRequest.getUserId());
        budget.get().setName(budgetCreateRequest.getName());
        budget.get().setDescription(budgetCreateRequest.getDescription());
        budgetRepository.save(budget.get());

        return budget;
    }

    @Override
    public void deleteBudget(Integer budgetId) {
        Optional<Budget> budget = budgetRepository.findById(budgetId);

        if (!budget.isPresent()) {
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpServletRequest request = requestAttributes.getRequest();

            throw new ResourceNotFoundException("Budget id " + budgetId + " cannot be found", request);
        }

        budgetRepository.delete(budget.get());
    }


    @Override
    public BudgetCategory createBudgetCategory(BudgetCategoryCreateRequest budgetCategoryCreateRequest) {
        BudgetCategory newBudgetCategory = new BudgetCategory();

        newBudgetCategory.setBudgetId(budgetCategoryCreateRequest.getBudgetId());
        newBudgetCategory.setName(budgetCategoryCreateRequest.getName());
        newBudgetCategory.setDescription(budgetCategoryCreateRequest.getDescription());
        newBudgetCategory.setValue(budgetCategoryCreateRequest.getValue());
        budgetCategoryRepository.save(newBudgetCategory);

        return newBudgetCategory;
    }

    @Override
    public List<BudgetCategory> getAllBudgetCategories() {
        return budgetCategoryRepository.findAll();
    }

    @Override
    public Optional<BudgetCategory> getBudgetCategoryById(Integer budgetCategoryId) {
        Optional<BudgetCategory> budgetCategory = budgetCategoryRepository.findById(budgetCategoryId);

        if (!budgetCategory.isPresent()) {
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpServletRequest request = requestAttributes.getRequest();

            throw new ResourceNotFoundException("Budget id " + budgetCategoryId + " cannot be found", request);
        }

        return budgetCategory;

    }

    @Override
    public List<BudgetCategory> getBudgetCategoryByBudgetId(Integer budgetId) {
        return budgetCategoryRepository.findByBudgetId(budgetId);
    }

    @Override
    public Optional<BudgetCategory> updateBudgetCategory(Integer budgetCategoryId, BudgetCategoryCreateRequest budgetCategoryCreateRequest) {
        Optional<BudgetCategory> budgetCategory = budgetCategoryRepository.findById(budgetCategoryId);

        if (!budgetCategory.isPresent()) {
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpServletRequest request = requestAttributes.getRequest();

            throw new ResourceNotFoundException("Budget id " + budgetCategoryId + " cannot be found", request);
        }


        budgetCategory.get().setBudgetId(budgetCategoryCreateRequest.getBudgetId());
        budgetCategory.get().setName(budgetCategoryCreateRequest.getName());
        budgetCategory.get().setDescription(budgetCategoryCreateRequest.getDescription());
        budgetCategory.get().setValue(budgetCategoryCreateRequest.getValue());
        budgetCategoryRepository.save(budgetCategory.get());

        return budgetCategory;
    }

    @Override
    public void deleteBudgetCategory(Integer budgetCategoryId) {
        Optional<BudgetCategory> budgetCategory = budgetCategoryRepository.findById(budgetCategoryId);

        if (!budgetCategory.isPresent()) {
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpServletRequest request = requestAttributes.getRequest();

            throw new ResourceNotFoundException("Budget id " + budgetCategoryId + " cannot be found", request);
        }

        budgetCategoryRepository.delete(budgetCategory.get());
    }
}
