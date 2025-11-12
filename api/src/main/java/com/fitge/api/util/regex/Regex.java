package com.fitge.api.util.regex;

public class Regex  {
    public static final String HAS_SIMPLE_LETTER= "(?=.*[A-Za-z])";
    public static final String HAS_NUMBER= "(?=.*\\d)";
    public static final String HAS_SYMBOL= "(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?])";

    public static final String HAS_NOT_LINE_BREAKS = "(?!.*(\\r|\\n))";
    public static final String HAS_NOT_DOUBLE_SPACES = "(?!.*(  ))";
    
    public static final String HAS_ONLY_SIMPLE_LETTER = "A-Za-z";
    public static final String HAS_ONLY_NUMBER = "0-9";
    public static final String HAS_ONLY_ALL_LETTER = "A-Za-zÀ-ÖØ-öø-ÿçÇ";
    public static final String HAS_ONLY_SIMPLE_SPACE = " ";
    public static final String HAS_ONLY_PERIOD = "\\.";
    public static final String HAS_ONLY_UNDERLINE = "_";

}
