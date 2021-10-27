package com.financetool.finance.controller;

import com.financetool.finance.dto.AssetCreateRequest;
import com.financetool.finance.dto.AssetOutDto;
import com.financetool.finance.exception.InternalServerException;
import com.financetool.finance.model.Asset;
import com.financetool.finance.service.AssetService;
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
public class AssetController {
    @Autowired
    private AssetService assetService;

    @PostMapping(path = "/assets", consumes = "application/json", produces = "application/json")
    public @ResponseStatus(HttpStatus.CREATED)
    AssetOutDto addNewAsset(@RequestBody @Valid AssetCreateRequest asset, HttpServletRequest request) {
        boolean validAssetCreateRequest = InputValidation.isValidAssetCreateRequest(asset, request);

        if (validAssetCreateRequest) {
            Asset newAsset = assetService.createAsset(asset);
            return OutputFormatter.assetToAssetOutDto(newAsset);
        }
        else
            throw new InternalServerException("Sorry something went wrong.", request);
    }

    @GetMapping(path = "/assets", produces = "application/json")
    public Iterable<AssetOutDto> getAllAssets() {
        List<AssetOutDto> assetOutList = new ArrayList<AssetOutDto>();
        List<Asset> assets = assetService.getAllAssets();

        for (Asset asset : assets)
            assetOutList.add(OutputFormatter.assetToAssetOutDto(asset));

        return assetOutList;
    }

    @GetMapping(path = "/assets/{assetId}", produces = "application/json")
    public AssetOutDto getAssetById(@PathVariable(value="assetId") Integer assetId) {
        Asset asset = assetService.getAssetById(assetId);

        return OutputFormatter.assetToAssetOutDto(asset);
    }

    @GetMapping(path = "/users/{userId}/assets", produces = "application/json")
    public Iterable<AssetOutDto> getAllAssetByUserId(@PathVariable(value="userId") Integer userId) {
        List<AssetOutDto> assetOutList = new ArrayList<AssetOutDto>();
        List<Asset> assets = assetService.getAssetByUserId(userId);

        for (Asset asset : assets)
            assetOutList.add(OutputFormatter.assetToAssetOutDto(asset));

        return assetOutList;
    }

    @PutMapping(path = "/assets/{assetId}", consumes = "application/json", produces = "application/json")
    public AssetOutDto updateAssetById(@PathVariable(value="assetId") Integer assetId, @RequestBody AssetCreateRequest assetRequest, HttpServletRequest request) {
        boolean ValidAssetCreateRequest = InputValidation.isValidAssetCreateRequest(assetRequest, request);

        if (ValidAssetCreateRequest) {
            Asset asset = assetService.updateAsset(assetId, assetRequest);
            return OutputFormatter.assetToAssetOutDto(asset);
        }
        else
            throw new InternalServerException("Sorry something went wrong.", request);
    }

    @DeleteMapping(path = "/assets/{assetId}")
    public ResponseEntity<?> deleteAssetById(@PathVariable(value="assetId") Integer assetId) {
        assetService.deleteAsset(assetId);

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
