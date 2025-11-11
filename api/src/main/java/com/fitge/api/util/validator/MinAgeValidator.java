package com.fitge.api.util.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.Period;
import com.fitge.api.util.generator.DateTimeGenerator;

public class MinAgeValidator implements ConstraintValidator<MinAge, LocalDate> {
    private int minAge;

    @Override
    public void initialize(MinAge constraintAnnotation) {
        this.minAge = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(LocalDate birthDate, ConstraintValidatorContext context) {
        if (birthDate == null) return true;

        LocalDate today = DateTimeGenerator.todayDate();

        return Period.between(birthDate, today).getYears() >= minAge;
    }

}
