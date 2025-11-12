package com.fitge.api.model.main;

import java.math.BigDecimal;

import com.fitge.api.util.database.TableName;
import com.fitge.api.util.translation.Translation;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
@Entity
@Table(name = TableName.FITGE_PLAN_TABLE)
public class FitgePlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Byte id;

    @Column(nullable = false, unique = true, length = Translation.DB_TRANSLATION_CODE_MAX_LENGTH)
    private String code;

    @Column(nullable = false, unique = true)
    private Byte level;

    @Column(name = "monthly_price_brl", precision = 19, scale = 2, nullable = false)
    private BigDecimal monthlyPriceBrl;

    @Column(name = "purchases_number", nullable = false)
    @Min(value = 0)
    private Long purchasesNumber = 0L;
}
