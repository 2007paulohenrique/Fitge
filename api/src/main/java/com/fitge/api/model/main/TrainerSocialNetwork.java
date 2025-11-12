package com.fitge.api.model.main;

import com.fitge.api.util.constraint.TrainerSocialNetworkConstraints;
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
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Data
@Entity
@Table(name = TableName.TRAINER_SOCIAL_NETWORK_TABLE,
    indexes = {
        @Index(name = "idx_" + TableName.TRAINER_SOCIAL_NETWORK_TABLE + TableName.TRAINER_FK_COLUMN, columnList = TableName.TRAINER_FK_COLUMN)
    },
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uk_trainer_social_network",
            columnNames = {TableName.TRAINER_FK_COLUMN, TableName.SOCIAL_NETWORK_FK_COLUMN}
        )
    }
)
public class TrainerSocialNetwork {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = TrainerSocialNetworkConstraints.PROFILE_MAX_LENGTH)
    private String profile;

    @ManyToOne
    @JoinColumn(name = TableName.TRAINER_FK_COLUMN, nullable = false)
    private Trainer trainer;
    
    @ManyToOne
    @JoinColumn(name = TableName.SOCIAL_NETWORK_FK_COLUMN, nullable = false)
    private SocialNetwork socialNetwork;
}
