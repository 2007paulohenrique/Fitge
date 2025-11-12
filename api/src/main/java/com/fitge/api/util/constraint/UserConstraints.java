package com.fitge.api.util.constraint;

import com.fitge.api.util.regex.Regex;

public class UserConstraints  {
    public static final int NAME_MAX_LENGTH = 50;
    public static final String NAME_REGEX = "^[" + Regex.HAS_ONLY_ALL_LETTER + Regex.HAS_ONLY_PERIOD + Regex.HAS_ONLY_SIMPLE_SPACE + "]+$";

    public static final int NICKNAME_MIN_LENGTH = 3;
    public static final int NICKNAME_MAX_LENGTH = 15;
    public static final String NICKNAME_REGEX = "^[" + Regex.HAS_ONLY_ALL_LETTER + Regex.HAS_ONLY_PERIOD + Regex.HAS_ONLY_UNDERLINE + Regex.HAS_ONLY_NUMBER + "]+$";

    public static final int EMAIL_MAX_LENGTH = 254;
    public static final String EMAIL_REGEX = 
        "^[" + 
        Regex.HAS_ONLY_SIMPLE_LETTER + 
        Regex.HAS_ONLY_NUMBER + 
        "._%+-]+@[" + 
        Regex.HAS_ONLY_SIMPLE_LETTER + 
        Regex.HAS_ONLY_NUMBER + 
        ".-]+\\.[" + 
        Regex.HAS_ONLY_SIMPLE_LETTER + 
        "]{2,}$";

    public static final int PASSWORD_MIN_LENGTH = 8;
    public static final int PASSWORD_MAX_LENGTH = 20;
    public static final String PASSWORD_REGEX = "^" + Regex.HAS_SIMPLE_LETTER + Regex.HAS_NUMBER + Regex.HAS_SYMBOL + "[^\\s]+$";
    
    public static final int MIN_AGE = 12;

    public static final Byte MAX_PHOTO_SIZE_MB = 2;

}
