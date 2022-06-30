package com.idmgmt.springboot.config;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;

public class TripleDES {
    private static final String UNICODE_FORMAT = "UTF8";
    private static final String DESEDE_ENCRYPTION_SCHEME = "DES";
    private final Cipher cipher;
    private final SecretKey key;

    //private String encryptionScheme;

    public TripleDES() throws Exception {
        String myKey = "QAZWSXEDCRFVTGBYHNUJMIKL";
        byte[] keyAsBytes = myKey.getBytes(UNICODE_FORMAT);
        //KeySpec keySpec = new DESedeKeySpec(keyAsBytes);
        KeySpec keySpec = new DESKeySpec(keyAsBytes);
        SecretKeyFactory skf = SecretKeyFactory.getInstance(DESEDE_ENCRYPTION_SCHEME);
        cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        key = skf.generateSecret(keySpec);
    }


    public String encrypt(String unencryptedString) {

        try {
            //cipher.init(javax.crypto.Cipher.ENCRYPT_MODE, key);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            assert false;
            byte[] plaintext = unencryptedString.getBytes(UNICODE_FORMAT);
            byte[] encryptedText = cipher.doFinal(plaintext);
            //Base64 BASE64Encoder = new BASE64Encoder();

            return Base64.getEncoder().encodeToString(encryptedText);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public String encrypt2(String unencryptedString) {
        //unencryptedString = null;
        try {
            cipher.init(Cipher.ENCRYPT_MODE, key);
            assert false;
            byte[] plaintext = unencryptedString.getBytes(UNICODE_FORMAT);
            byte[] encryptedText = cipher.doFinal(plaintext);
            //BASE64Encoder BASE64Encoder = new BASE64Encoder();

            return Base64.getEncoder().encodeToString(encryptedText);

        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public String encrypt3(String unencryptedString) {
        //unencryptedString = "";

        try {
            //keygen
            KeyGenerator kg = KeyGenerator.getInstance("DES");
            SecretKey myDESKey = kg.generateKey();
            //cipher get instance
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, myDESKey);

            assert false;
            byte[] encodedBytes = Base64.getEncoder().encode(unencryptedString.getBytes());
            System.out.println("encodedBytes " + new String(encodedBytes));
            //byte[] decodedBytes = Base64.getDecoder().decode(encodedBytes);
            //System.out.println("decodedBytes " + new String(decodedBytes));

            // login credentials
            // String loginCredentials = Base64.getEncoder().encodeToString((userName + ":" + password).getBytes());

            //convert bytes to encrypted "texts"
            byte[] encryptedText = cipher.doFinal(encodedBytes);
            System.out.println("Text in bytes: " + Arrays.toString(encodedBytes));
            System.out.println("Text: " + new String(encodedBytes));
            System.out.println("Text encrypted: " + new String(encryptedText));
            return new String(encryptedText);

        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    public String decrypt(String encryptedString) {
        String decryptedText;

        try {
            cipher.init(Cipher.DECRYPT_MODE, key);
            //Base64 BASE64Decoder = Base64.getEncoder();
            byte[] plaintext;
            //byte[] encryptedTet = BASE64Decoder.decodeBuffer(encryptedString);
            byte[] encryptedText = Base64.getDecoder().decode(encryptedString);
            assert false;
            plaintext = cipher.doFinal(encryptedText);
            decryptedText = bytes2String(plaintext);

            return decryptedText;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public String decrypt2(String encryptedString) {
        String decryptedText;
        //plaintext = null;;
        try {
            cipher.init(Cipher.DECRYPT_MODE, key);
            //Base64Encoder BASE64Decoder = new Base64Encoder();

            byte[] encryptedText = Base64.getDecoder().decode(encryptedString);
            //Base64.getDecoder().decode(encryptedString);

            byte[] plaintext = cipher.doFinal(encryptedText);
            decryptedText = bytes2String(plaintext);

            return decryptedText;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String bytes2String(byte[] bytes) {
        StringBuilder StringBuilder = new StringBuilder();
        //for (int i=0; i < bytes.length; i++) {
        for (byte aByte : bytes) {
            StringBuilder.append((char) aByte);
        }
        return StringBuilder.toString();
    }

}
