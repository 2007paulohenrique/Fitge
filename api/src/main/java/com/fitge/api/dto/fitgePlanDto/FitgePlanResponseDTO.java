package com.fitge.api.dto.fitgePlanDto;

import java.math.BigDecimal;

import com.fitge.api.model.main.FitgePlan;

import lombok.Data;

@Data
public class FitgePlanResponseDTO {
    private Byte id;
    private String code;
    private Byte level;
    private BigDecimal monthlyPriceBrl;
    private Long purchasesNumber;

    public FitgePlanResponseDTO(FitgePlan fitgePlan) {
        this.id = fitgePlan.getId();
        this.code = fitgePlan.getCode();
        this.level = fitgePlan.getLevel();
        this.monthlyPriceBrl = fitgePlan.getMonthlyPriceBrl();
        this.purchasesNumber = fitgePlan.getPurchasesNumber();
    }
}
