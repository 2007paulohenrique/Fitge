package com.fitge.api.util.constraint;

import com.fitge.api.util.regex.Regex;

public class ClientConstraints {
    public static final int MIN_HEIGHT = 20;
    public static final int MAX_HEIGHT = 300;
    
    public static final int MIN_WEIGHT = 20;
    public static final int MAX_WEIGHT = 600;

    public static final int MIN_DAILY_CALORIC_INTAKE_KCAL = 200;
    public static final int MAX_DAILY_CALORIC_INTAKE_KCAL = 30000;

    public static final int MIN_DAILY_WATER_INTAKE_LITERS = 0;
    public static final int MAX_DAILY_WATER_INTAKE_LITERS = 20;    

    public static final int MIN_DAILY_SLEEP_HOURS = 2;
    public static final int MAX_DAILY_SLEEP_HOURS = 20;
    
    public static final String CARDIAC_CONDITIONS_REGEX = "^" + Regex.HAS_NOT_DOUBLE_SPACES + Regex.HAS_NOT_LINE_BREAKS + ".*$";
    public static final int CARDIAC_CONDITIONS_MAX_LENGTH = 500;

    public static final String MENTAL_CONDITIONS_REGEX = "^" + Regex.HAS_NOT_DOUBLE_SPACES + Regex.HAS_NOT_LINE_BREAKS + ".*$";
    public static final int MENTAL_CONDITIONS_MAX_LENGTH = 500;
    
    public static final String PHYSICAL_LIMITATIONS_REGEX = "^" + Regex.HAS_NOT_DOUBLE_SPACES + Regex.HAS_NOT_LINE_BREAKS + ".*$";
    public static final int PHYSICAL_LIMITATIONS_MAX_LENGTH = 500;

}
