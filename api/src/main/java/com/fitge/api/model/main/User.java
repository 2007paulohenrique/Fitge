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
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fitge.api.util.constraint.UserConstraints;
import com.fitge.api.util.database.TableName;
import com.fitge.api.util.generator.DateTimeGenerator;
import com.fitge.api.util.validator.MinAge;

@Data
@Entity
@Table(name = TableName.USER_TABLE,
    indexes = {
        @Index(name = "idx_" + TableName.USER_TABLE + "name", columnList = "name"),
        @Index(name = "idx_" + TableName.USER_TABLE + "nickname", columnList = "nickname"),
        @Index(name = "idx_" + TableName.USER_TABLE + TableName.MEDIA_FK_COLUMN, columnList = TableName.MEDIA_FK_COLUMN),
        @Index(name = "idx_" + TableName.USER_TABLE + TableName.COUNTRY_FK_COLUMN, columnList = TableName.COUNTRY_FK_COLUMN)
    }
)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = UserConstraints.NAME_MAX_LENGTH)
    private String name;
    
    @Column(nullable = false, length = UserConstraints.NICKNAME_MAX_LENGTH, unique = true)
    private String nickname;

    @Lob
    @Column(name = "email_encrypted", nullable = false)
    private byte[] emailEncrypted;

    @Column(name = "email_hash", nullable = false, unique = true, length = 64)
    private String emailHash;

    @Column(name = "password_hash", nullable = false, length = 60)
    private String passwordHash;

    @Column(name = "is_trainer", nullable = false, updatable = false)
    private Boolean isTrainer;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @Column(name = "is_dark_theme", nullable = false)
    private Boolean isDarkTheme;

    @Column(name = "is_complainter_anonymous", nullable = false)
    private Boolean isComplainterAnonymous;

    @Column(name = "is_rater_anonymous", nullable = false)
    private Boolean isRaterAnonymous;

    @Column(name = "email_messages_notification_permission", nullable = false)
    private Boolean emailMessagesNotificationPermission;
    
    @Column(name = "email_news_notification_permission", nullable = false)
    private Boolean emailNewsNotificationPermission;
    
    @Column(name = "email_others_notification_permission", nullable = false)
    private Boolean emailOthersNotificationPermission;
    
    @Column(name = "app_messages_notification_permission", nullable = false)
    private Boolean appMessagesNotificationPermission;
    
    @Column(name = "app_news_notification_permission", nullable = false)
    private Boolean appNewsNotificationPermission;

    @Column(name = "app_others_notification_permission", nullable = false)
    private Boolean appOthersNotificationPermission;

    @Column(name = "is_male")
    private Boolean isMale;

    @Column(name = "birth_date", nullable = false, updatable = false)
    @MinAge(UserConstraints.MIN_AGE)
    private LocalDate birthDate;
    
    @Column(name = "create_date", nullable = false, updatable = false)
    private Instant createDate = DateTimeGenerator.nowInstant();

    @OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JoinColumn(name = TableName.MEDIA_FK_COLUMN, unique = true)
    private Media media;
    
    @ManyToOne
    @JoinColumn(name = TableName.COUNTRY_FK_COLUMN)
    private Country country;
    
    @ManyToOne
    @JoinColumn(name = TableName.LANGUAGE_FK_COLUMN, nullable = false)
    private Language language;
    
    @OneToMany(mappedBy = "user")
    private List<Purchase> purchases = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<FitgePlanPurchase> fitgePlanPurchases = new ArrayList<>();

}