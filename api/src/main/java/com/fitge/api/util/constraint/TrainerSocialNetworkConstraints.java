package com.fitge.api.util.constraint;

import com.fitge.api.util.regex.Regex;

public class TrainerSocialNetworkConstraints {
    public static final int PROFILE_MAX_LENGTH = 50;
    public static final String PROFILE_REGEX = "^(?![-_])(?!.*([-_])\\1)[" + Regex.HAS_ONLY_SIMPLE_LETTER + Regex.HAS_ONLY_NUMBER + "_-]+(?<![-_])$";;

}
