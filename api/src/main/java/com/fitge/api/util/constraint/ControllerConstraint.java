package com.fitge.api.util.constraint;

import java.util.Arrays;
import java.util.List;

public class ControllerConstraint {
    public static final List<String> PUBLIC_CONTROLLERS = Arrays.asList(
        "/login",
        "/sign-up",
        "/recover-password",
        "/token/refresh",
        "/identity-confirmation"
    );
}
