package com.fitge.api.repository.main;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fitge.api.model.main.FitgePlanPurchase;

@Repository
public interface FitgePlanPurchaseRepository extends JpaRepository<FitgePlanPurchase, Long> {
    Optional<FitgePlanPurchase> findFirstByUser_IdOrderByValidityStartDateDesc(Long userId);

}
