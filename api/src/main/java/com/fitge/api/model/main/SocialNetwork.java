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
@Table(name = TableName.SOCIAL_NETWORK_TABLE)
public class SocialNetwork {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Byte id;

    @Column(nullable = false, length = 30, unique = true)
    private String name;

    @Column(name = "web_domain", nullable = false, length = 255, unique = true)
    private String webDomain;

}
