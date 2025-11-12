package com.fitge.api.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fitge.api.model.main.FitgePlanPurchase;
import com.fitge.api.repository.main.FitgePlanPurchaseRepository;
import com.fitge.api.util.generator.DateTimeGenerator;

@Service
public class FitgePlanPurchaseService {
    FitgePlanPurchaseRepository fitgePlanPurchaseRepository;

    @Autowired
    public FitgePlanPurchaseService(FitgePlanPurchaseRepository fitgePlanPurchaseRepository) {
        this.fitgePlanPurchaseRepository = fitgePlanPurchaseRepository;
    }

    public Optional<FitgePlanPurchase> findLastUserFitgePlanPurchase(Long userId) {
        return fitgePlanPurchaseRepository.findFirstByUser_IdOrderByValidityStartDateDesc(userId);
    }

    public boolean checkFitgePlanPurchaseIsValid(FitgePlanPurchase planPurchase) {
        if (planPurchase == null) return false;

        LocalDate today = DateTimeGenerator.todayDate();

        boolean isWithinValidity = !today.isBefore(planPurchase.getValidityStartDate()) && !today.isAfter(planPurchase.getValidityEndDate());
        boolean isNotRefunded = planPurchase.getPurchase().getRefund() == null;

        return isWithinValidity && isNotRefunded;
    }
}
