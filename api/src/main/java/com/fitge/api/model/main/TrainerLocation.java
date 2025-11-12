package com.fitge.api.model.main;

import com.fitge.api.util.constraint.TrainerLocationConstraints;
import com.fitge.api.util.database.TableName;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = TableName.TRAINER_LOCATION_TABLE, 
    indexes = {
        @Index(name = "idx_" + TableName.TRAINER_LOCATION_TABLE + TableName.TRAINER_FK_COLUMN, columnList = TableName.TRAINER_FK_COLUMN)
    }
)
public class TrainerLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = TrainerLocationConstraints.NAME_MAX_LENGTH)
    private String name;

    @Column(nullable = false, length = TrainerLocationConstraints.ADDRESS_MAX_LENGTH)
    private String address;

    @ManyToOne
    @JoinColumn(name = TableName.TRAINER_FK_COLUMN, nullable = false)
    private Trainer trainer;

}
