package com.fitge.api.model.main;

import com.fitge.api.util.database.TableName;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = TableName.MEDIA_TABLE)
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, updatable = false)
    private String url;
    
    @Column(nullable = false, unique = true, updatable = false)
    private String cloudinaryPublicId;

    @ManyToOne
    @JoinColumn(name = TableName.MEDIA_TYPE_FK_COLUMN, nullable = false, updatable = false)
    private MediaType mediaType;

}
