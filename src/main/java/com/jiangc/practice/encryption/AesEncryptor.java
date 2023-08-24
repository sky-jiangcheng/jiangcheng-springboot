package com.jiangc.practice.encryption;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class AesEncryptor {

    public static byte[] encrypt(String content, String password) {

        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(128,new SecureRandom(password.getBytes(StandardCharsets.UTF_8)));

            SecretKey secretKey = kgen.generateKey();
            byte[] encodeFormat = secretKey.getEncoded();

            SecretKeySpec key = new SecretKeySpec(encodeFormat,"AES");
            Cipher cipher = Cipher.getInstance("AES");

            byte[] byteContent = content.getBytes(StandardCharsets.UTF_8);
            cipher.init(Cipher.ENCRYPT_MODE,key);

            byte[] result = cipher.doFinal(byteContent);

            return result;

        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException |
                 BadPaddingException e) {
            throw new RuntimeException(e);
        }

    }

    public static byte[] decrypt(byte[] content,String password){
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(128,new SecureRandom(password.getBytes(StandardCharsets.UTF_8)));


            SecretKey secretKey = kgen.generateKey();
            byte[] encodeFormat = secretKey.getEncoded();

            SecretKeySpec key = new SecretKeySpec(encodeFormat,"AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE,key);

            byte[] result = cipher.doFinal(content);
            return result;
        } catch (NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | InvalidKeyException |
                 NoSuchPaddingException e) {
            throw new RuntimeException(e);
        }

    }

}
