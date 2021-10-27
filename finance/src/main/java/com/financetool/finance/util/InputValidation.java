package com.financetool.finance.util;

import com.financetool.finance.dto.*;
import com.financetool.finance.exception.BadRequestException;
import com.financetool.finance.exception.ResourceNotFoundException;
import com.financetool.finance.model.BankAccount;
import com.financetool.finance.model.Budget;
import com.financetool.finance.model.Debt;
import com.financetool.finance.model.User;
import com.financetool.finance.service.*;
import jdk.internal.util.xml.impl.Input;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Component
public class InputValidation {
    private static UserService userService;
    private static BankService bankService;
    private static BudgetService budgetService;
    private static AssetService assetService;
    private static DebtService debtService;
    private static InvestmentService investmentService;

    @Autowired
    public InputValidation(UserService uService, BankService bService, BudgetService buService, AssetService aService, DebtService dService, InvestmentService iService) {
        InputValidation.userService = uService;
        InputValidation.bankService = bService;
        InputValidation.budgetService = buService;
        InputValidation.assetService = aService;
        InputValidation.debtService = debtService;
        InputValidation.investmentService = investmentService;
    }

    public static boolean EmailExists(String email) {
        User user = userService.getUserByEmail(email);
        return user != null;
    }

    public static boolean checkStringNotEmpty(String apiStringInput) {
        return (apiStringInput != null && !apiStringInput.trim().isEmpty());
    }

    public static boolean checkUserExists(Integer userId) {
        User user = userService.getUserById(userId);
        return user != null;
    }

    public static boolean checkBankAccountExists(Integer bankAccountId) {
        BankAccount bankAccount = bankService.getBankAccountById(bankAccountId);
        return bankAccount != null;
    }

    public static boolean checkBudgetExists(Integer budgetId) {
        Budget budget = budgetService.getBudgetById(budgetId);
        return budget != null;
    }

    public static boolean checkDebtExists(Integer debtId) {
        Debt debt = debtService.getDebtById(debtId);
        return debt != null;
    }

    public static boolean valueGreaterThanZero(double value) {
        return value > 0;
    }

    public static boolean valueGreaterThanEqualToZero(double value) {
        return value >= 0;
    }


    public static boolean isValidUserCreateRequest(UserCreateRequest userCreateRequest, HttpServletRequest request) {
        if (!checkStringNotEmpty(userCreateRequest.getFirstName()))
            throw new BadRequestException("Field firstName cannot be null or empty.", request);
        if (!checkStringNotEmpty(userCreateRequest.getLastName()))
            throw new BadRequestException("Field lastName cannot be null or empty.", request);
        if (!checkStringNotEmpty(userCreateRequest.getEmail()))
            throw new BadRequestException("Field email cannot be null or empty.", request);
        if (EmailExists(userCreateRequest.getEmail()))
            throw new BadRequestException("Sorry this email already exists.", request);
        if (!checkStringNotEmpty(userCreateRequest.getPassword()))
            throw new BadRequestException("Field password cannot be null or empty.", request);

        return true;
    }

    public static boolean isValidAssetCreateRequest(AssetCreateRequest assetCreateRequest, HttpServletRequest request) {
        if (!checkUserExists(assetCreateRequest.getUserId()))
            throw new ResourceNotFoundException("User id " + assetCreateRequest.getUserId() + " cannot be found.", request);
        if (!checkStringNotEmpty(assetCreateRequest.getName()))
            throw new BadRequestException("Field name cannot be null or empty.", request);
        if (!valueGreaterThanZero(assetCreateRequest.getValue()))
            throw new BadRequestException("Field value must be greater than zero.", request);

        return true;
    }

    public static boolean isValidBankAccountCreateRequest(BankAccountCreateRequest bankAccountCreateRequest, HttpServletRequest request) {
        if (!checkStringNotEmpty(bankAccountCreateRequest.getName()))
            throw new BadRequestException("Field name cannot be null or empty.", request);
        if (!checkUserExists(bankAccountCreateRequest.getUserId()))
            throw new ResourceNotFoundException("User id " + bankAccountCreateRequest.getUserId() + " cannot be found.", request);
        return true;
    }

