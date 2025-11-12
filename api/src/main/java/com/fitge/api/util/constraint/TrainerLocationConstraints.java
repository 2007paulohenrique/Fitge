package com.fitge.api.util.constraint;

import com.fitge.api.util.regex.Regex;

public class TrainerLocationConstraints {    
    public static final int NAME_MAX_LENGTH = 100;
    public static final String NAME_REGEX = "^[" + Regex.HAS_ONLY_ALL_LETTER + Regex.HAS_ONLY_NUMBER + Regex.HAS_ONLY_PERIOD + Regex.HAS_ONLY_SIMPLE_SPACE + "]+$";
    
    public static final int ADDRESS_MAX_LENGTH = 255;
    public static final String ADDRESS_REGEX = "^[" + Regex.HAS_ONLY_ALL_LETTER + Regex.HAS_ONLY_NUMBER + Regex.HAS_ONLY_PERIOD + Regex.HAS_ONLY_SIMPLE_SPACE + "]+$";
    
    public static final int MAX_LOCATIONS_PER_TRAINER = 3;

}
