package com.fitge.api.util.formatter;

public class StringFormatter {
    public static String bytesToHexadecimal(byte[] bytes) {
        StringBuilder stringBuilder = new StringBuilder();

        for (byte b : bytes) {
            String hexadecimal = Integer.toHexString(0xff & b);

            if (hexadecimal.length() == 1) stringBuilder.append('0');
            
            stringBuilder.append(hexadecimal);
        }

        return stringBuilder.toString();
    }

}