    public static boolean isValidBankTransactionCreateRequest(BankTransactionCreateRequest bankTransactionCreateRequest, HttpServletRequest request) {
        if (!checkUserExists(bankTransactionCreateRequest.getUserId()))
            throw new ResourceNotFoundException("User id " + bankTransactionCreateRequest.getUserId() + " cannot be found.", request);
        if (!checkBankAccountExists(bankTransactionCreateRequest.getAccountId()))
            throw new ResourceNotFoundException("Bank Account id " + bankTransactionCreateRequest.getAccountId() + " cannot be found.", request);
        if (!valueGreaterThanZero(bankTransactionCreateRequest.getValue()))
            throw new BadRequestException("Field value must be greater than zero.", request);
        if (!checkStringNotEmpty(bankTransactionCreateRequest.getCategory()))
            throw new BadRequestException("Field category cannot be null or empty.", request);

        return true;
    }

    public static boolean isValidBudgetCategoryCreateRequest(BudgetCategoryCreateRequest budgetCategoryCreateRequest, HttpServletRequest request) {
        if (!checkBudgetExists(budgetCategoryCreateRequest.getBudgetId()))
            throw new ResourceNotFoundException("Budget id " + budgetCategoryCreateRequest.getBudgetId() + " cannot be found.", request);
        if (!checkStringNotEmpty(budgetCategoryCreateRequest.getName()))
            throw new BadRequestException("Field name cannot be null or empty.", request);
        if (!valueGreaterThanZero(budgetCategoryCreateRequest.getValue()))
            throw new BadRequestException("Field value must be greater than zero.", request);

        return true;
    }

    public static boolean isValidBudgetCreateRequest(BudgetCreateRequest budgetCreateRequest, HttpServletRequest request) {
        if (!checkUserExists(budgetCreateRequest.getUserId()))
            throw new ResourceNotFoundException("User id " + budgetCreateRequest.getUserId() + " cannot be found.", request);
        if (!checkStringNotEmpty(budgetCreateRequest.getName()))
            throw new BadRequestException("Field name cannot be null or empty.", request);

        return true;
    }

    public static boolean isValidDebtCreateRequest(DebtCreateRequest debtCreateRequest, HttpServletRequest request) {
        if (!checkUserExists(debtCreateRequest.getUserId()))
            throw new ResourceNotFoundException("User id " + debtCreateRequest.getUserId() + " cannot be found.", request);
        if (!valueGreaterThanEqualToZero(debtCreateRequest.getValue()))
            throw new BadRequestException("Field value must be greater than zero or equal to zero.", request);

        return true;
    }

    public static boolean isValidDebtRepaymentCreateRequest(DebtRepaymentCreateRequest debtRepaymentCreateRequest, HttpServletRequest request) {
        if (!checkDebtExists(debtRepaymentCreateRequest.getDebtId()))
            throw new ResourceNotFoundException("Debt id " + debtRepaymentCreateRequest.getDebtId() + " cannot be found.", request);
        if (!valueGreaterThanZero(debtRepaymentCreateRequest.getValue()))
            throw new BadRequestException("Field value must be greater than zero.", request);

        return true;
    }

    public static boolean isValidInvestmentCreateRequest(InvestmentCreateRequest investmentCreateRequest, HttpServletRequest request) {
        if (!checkUserExists(investmentCreateRequest.getUserId()))
            throw new ResourceNotFoundException("User id " + investmentCreateRequest.getUserId() + " cannot be found.", request);
        if (!checkStringNotEmpty(investmentCreateRequest.getName()))
            throw new BadRequestException("Field name cannot be null or empty.", request);
        if (!valueGreaterThanZero(investmentCreateRequest.getValue()))
            throw new BadRequestException("Field value must be greater than zero.", request);

        return true;
    }
}
