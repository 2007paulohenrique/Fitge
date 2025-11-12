package com.fitge.api.model.main;

import com.fitge.api.util.database.TableName;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import jakarta.persistence.GenerationType;

@Data
@Entity
@Table(name = TableName.TRAINER_SKILL_TABLE)
public class TrainerSkill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "is_main", nullable = false)
    private Boolean isMain;

    @ManyToOne
    @JoinColumn(name = TableName.TRAINER_FK_COLUMN, nullable = false)
    private Trainer trainer;
    
    @ManyToOne
    @JoinColumn(name = TableName.TRAINING_TARGET_FK_COLUMN, nullable = false)
    private TrainingTarget trainingTarget;
    
}