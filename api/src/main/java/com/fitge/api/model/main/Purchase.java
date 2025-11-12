package com.fitge.api.model.main;

import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fitge.api.util.database.TableName;
import com.fitge.api.util.generator.DateTimeGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Data
@Entity
@Table(name = TableName.PURCHASE_TABLE,
    indexes = {
        @Index(name = "idx_" + TableName.PURCHASE_TABLE + TableName.USER_FK_COLUMN, columnList = TableName.USER_FK_COLUMN),
        @Index(name = "idx_" + TableName.PURCHASE_TABLE + "gateway_transaction_id", columnList = "gateway_transaction_id"),
        @Index(name = "idx_" + TableName.PURCHASE_TABLE + "order_number", columnList = "order_number"),
        @Index(name = "idx_" + TableName.PURCHASE_TABLE + TableName.PURCHASE_REFUND_FK_COLUMN, columnList = TableName.PURCHASE_REFUND_FK_COLUMN)
    }
)
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_number", unique = true, nullable = false, updatable = false)
    private String orderNumber;

    @Column(name = "item_amount_brl", precision = 19, scale = 2, nullable = false, updatable = false)
    private BigDecimal itemAmountBrl;

    @Column(name = "fee_amount_brl", precision = 19, scale = 2, nullable = false, updatable = false)
    private BigDecimal feeAmountBrl = BigDecimal.ZERO;

    @Column(name = "discount_amount_brl", precision = 19, scale = 2, nullable = false, updatable = false)
    private BigDecimal discountAmountBrl = BigDecimal.ZERO;

    @Column(name = "total_brl", precision = 19, scale = 2, nullable = false, updatable = false)
    private BigDecimal totalBrl;

    @Column(name = "payment_method", nullable = false, updatable = false)
    private String paymentMethod;

    @Column(name = "gateway_transaction_id", nullable = false, unique = true, updatable = false)
    private String gatewayTransactionId;

    @Column(name = "ip_address", nullable = false, updatable = false, length = 45)
    private String ipAddress;

    @Column(name = "user_agent", nullable = false, updatable = false, length = 100)
    private String userAgent;

    @Column(nullable = false, updatable = false, length = 50)
    private String device;

    @Column(name = "purchase_date", nullable = false, updatable = false)
    private Instant purchaseDate = DateTimeGenerator.nowInstant();
    
    @ManyToOne
    @JoinColumn(name = TableName.USER_FK_COLUMN, updatable = false)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private User user;
    
    @OneToOne
    @JoinColumn(name = TableName.PURCHASE_REFUND_FK_COLUMN, unique = true)
    private PurchaseRefund refund;

}
