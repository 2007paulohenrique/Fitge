package com.fitge.api.model.main;

import com.fitge.api.util.database.TableName;
import com.fitge.api.util.translation.Translation;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = TableName.TRAINING_TARGET_TABLE)
public class TrainingTarget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Byte id;

    @Column(nullable = false, unique = true, length = Translation.DB_TRANSLATION_CODE_MAX_LENGTH)
    private String code;

    @Column(name = "is_sport", nullable = false, updatable = false)
    private Boolean isSport;

}
