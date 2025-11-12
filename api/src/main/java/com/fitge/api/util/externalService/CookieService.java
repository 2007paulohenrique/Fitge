package com.fitge.api.util.externalService;

import com.fitge.api.config.JwtProperties;
import com.fitge.api.exception.ApiException;
import com.fitge.api.exception.exceptionCode.SecurityError;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
public class CookieService {
    private final JwtProperties jwtProperties;

    @Autowired
    public CookieService(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    public void addCookie(HttpServletResponse response, String name, String value, String path) {
        ResponseCookie cookie = ResponseCookie.from(name, value)
            .httpOnly(jwtProperties.isCookieHttpOnly())
            .secure(jwtProperties.isCookieSecure())
            .sameSite(jwtProperties.getCookieSameSite())
            .path(path)
            .build();

        response.addHeader("Set-Cookie", cookie.toString());
    }

    public void addAuthCookies(HttpServletResponse response, String accessToken, String refreshToken) {
        addCookie(response, jwtProperties.getAccessCookieName(), accessToken, jwtProperties.getAccessCookiePath());
        addCookie(response, jwtProperties.getRefreshCookieName(), refreshToken, jwtProperties.getRefreshCookiePath());
    }

    public void clearAuthCookies(HttpServletResponse response) {
        deleteCookie(response, jwtProperties.getAccessCookieName(), jwtProperties.getAccessCookiePath());
        deleteCookie(response, jwtProperties.getRefreshCookieName(), jwtProperties.getRefreshCookiePath());
    }

    public String getAccessToken(HttpServletRequest request) {
        return getCookieValueOrThrow(request, jwtProperties.getAccessCookieName());
    }

    public String getRefreshToken(HttpServletRequest request) {
        return getCookieValueOrThrow(request, jwtProperties.getRefreshCookieName());
    }

    public void addLoginCookie(HttpServletResponse response, String loginToken) {
        addCookie(response, jwtProperties.getLoginCookieName(), loginToken, jwtProperties.getLoginCookiePath());
    }

    public void clearLoginCookie(HttpServletResponse response) {
        deleteCookie(response, jwtProperties.getLoginCookieName(), jwtProperties.getLoginCookiePath());
    }

    public String getLoginToken(HttpServletRequest request) {
        return getCookieValueOrThrow(request, jwtProperties.getLoginCookieName());
    }

    public void addSignUpCookie(HttpServletResponse response, String signUpToken) {
        addCookie(response, jwtProperties.getSignUpCookieName(), signUpToken, jwtProperties.getSignUpCookiePath());
    }

    public void clearSignUpCookie(HttpServletResponse response) {
        deleteCookie(response, jwtProperties.getSignUpCookieName(), jwtProperties.getSignUpCookiePath());
    }

    public String getSignUpToken(HttpServletRequest request) {
        return getCookieValueOrThrow(request, jwtProperties.getSignUpCookieName());
    }

    public void deleteCookie(HttpServletResponse response, String name, String path) {
        ResponseCookie cookie = ResponseCookie.from(name, "")
            .httpOnly(jwtProperties.isCookieHttpOnly())
            .secure(jwtProperties.isCookieSecure())
            .sameSite(jwtProperties.getCookieSameSite())
            .path(path)
            .maxAge(0)
            .build();

        response.addHeader("Set-Cookie", cookie.toString());
    }

    public Optional<Cookie> getCookie(HttpServletRequest request, String name) {
        if (request.getCookies() == null) return Optional.empty();

        return Arrays.stream(request.getCookies())
            .filter(cookie -> name.equals(cookie.getName()))
            .findFirst();
    }

    public String getCookieValueOrThrow(HttpServletRequest request, String name) {
        return getCookie(request, name)
            .map(Cookie::getValue)
            .orElseThrow(() -> new ApiException(SecurityError.INVALID_TOKEN_ERROR, HttpStatus.UNAUTHORIZED));
    }
}
