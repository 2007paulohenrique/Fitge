package com.fitge.api.util.constraint;

public class PaymentPlanConstraints {
    public static final int MIN_DURATION_DAYS = 7;
    public static final int MAX_DURATION_DAYS = 365;
    public static final Double MIN_PRICE = 10.0;
    public static final Double MAX_PRICE_DOUBLE = 30000.0;
    public static final String MAX_PRICE_STRING = "" + 30000.0;
    public static final Double MAX_DURATION_DAYS_PRICE_RATIO_DOUBLE = MAX_DURATION_DAYS / MIN_PRICE;
    public static final String MAX_DURATION_DAYS_PRICE_RATIO_STRING = "" + 365 / 10.0;
    public static final int MAX_PAYMENT_PLANS_PER_TRAINER = 5;

}
