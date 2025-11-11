package com.fitge.api.util.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

import com.fitge.api.exception.exceptionCode.DefaultError;

@Documented
@Constraint(validatedBy = MinAgeValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface MinAge {
    String message() default DefaultError.MIN_AGE_DEFAULT_ERROR;

    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default {};

    int value();

}
