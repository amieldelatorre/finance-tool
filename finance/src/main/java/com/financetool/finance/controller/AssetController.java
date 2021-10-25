package com.financetool.finance.controller;

import com.financetool.finance.dto.AssetCreateRequest;
import com.financetool.finance.dto.AssetOutDto;
import com.financetool.finance.model.Asset;
import com.financetool.finance.service.AssetService;
import com.financetool.finance.util.OutputFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class AssetController {
    @Autowired
    private AssetService assetService;

    @PostMapping(path = "/assets", consumes = "application/json", produces = "application/json")
    public @ResponseStatus(HttpStatus.CREATED)
    AssetOutDto addNewAsset(@RequestBody @Valid AssetCreateRequest asset) {
        Asset newAsset = assetService.createAsset(asset);

        return OutputFormatter.assetToAssetOutDto(newAsset);
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
        Optional<Asset> asset = assetService.getAssetById(assetId);

        return OutputFormatter.assetToAssetOutDto(asset.get());
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
    public AssetOutDto updateAssetById(@PathVariable(value="assetId") Integer assetId, @RequestBody AssetCreateRequest assetRequest) {
        Optional<Asset> asset = assetService.updateAsset(assetId, assetRequest);

        return OutputFormatter.assetToAssetOutDto(asset.get());
    }

    @DeleteMapping(path = "/assets/{assetId}")
    public ResponseEntity<?> deleteAssetById(@PathVariable(value="assetId") Integer assetId) {
        assetService.deleteAsset(assetId);

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
