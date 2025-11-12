package com.fitge.api.model.main;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import com.fitge.api.util.database.TableName;

@Data
@Entity
@Table(name = TableName.CLIENT_TABLE,
    indexes = {
        @Index(name = "idx_" + TableName.CLIENT_TABLE + TableName.TRAINING_EXPERIENCE_FK_COLUMN, columnList = TableName.TRAINING_EXPERIENCE_FK_COLUMN),
        @Index(name = "idx_" + TableName.CLIENT_TABLE + TableName.TRAINING_TARGET_FK_COLUMN, columnList = TableName.TRAINING_TARGET_FK_COLUMN),
        @Index(name = "idx_" + TableName.CLIENT_TABLE + TableName.USER_FK_COLUMN, columnList = TableName.USER_FK_COLUMN)
    }
)
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "height_cm")
    private Short heightCm;

    @Column(name = "weight_kg")
    private Short weightKg;
    
    @Column(name = "week_available_days")
    private Byte weekAvailableDays;
    
    @Column(name = "day_available_hours")
    private Byte dayAvailableHours;
    
    @Column(name = "daily_caloric_intake_kcal")
    private Short dailyCaloricIntakeKcal;
    
    @Column(name = "daily_water_intake_liters")
    private Byte dailyWaterIntakeLiters;
    
    @Column(name = "daily_sleep_hours")
    private Byte dailySleepHours;

    @Lob
    @Column(name = "cardiac_conditions")
    private byte[] cardiacConditions;

    @Lob
    @Column(name = "mental_conditions")
    private byte[] mentalConditions;

    @Lob
    @Column(name = "physical_limitations")
    private byte[] physicalLimitations;
    
    @ManyToOne
    @JoinColumn(name = TableName.TRAINING_EXPERIENCE_FK_COLUMN)
    private TrainingExperience trainingExperience;
    
    @ManyToOne
    @JoinColumn(name = TableName.TRAINING_TARGET_FK_COLUMN)
    private TrainingTarget trainingTarget;
    
    @OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JoinColumn(name = TableName.USER_FK_COLUMN, nullable = false, unique = true, updatable = false)
    private User user;
    
}