package com.fitge.api.model.main;

import com.fitge.api.util.database.TableName;
import com.fitge.api.util.translation.Translation;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
@Entity
@Table(name = TableName.TRAINING_EXPERIENCE_TABLE)
public class TrainingExperience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Byte id;

    @Column(nullable = false, unique = true, length = Translation.DB_TRANSLATION_CODE_MAX_LENGTH)
    private String code;

    @Column(name = "min_experience_years", nullable = false, unique = true)
    @Min(value = 0)
    @Max(value = 10)
    private Byte minExperienceYears; 
    
    @Column(name = "max_experience_years", unique = true)
    @Min(value = 1)
    @Max(value = 10)
    private Byte maxExperienceYears; 

}
