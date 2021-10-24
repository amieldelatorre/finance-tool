package com.financetool.finance.util;

import com.financetool.finance.dto.AssetOutDto;
import com.financetool.finance.dto.UserOutDto;
import com.financetool.finance.model.Asset;
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
}
