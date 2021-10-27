package com.financetool.finance.service;

import com.financetool.finance.dto.AssetCreateRequest;
import com.financetool.finance.model.Asset;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface AssetService {
    Asset createAsset(AssetCreateRequest asset);
    List<Asset> getAllAssets();
    Asset getAssetById(Integer assetId);
    List<Asset> getAssetByUserId(Integer userId);
    Asset updateAsset(Integer assetId, AssetCreateRequest assetRequest);
    void deleteAsset(Integer assetId);
}
