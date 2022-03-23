import java.security.SecureRandom;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.alibaba.fastjson.JSONObject;
import com.zero.hintmgr.util.SecurityUtil;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

@SuppressWarnings("restriction")
public class Security1 {

    private static final String SEED = "appsecretappsecr";
    private static String ALGORITHM_NAME = "AES";
    private static String CHARSET = "UTF-8";

    public static String encode(String content) {
        try {
            return doCipher(Cipher.ENCRYPT_MODE, content);
        } catch (Exception e) {
            e.printStackTrace();
            return content;
        }
    }

    public static String decode(String content) throws Exception {
        return doCipher(Cipher.DECRYPT_MODE, content);
    }

    public static void main(String[] args) throws Exception {
        testSession();
    }
    
    static void testEncodeDecode() throws Exception {
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
            System.out.println(encode("Hint@376#"));
        }
    }
    
    static void testSession() {
        for (int i = 0;i < 10; i++) {
            Map<String, String> map = new HashMap<>();
            String time = String.format("%1$tF %1$tT", System.currentTimeMillis());
            map.put("userId", "2b7b8610233b4137813c2bf26f4a0f4b");
            map.put("loginTime", time);
            String str = JSONObject.toJSONString(map);
            System.out.println("S0: " + str);
            
            //String str = "2b7b8610233b4137813c2bf26f4a0f4b" + "=" + time;
            String str1 = SecurityUtil.encode(str);
            str1 = new String(Base64.getEncoder().encode(str1.getBytes()));
            
            String str2 = SecurityUtil.decode(new String(Base64.getDecoder().decode(str1.getBytes())));
            System.out.println("S1: " + str1);
            System.out.println("S2: " + str2);
            JSONObject obj = JSONObject.parseObject(str2);
            String userId = obj.getString("userId");
            time = obj.getString("loginTime");
            System.out.println("UserID: " + userId + " Time: " + time);
            System.out.println();
        }
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
