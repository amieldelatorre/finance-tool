package com.financetool.finance.service;

import com.financetool.finance.dto.AssetCreateRequest;
import com.financetool.finance.exception.ResourceNotFoundException;
import com.financetool.finance.model.Asset;
import com.financetool.finance.repository.AssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Service
public class AssetServiceImpl implements AssetService {
    @Autowired
    private AssetRepository assetRepository;

    @Override
    public Asset createAsset(AssetCreateRequest asset) {
        Asset newAsset = new Asset();

        newAsset.setUserId(asset.getUserId());
        newAsset.setName(asset.getName());
        newAsset.setValue(asset.getValue());
        assetRepository.save(newAsset);

        return newAsset;
    }

    @Override
    public List<Asset> getAllAssets() {
        return assetRepository.findAll();
    }

    @Override
    public Optional<Asset> getAssetById(Integer assetId) {
        Optional<Asset> asset = assetRepository.findById(assetId);

        if (!asset.isPresent()) {
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpServletRequest request = requestAttributes.getRequest();

            throw new ResourceNotFoundException("Asset id " + assetId + " cannot be found.", request);
        }

        return asset;
    }

    @Override
    public List<Asset> getAssetByUserId(Integer userId) {
        List<Asset> assets = assetRepository.findByUserId(userId);
        return assets;
    }

    @Override
    public Optional<Asset> updateAsset(Integer assetId, AssetCreateRequest assetRequest) {
        Optional<Asset> asset = assetRepository.findById(assetId);

        if (!asset.isPresent()) {
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpServletRequest request = requestAttributes.getRequest();

            throw new ResourceNotFoundException("Asset id " + assetId + " cannot be found.", request);
        }
        else {
            asset.get().setUserId(assetRequest.getUserId());
            asset.get().setName(assetRequest.getName());
            asset.get().setValue(assetRequest.getValue());
            assetRepository.save(asset.get());
            return asset;
        }
    }

    @Override
    public void deleteAsset(Integer assetId) {
        Optional<Asset> asset = assetRepository.findById(assetId);

        if (!asset.isPresent()) {
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpServletRequest request = requestAttributes.getRequest();

            throw new ResourceNotFoundException("Asset id " + assetId + " cannot be found.", request);
        }
        else {
            assetRepository.delete(asset.get());
        }
    }
}
