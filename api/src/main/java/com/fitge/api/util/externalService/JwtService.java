package com.fitge.api.util.externalService;

import com.fitge.api.config.JwtProperties;
import com.fitge.api.exception.ApiException;
import com.fitge.api.exception.exceptionCode.SecurityError;
import com.fitge.api.util.generator.DateTimeGenerator;

import io.jsonwebtoken.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {
    private final JwtProperties jwtProperties;
    private final SecretKey secretKey;

    @Autowired
    public JwtService(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
        this.secretKey = jwtProperties.getJwtSecretKey();
    }

    public String generateAccessToken(Long userId, boolean isTrainer, Long roleUserId, Map<String, Object> extraClaims) {
        return generateToken(userId, "access", isTrainer, roleUserId, extraClaims, jwtProperties.getAccessExpirationMs());
    }

    public String generateRefreshToken(Long userId, boolean isTrainer, Long roleUserId, Map<String, Object> extraClaims) {
        return generateToken(userId, "refresh", isTrainer, roleUserId, extraClaims, jwtProperties.getRefreshExpirationMs());
    }

    public String generateLoginToken(Long userId, boolean isTrainer, Long roleUserId) {
        return generateToken(userId, "login", isTrainer, roleUserId, null, jwtProperties.getLoginExpirationMs());
    }

    public String generateSignUpToken() {
        Map<String, Object> claims = new HashMap<>();

        claims.put("token_type", "signup");

        return Jwts.builder()
            .setId(java.util.UUID.randomUUID().toString())
            .setIssuedAt(DateTimeGenerator.nowDate())
            .setExpiration(new Date(DateTimeGenerator.nowInMs() + jwtProperties.getSignUpExpirationMs()))
            .addClaims(claims)
            .signWith(secretKey, SignatureAlgorithm.HS256)
            .compact();
    }


    private String generateToken(Long userId, String tokenType, boolean isTrainer, Long roleUserId, Map<String, Object> extraClaims, long expirationMs) {
        Map<String, Object> claims = new HashMap<>(extraClaims);

        claims.put("token_type", tokenType);
        claims.put("user_role", isTrainer ? "TRAINER" : "CLIENT");
        claims.put("role_user_id", roleUserId);

        return Jwts.builder()
            .setSubject(userId.toString())
            .setIssuedAt(DateTimeGenerator.nowDate())
            .setExpiration(new Date(DateTimeGenerator.nowInMs() + expirationMs))
            .addClaims(claims)
            .signWith(secretKey, SignatureAlgorithm.HS256)
            .compact();
    }

    public void isAccessTokenValid(String token) {
        isTokenValid(token, "access");
    }

    public void isRefreshTokenValid(String token) {
        isTokenValid(token, "refresh");
    }

    public void isLoginTokenValid(String token) {
        isTokenValid(token, "login");
    }

    public void isSignUpTokenValid(String token) {
        isTokenValid(token, "signup");
    }

    private void isTokenValid(String token, String expectedTokenType) {
        try {
            Claims claims = parseClaims(token).getBody();
            String type = claims.get("token_type", String.class);
    
            if (type == null || !type.equals(expectedTokenType)) throw new ApiException(SecurityError.INVALID_TOKEN_ERROR, HttpStatus.UNAUTHORIZED);
    
            if (claims.getExpiration().before(DateTimeGenerator.nowDate())) throw new ApiException(SecurityError.INVALID_TOKEN_ERROR, HttpStatus.UNAUTHORIZED);
        
        } catch (JwtException e) {
            throw new ApiException(SecurityError.INVALID_TOKEN_ERROR, HttpStatus.UNAUTHORIZED);

        }
    }

    public Long extractUserId(String token) {
        return Long.parseLong(parseClaims(token).getBody().getSubject());
    }

    public String extractUserRole(String token) {
        return extractClaim(token, "user_role", String.class);
    }

    public Long validateAndExtractClientId(String token) {
        boolean isClient = "CLIENT".equals(extractClaim(token, "user_role", String.class));
        
        if (!isClient) throw new ApiException(SecurityError.REQUIRED_USER_ROLE_CLIENT_ERROR, HttpStatus.UNAUTHORIZED);

        return extractClaim(token, "role_user_id", Long.class);
    }

    public Long validateAndExtractTrainerId(String token) {
        boolean isTrainer = "TRAINER".equals(extractClaim(token, "user_role", String.class));
        
        if (!isTrainer) throw new ApiException(SecurityError.REQUIRED_USER_ROLE_TRAINER_ERROR, HttpStatus.UNAUTHORIZED);

        return extractClaim(token, "role_user_id", Long.class);
    }

    public <T> T extractClaim(String token, String claimKey, Class<T> returnedValueClass) {
        Claims claims = extractAllClaims(token);

        return claims.get(claimKey, returnedValueClass);
    }

    public Claims extractAllClaims(String token) {
        return parseClaims(token).getBody();
    }

    private Jws<Claims> parseClaims(String token) {
        return Jwts.parserBuilder()
            .setSigningKey(secretKey)
            .build()
            .parseClaimsJws(token);
    }

}
