package com.zero.hintmgr.util;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.zero.hintmgr.ServiceException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * Security Util for Win/Linux
 * 
 * @author Louisling
 * @since 2021-11-27
 */
@SuppressWarnings("restriction")
public class SecurityUtil {
    private static final String SEED = "appsecretappsecr";
    private static String ALGORITHM_NAME = "AES";
    private static String CHARSET = "UTF-8";

    public static String encode(String content) {
        try {
            return doCipher(Cipher.ENCRYPT_MODE, content);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("Failed to encode");
        }
    }

    public static String decode(String content) {
        try {            
            return doCipher(Cipher.DECRYPT_MODE, content);
        } catch (Exception e) {
            throw new ServiceException("Failed to decode");
        }
    }

    public static void main(String[] args) throws Exception {
        String s = "Hint@376#";
        String en = encode(s);
        String de = decode(en);
        System.out.println("Ori: " + s + " \nEn " + en + " \nDe " + de + " == " + decode("a4HfsUHXuUKMxkYetG4JtQ==")); //
        System.out.println();

        System.out.println(decode("/I9Kb8PhrUDYede+2V2KPwWSAIbP33X7uZATUwEBrvg=") + " --- " + decode("a4HfsUHXuUKMxkYetG4JtQ=="));
        for (int i = 0; i < 5; i++) {
            s = "root=" + String.format("%1$tF %1$tT", System.currentTimeMillis());
            en = encode(s);
            de = decode(en);
            System.out.println("Ori " + s + " En " + en + " De " + de);
            //Thread.sleep(1000);
        }
        System.out.println();

        for (int i = 0; i < 5; i++) {
            System.out.println(encode("Secret" + i));
        }
        System.out.println(encode("secret")); //PcXtp6DX5c3zJR8/X3L4NQ==
        System.out.println(decode("LSgIs10JyNr6Rw+8/OnS1Q==")); //Secret1
    }

    private static String doCipher(int opMode, String content) throws Exception {
        String result = "";

        byte[] keyBytes = generateKey();
        SecretKeySpec sks = new SecretKeySpec(keyBytes, ALGORITHM_NAME);

        Cipher cipher = Cipher.getInstance(ALGORITHM_NAME);
        cipher.init(opMode, sks);

        if (opMode == Cipher.ENCRYPT_MODE) {
            byte[] encodedBytes = cipher.doFinal(content.getBytes(CHARSET));
            result = new String(new BASE64Encoder().encode(encodedBytes));
        } else if (opMode == Cipher.DECRYPT_MODE) {
            byte[] decodedBytes = cipher.doFinal(new BASE64Decoder().decodeBuffer(content));
            result = new String(decodedBytes, CHARSET);
        }

        return result;
    }

    private static byte[] generateKey() throws Exception {
        byte[] keyBytes = null;
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        //Just for Windows: kgen.init(128, new SecureRandom(password.getBytes("UTF-8"));
        //For Linux/Windows
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        random.setSeed(SEED.getBytes(CHARSET));
        kgen.init(128, random);

        SecretKey secretKey = kgen.generateKey();
        keyBytes = secretKey.getEncoded();
        return keyBytes;
    }
}