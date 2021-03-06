package com.financetool.finance.controller;

import com.financetool.finance.dto.BudgetCategoryCreateRequest;
import com.financetool.finance.dto.BudgetCategoryOutDto;
import com.financetool.finance.dto.BudgetCreateRequest;
import com.financetool.finance.dto.BudgetOutDto;
import com.financetool.finance.exception.InternalServerException;
import com.financetool.finance.model.Budget;
import com.financetool.finance.model.BudgetCategory;
import com.financetool.finance.service.BudgetService;
import com.financetool.finance.util.InputValidation;
import com.financetool.finance.util.OutputFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class BudgetController {
    @Autowired
    private BudgetService budgetService;

    @PostMapping(path = "/budgets", consumes = "application/json", produces = "application/json")
    public @ResponseStatus(HttpStatus.CREATED)
    BudgetOutDto addNewBudget(@RequestBody @Valid BudgetCreateRequest budgetCreateRequest, HttpServletRequest request) {
        boolean validBudgetCreateRequest = InputValidation.isValidBudgetCreateRequest(budgetCreateRequest, request);

        if (validBudgetCreateRequest) {
            Budget budget = budgetService.createBudget(budgetCreateRequest);
            return OutputFormatter.budgetToBudgetOutDto(budget);
        }
        else
            throw new InternalServerException("Sorry something went wrong.", request);
    }

    @GetMapping(path = "/budgets", produces = "application/json")
    public Iterable<BudgetOutDto> getAllBudgets() {
        List<BudgetOutDto> budgetOutDtoList = new ArrayList<BudgetOutDto>();
        List<Budget> budgets = budgetService.getAllBudgets();

        for (Budget budget : budgets)
            budgetOutDtoList.add(OutputFormatter.budgetToBudgetOutDto(budget));

        return budgetOutDtoList;
    }

    @GetMapping(path = "/budgets/{budgetId}", produces = "application/json")
    public BudgetOutDto getBudgetByBudgetId(@PathVariable(value="budgetId") Integer budgetId) {
        Budget budget = budgetService.getBudgetById(budgetId);

        return OutputFormatter.budgetToBudgetOutDto(budget);
    }

    @GetMapping(path = "/users/{userId}/budgets", produces = "application/json")
    public Iterable<BudgetOutDto> getBudgetsByUserId(@PathVariable(value="userId") Integer userId) {
        List<BudgetOutDto> budgetOutDtoList = new ArrayList<BudgetOutDto>();
        List<Budget> budgets = budgetService.getBudgetByUserId(userId);

        for (Budget budget : budgets)
            budgetOutDtoList.add(OutputFormatter.budgetToBudgetOutDto(budget));

        return budgetOutDtoList;
    }

    @PutMapping(path = "/budgets/{budgetId}", consumes = "application/json", produces = "application/json")
    public BudgetOutDto updateBudgetById(@PathVariable(value="budgetId") Integer budgetId, @RequestBody @Valid BudgetCreateRequest budgetCreateRequest, HttpServletRequest request) {
        boolean validBudgetCreateRequest = InputValidation.isValidBudgetCreateRequest(budgetCreateRequest, request);

        if (validBudgetCreateRequest) {
            Budget budget = budgetService.updateBudget(budgetId, budgetCreateRequest);
            return OutputFormatter.budgetToBudgetOutDto(budget);
        }
        else
            throw new InternalServerException("Sorry something went wrong.", request);
    }

    @DeleteMapping(path = "/budgets/{budgetId}")
    public ResponseEntity<?> deleteBudget(@PathVariable(value="budgetId") Integer budgetId) {
        budgetService.deleteBudget(budgetId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Budget Category

    @PostMapping(path = "/budgets/{budgetId}/categories", consumes = "application/json", produces = "application/json")
    public @ResponseStatus(HttpStatus.CREATED)
    BudgetCategoryOutDto addNewBudgetCategory(@RequestBody @Valid BudgetCategoryCreateRequest budgetCategoryCreateRequest, HttpServletRequest request) {
        boolean isValidBudgetCategoryCreateRequest = InputValidation.isValidBudgetCategoryCreateRequest(budgetCategoryCreateRequest, request);

        if (isValidBudgetCategoryCreateRequest) {
            BudgetCategory budgetCategory = budgetService.createBudgetCategory(budgetCategoryCreateRequest);
            return OutputFormatter.budgetCategoryToBudgetCategoryOutDto(budgetCategory);
        }
        else
            throw new InternalServerException("Sorry something went wrong.", request);
    }

    @GetMapping(path = "/budgetCategories", produces = "application/json")
    public Iterable<BudgetCategoryOutDto> getAllBudgetCategories() {
        List<BudgetCategoryOutDto> budgetCategoryOutDtoList = new ArrayList<BudgetCategoryOutDto>();
        List<BudgetCategory> budgetCategories = budgetService.getAllBudgetCategories();

        for (BudgetCategory budgetCategory : budgetCategories)
            budgetCategoryOutDtoList.add(OutputFormatter.budgetCategoryToBudgetCategoryOutDto(budgetCategory));

        return budgetCategoryOutDtoList;
    }

    @GetMapping(path = "/budgetCategories/{budgetCategoryId}", produces = "application/json")
    public BudgetCategoryOutDto getBudgetByBudgetCategoryId(@PathVariable(value="budgetCategoryId") Integer budgetCategoryId) {
        BudgetCategory budgetCategory = budgetService.getBudgetCategoryById(budgetCategoryId);

        return OutputFormatter.budgetCategoryToBudgetCategoryOutDto(budgetCategory);
    }

    @GetMapping(path = "/budgets/{budgetId}/budgetCategories", produces = "application/json")
    public Iterable<BudgetCategoryOutDto> getBudgetCategoryByBudgetId(@PathVariable(value="budgetId") Integer budgetId) {
        List<BudgetCategoryOutDto> budgetCategoryOutDtoList = new ArrayList<BudgetCategoryOutDto>();
        List<BudgetCategory> budgetCategories = budgetService.getBudgetCategoryByBudgetId(budgetId);

        for (BudgetCategory budgetCategory : budgetCategories)
            budgetCategoryOutDtoList.add(OutputFormatter.budgetCategoryToBudgetCategoryOutDto(budgetCategory));

        return budgetCategoryOutDtoList;
    }

    @PutMapping(path = "/budgetCategories/{budgetCategoryId}", consumes = "application/json", produces = "application/json")
    public BudgetCategoryOutDto updateBudgetCategoryById(@PathVariable(value="budgetCategoryId") Integer budgetCategoryId, @RequestBody @Valid BudgetCategoryCreateRequest budgetCategoryCreateRequest, HttpServletRequest request) {
        boolean validBudgetCategoryCreateRequest = InputValidation.isValidBudgetCategoryCreateRequest(budgetCategoryCreateRequest, request);

        if (validBudgetCategoryCreateRequest) {
            BudgetCategory budgetCategory = budgetService.updateBudgetCategory(budgetCategoryId, budgetCategoryCreateRequest);
            return OutputFormatter.budgetCategoryToBudgetCategoryOutDto(budgetCategory);
        }
        else
            throw new InternalServerException("Sorry something went wrong.", request);
    }

    @DeleteMapping(path = "/budgetCategories/{budgetCategoryId}")
    public ResponseEntity<?> deleteBudgetCategory(@PathVariable(value="budgetCategoryId") Integer budgetCategoryId) {
        budgetService.deleteBudgetCategory(budgetCategoryId);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
