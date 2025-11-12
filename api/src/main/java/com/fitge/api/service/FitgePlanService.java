package com.fitge.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fitge.api.dto.fitgePlanDto.FitgePlanResponseDTO;
import com.fitge.api.exception.ApiException;
import com.fitge.api.exception.exceptionCode.FitgePlanError;
import com.fitge.api.model.main.FitgePlan;
import com.fitge.api.model.main.FitgePlanPurchase;
import com.fitge.api.repository.main.FitgePlanRepository;

@Service
public class FitgePlanService {
    FitgePlanRepository fitgePlanRepository;
    FitgePlanPurchaseService fitgePlanPurchaseService;

    @Autowired
    public FitgePlanService(FitgePlanRepository fitgePlanRepository, FitgePlanPurchaseService fitgePlanPurchaseService) {
        this.fitgePlanRepository = fitgePlanRepository;
        this.fitgePlanPurchaseService = fitgePlanPurchaseService;
    }

    public FitgePlan findById(Byte id) {
        return fitgePlanRepository.findById(id)
            .orElseThrow(() -> new ApiException(FitgePlanError.FITGE_PLAN_NOT_FOUND_ERROR, HttpStatus.NOT_FOUND));
    }

    public FitgePlanResponseDTO findByIdAndCreateResponseDto(Byte id) {
        FitgePlan fitgePlan = findById(id);

        return new FitgePlanResponseDTO(fitgePlan); 
    }

    public FitgePlan findByLevel(Byte level) {
        return fitgePlanRepository.findByLevel(level)
            .orElseThrow(() -> new ApiException(FitgePlanError.FITGE_PLAN_NOT_FOUND_ERROR, HttpStatus.NOT_FOUND));
    }

    public FitgePlan findUserFitgePlan(Long userId) {
        Optional<FitgePlanPurchase> lastFitgePlanPurchase = fitgePlanPurchaseService.findLastUserFitgePlanPurchase(userId);

        if (lastFitgePlanPurchase.isPresent()) {
            if (fitgePlanPurchaseService.checkFitgePlanPurchaseIsValid(lastFitgePlanPurchase.get())) return lastFitgePlanPurchase.get().getFitgePlan();
        }

        return findByLevel((byte) 1);
    }
}
