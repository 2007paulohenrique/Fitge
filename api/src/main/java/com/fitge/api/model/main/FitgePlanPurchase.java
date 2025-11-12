package com.fitge.api.model.main;

import java.time.LocalDate;

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
import lombok.Data;

@Data
@Entity
@Table(name = TableName.FITGE_PLAN_PURCHASE_TABLE,
    indexes = {
        @Index(name = "idx_" + TableName.FITGE_PLAN_PURCHASE_TABLE + TableName.USER_FK_COLUMN, columnList = TableName.USER_FK_COLUMN)
    }
)
public class FitgePlanPurchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "validity_start_date", nullable = false, updatable = false)
    private LocalDate validityStartDate = DateTimeGenerator.todayDate();

    @Column(name = "validity_end_date", nullable = false, updatable = false)
    private LocalDate validityEndDate = DateTimeGenerator.todayDate().plusDays(30);

    @Column(name = "is_auto_renewable", nullable = false)
    private boolean isAutoRenewable = false;

    @ManyToOne
    @JoinColumn(name = TableName.USER_FK_COLUMN, updatable = false)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private User user;
    
    @ManyToOne
    @JoinColumn(name = TableName.FITGE_PLAN_FK_COLUMN, nullable = false, updatable = false)
    private FitgePlan fitgePlan;
    
    @OneToOne
    @JoinColumn(name = TableName.PURCHASE_FK_COLUMN, nullable = false, updatable = false)
    private Purchase purchase;
}
