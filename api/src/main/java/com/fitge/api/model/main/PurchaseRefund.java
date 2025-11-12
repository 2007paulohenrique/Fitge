package com.fitge.api.model.main;

import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

import com.fitge.api.util.database.TableName;
import com.fitge.api.util.generator.DateTimeGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Data
@Entity
@Table(name = TableName.PURCHASE_REFUND_TABLE,
    indexes = {
        @Index(name = "idx_" + TableName.PURCHASE_REFUND_TABLE + TableName.PURCHASE_FK_COLUMN, columnList = TableName.PURCHASE_FK_COLUMN),
        @Index(name = "idx_" + TableName.PURCHASE_REFUND_TABLE + "gateway_refund_id", columnList = "gateway_refund_id")
    }
)
public class PurchaseRefund {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "refund_amount_brl", precision = 19, scale = 2, nullable = false, updatable = false)
    private BigDecimal refundAmountBrl;

    @Column(name = "gateway_refund_id", nullable = false, unique = true, updatable = false)
    private String gatewayRefundId;

    @Column(name = "refund_method", nullable = false, updatable = false)
    private String refundMethod;

    @Column(name = "request_date", nullable = false, updatable = false)
    private Instant requestDate = DateTimeGenerator.nowInstant();

    @Column(name = "refund_date")
    private Instant refundDate;

    @OneToOne
    @JoinColumn(name = TableName.PURCHASE_FK_COLUMN, nullable = false, updatable = false, unique = true)
    private Purchase purchase;
}
