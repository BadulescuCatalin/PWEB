package com.example.vroom.Vroom.utils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Utils {
    private static final String privateKey = "SecretKey123456789";
    public static String hashWithKey(String message) {
        try {
            Mac hasher = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKey = new SecretKeySpec(privateKey.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            hasher.init(secretKey);

            byte[] hashedBytes = hasher.doFinal(message.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException("Failed to calculate hash", e);
        }
    }
}
