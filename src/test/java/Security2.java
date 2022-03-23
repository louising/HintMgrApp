import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

@SuppressWarnings("restriction")
public class Security2 {

    private static final String SEED = "appsecretappsecr";
    private static String ALGORITHM_NAME = "DESede";
    private static String TRANSFORMATION = "DESede/ECB/PKCS5Padding";
    private static String CHARSET_NAME = "UTF-8";

    public static String encode(String content) {
        try {
            return doCipher(Cipher.ENCRYPT_MODE, content);
        } catch (Exception e) {
            e.printStackTrace();
            return content;
        }
    }

    public static String decode(String content) throws InvalidKeyException, GeneralSecurityException, IOException {
        return doCipher(Cipher.DECRYPT_MODE, content);
    }

    public static void main(String[] args) throws Exception {
        String s = "Hint@376#";
        String en = encode(s); //4Hrsokx0xCGDwfyLsy6zJQ==
        String de = decode(en);
        System.out.println("Ori: " + s + " \nEn " + en + " \nDe " + de + " == " + decode("4Hrsokx0xCGDwfyLsy6zJQ=="));
        System.out.println();

        for (int i = 0; i < 5; i++) {
            s = "root=" + String.format("%1$tF %1$tT", System.currentTimeMillis());
            en = encode(s);
            de = decode(en);
            System.out.println("Ori " + s + " En " + en + " De " + de);
        }
        System.out.println();

        for (int i = 0; i < 5; i++) {
            System.out.println(encode("Hint@376#"));
        }
    }

    /**
    * Do cipher
    * Encode: first encode(), then base64Encode()
    * Decode: first base64Decode(), then decode()
    * @param opMode
    * @param content
    * @return
     * @throws InvalidKeyException 
     * @throws GeneralSecurityException
    * @throws IOException
    */
    private static String doCipher(int opMode, String content) throws InvalidKeyException, GeneralSecurityException, IOException {
        String result = "";

        Cipher cipher = Cipher.getInstance(TRANSFORMATION); //"DES/ECB/NoPadding", "DES/CBC/PKCS5Padding"
        cipher.init(opMode, getKey());

        if (opMode == Cipher.ENCRYPT_MODE) {
            byte[] encodedBytes = cipher.doFinal(content.getBytes(CHARSET_NAME));
            result = new String(new BASE64Encoder().encode(encodedBytes));
        } else if (opMode == Cipher.DECRYPT_MODE) {
            byte[] decodedBytes = cipher.doFinal(new BASE64Decoder().decodeBuffer(content));
            result = new String(decodedBytes, CHARSET_NAME);
        }

        return result;
    }

    /**
    * Get key
    * @return
    * @throws GeneralSecurityException
    * @throws IOException
    */
    private static Key getKey() throws GeneralSecurityException, IOException {
        /*
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM_NAME); 
        byte[] DESkey = SEED.getBytes(CHARSET_NAME); 
        DESKeySpec keySpec = new DESKeySpec(DESkey); 
        Key key = keyFactory.generateSecret(keySpec);
        */

        //Using the following code, will can not be decoded in another JVM
        KeyGenerator keygen = KeyGenerator.getInstance(ALGORITHM_NAME);
        keygen.init(new SecureRandom(SEED.getBytes())); //DES-56, AES-128, DESede-112/168
        //keygen.init(168, new SecureRandom(SEED.getBytes())); //can be [168, 112], default is 168 
        SecretKey secretKey = keygen.generateKey();
        SecretKey key = new SecretKeySpec(secretKey.getEncoded(), ALGORITHM_NAME);

        return key;
    }
}
