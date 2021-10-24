package com.financetool.finance.util;

import com.financetool.finance.dto.AssetOutDto;
import com.financetool.finance.dto.DebtOutDto;
import com.financetool.finance.dto.DebtRepaymentOutDto;
import com.financetool.finance.dto.UserOutDto;
import com.financetool.finance.model.Asset;
import com.financetool.finance.model.Debt;
import com.financetool.finance.model.DebtRepayment;
import com.financetool.finance.model.User;

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
}
