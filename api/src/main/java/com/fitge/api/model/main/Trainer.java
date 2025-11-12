package com.fitge.api.model.main;

import java.util.ArrayList;
import java.util.List;
import com.fitge.api.util.constraint.PaymentPlanConstraints;
import com.fitge.api.util.constraint.TrainerConstraints;
import com.fitge.api.util.database.TableName;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
@Entity
@Table(name = TableName.TRAINER_TABLE, 
    indexes = {
        @Index(name = "idx_" + TableName.TRAINER_TABLE + TableName.USER_FK_COLUMN, columnList = TableName.USER_FK_COLUMN)
    }
)
public class Trainer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = TrainerConstraints.DESCRIPTION_MAX_LENGTH)
    private String description;

    @Column(nullable = false)
    private Double rating = 0.0;

    @Column(name = "ratings_number", nullable = false)
    @Min(value = 0)
    private int ratingsNumber = 0;
    
    @Column(name = "complaints_number", nullable = false)
    @Min(value = 0)
    private int complaintsNumber = 0;
 
    @Column(name = "contracts_number", nullable = false)
    @Min(value = 0)
    private int contractsNumber = 0;
  
    @Column(name = "payment_plans_duration_price_ratio", nullable = false)
    @DecimalMin(value = "0.0")
    @DecimalMax(value = PaymentPlanConstraints.MAX_DURATION_DAYS_PRICE_RATIO_STRING)
    private Double paymentPlansDurationPriceRatio = 0.0;

    @Column(name = "payment_plans_average_price", nullable = false)
    @DecimalMin(value = "0.0")
    @DecimalMax(value = PaymentPlanConstraints.MAX_PRICE_STRING)
    private Double paymentPlansAveragePrice = 0.0;

    @Column(name = "days_for_client_contract_after_permission", nullable = false)
    private Byte daysForClientContractAfterPermission;

    @Column(name = "max_active_contracts", nullable = false)
    private Byte maxActiveContracts;

    @Column(name = "is_available", nullable = false)
    private Boolean isAvailable = true;

    @Column(name = "is_requests_blocked_in_unavailability", nullable = false)
    private Boolean isRequestsBlockedInUnavailability;

    @Column(name = "email_client_changes_notification_permission", nullable = false)
    private Boolean emailClientChangesNotificationPermission;
    
    @Column(name = "app_client_changes_notification_permission", nullable = false)
    private Boolean appClientChangesNotificationPermission;
        
    @Column(length = 255)
    private String website;

    @OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JoinColumn(name = TableName.USER_FK_COLUMN, nullable = false, unique = true, updatable = false)
    private User user;

    @OneToMany(mappedBy = "trainer", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<TrainerLocation> locations = new ArrayList<>();

    @OneToMany(mappedBy = "trainer", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<TrainerSocialNetwork> socialNetworks = new ArrayList<>();

    @OneToMany(mappedBy = "trainer", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<TrainerSkill> trainingTargets = new ArrayList<>();

}
