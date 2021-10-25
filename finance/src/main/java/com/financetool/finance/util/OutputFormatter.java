package com.financetool.finance.util;

import com.financetool.finance.dto.*;
import com.financetool.finance.model.*;

public class OutputFormatter {
    public static UserOutDto userToUserOutDto(User user) {
        UserOutDto userOut = new UserOutDto();

        userOut.setId(user.getId());
        userOut.setFirstName(user.getFirstName());
        userOut.setLastName(user.getLastName());
        userOut.setEmail(user.getEmail());
        userOut.setRoleType(user.getRoleType());

        return userOut;
    }

    public static AssetOutDto assetToAssetOutDto(Asset asset) {
        AssetOutDto assetOut = new AssetOutDto();

        assetOut.setId(asset.getId());
        assetOut.setUserId(asset.getUserId());
        assetOut.setName(asset.getName());
        assetOut.setValue(asset.getValue());

        return assetOut;
    }

    public static DebtOutDto debtToDebtOutDto(Debt debt) {
        DebtOutDto debtOut = new DebtOutDto();

        debtOut.setId(debt.getId());
        debtOut.setUserId(debt.getUserId());
        debtOut.setValue(debt.getValue());
        debtOut.setDescription(debt.getDescription());

        return debtOut;
    }

    public static DebtRepaymentOutDto debtRepaymentToDebtRepaymentOutDto(DebtRepayment debtRepayment) {
        DebtRepaymentOutDto debtRepaymentOut = new DebtRepaymentOutDto();

        debtRepaymentOut.setDebtRepaymentId(debtRepayment.getId());
        debtRepaymentOut.setDebtId(debtRepayment.getDebtId());
        debtRepaymentOut.setValue(debtRepayment.getValue());
        debtRepaymentOut.setOccurenceType(debtRepayment.getOccurenceType());

        return debtRepaymentOut;
    }

    public static InvestmentOutDto investmentToInvestmentOutDto(Investment investment) {
        InvestmentOutDto investmentOut = new InvestmentOutDto();

        investmentOut.setInvestmentId(investment.getId());
        investmentOut.setUserId(investment.getUserId());
        investmentOut.setName(investment.getName());
        investmentOut.setValue(investment.getValue());

        return investmentOut;
    }

    public static BudgetOutDto budgetToBudgetOutDto(Budget budget) {
        BudgetOutDto budgetOut = new BudgetOutDto();

        budgetOut.setBudgetId(budget.getId());
        budgetOut.setUserId(budget.getUserId());
        budgetOut.setName(budget.getName());
        budgetOut.setDescription(budget.getDescription());

        return budgetOut;
    }

    public static BudgetCategoryOutDto budgetCategoryToBudgetCategoryOutDto(BudgetCategory budgetCategory) {
        BudgetCategoryOutDto budgetCategoryOut = new BudgetCategoryOutDto();

        budgetCategoryOut.setBudgetCategoryId(budgetCategory.getId());
        budgetCategoryOut.setBudgetId(budgetCategory.getBudgetId());
        budgetCategoryOut.setName(budgetCategory.getName());
        budgetCategoryOut.setDescription(budgetCategory.getDescription());
        budgetCategoryOut.setValue(budgetCategory.getValue());

        return budgetCategoryOut;
    }
}
