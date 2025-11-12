package com.fitge.api.util.security;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.fitge.api.util.formatter.ByteFormatter;
import com.fitge.api.util.formatter.StringFormatter;

public class Encrypt {        
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private static final SecureRandom secureRandom = new SecureRandom();

    public static final int GCM_TAG_LENGTH = 128;
    public static final int GCM_IV_SIZE = 12;

    public static String hashPassword(String plainPassword) {
        return passwordEncoder.encode(plainPassword);
    }

    public static boolean checkPassword(String plainPassword, String hashedPassword) {
        return passwordEncoder.matches(plainPassword, hashedPassword);
    }

    public static String hashSensitiveData(String data) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            byte[] hash = digest.digest(data.getBytes(StandardCharsets.UTF_8));
            
            return StringFormatter.bytesToHexadecimal(hash);

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Erro ao gerar hash do dado sensível", e);
        
        }
    }

    public static boolean checkSensitiveData(String data, String hashedData) {
        String hashedInput = hashSensitiveData(data);

        return hashedInput.equalsIgnoreCase(hashedData);
    }

    public static byte[] encryptSensitiveData(String data, SecretKey key) {
        if (key == null) throw new RuntimeException("Chave AES não definida");
        
        try {
            byte[] iv = new byte[GCM_IV_SIZE];
            secureRandom.nextBytes(iv);

            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            GCMParameterSpec spec = new GCMParameterSpec(GCM_TAG_LENGTH, iv);
            cipher.init(Cipher.ENCRYPT_MODE, key, spec);

            byte[] encrypted = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));

            return ByteFormatter.concatenateBytes(iv, encrypted);

        } catch (Exception e) {
            throw new RuntimeException("Erro ao criptografar dado sensível", e);

        }
    }

    public static String decryptSensitiveData(byte[] encryptedData, SecretKey key) {
        if (key == null) throw new RuntimeException("Chave AES não definida");
        
        try {
            byte[] iv = Arrays.copyOfRange(encryptedData, 0, GCM_IV_SIZE);
            byte[] cipherText = Arrays.copyOfRange(encryptedData, GCM_IV_SIZE, encryptedData.length);

            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            GCMParameterSpec spec = new GCMParameterSpec(GCM_TAG_LENGTH, iv);
            cipher.init(Cipher.DECRYPT_MODE, key, spec);

            return new String(cipher.doFinal(cipherText), StandardCharsets.UTF_8);

        } catch (Exception e) {            
            throw new RuntimeException("Erro ao descriptografar dado sensível", e);
            
        }
    }

}
