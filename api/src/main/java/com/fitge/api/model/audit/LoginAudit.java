package com.fitge.api.model.audit;

import java.time.Instant;

import com.fitge.api.util.database.TableName;
import com.fitge.api.util.generator.DateTimeGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = TableName.LOGIN_AUDIT_TABLE, 
    indexes = {
        @Index(name = "idx_" + TableName.LOGIN_AUDIT_TABLE + "user_id", columnList = "user_id")
    }
)
public class LoginAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false, updatable = false)
    private Long userId;

    @Column(name = "ip_address", nullable = false, updatable = false, length = 45)
    private String ipAddress;

    @Column(name = "user_agent", nullable = false, updatable = false, length = 100)
    private String userAgent;

    @Column(nullable = false, updatable = false, length = 50)
    private String device;

    @Column(nullable = false, updatable = false, length = 30)
    private String os;

    @Column(nullable = false, updatable = false, length = 100)
    private String location;

    @Column(name = "create_date", nullable = false, updatable = false)
    private Instant createDate = DateTimeGenerator.nowInstant();

    @Column(nullable = false, updatable = false)
    private Boolean successful;

}