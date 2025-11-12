package com.fitge.api.model.main;

import com.fitge.api.util.database.TableName;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;import lombok.Data;

@Data
@Entity
@Table(name = TableName.MEDIA_TYPE_TABLE)
public class MediaType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Byte id;

    @Column(nullable = false, unique = true, length = 20)
    private String type; 

}
