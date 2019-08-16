package com.ps.bicycleuserservice.util;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * AES算法（推荐使用）
 */
public class AESUtil {

   public static void main(String[] args) throws Exception {
      // 生成秘钥
      String key = generateKey();
      System.out.println("秘钥： " + key);

      // 加密
      String encryption = encode(key, "我爱你1");
      System.out.println("加密： " + encryption);

      // 解密
      System.out.println( decode(key, encryption) );
   }

   public static String generateKey() throws NoSuchAlgorithmException {

      //密钥生成器
      KeyGenerator keyGen = KeyGenerator.getInstance("AES");

      //默认128，获得无政策权限后可为192或256
      keyGen.init(128);

      //生成密钥
      SecretKey secretKey = keyGen.generateKey();

      //密钥字节数组
      byte[] key = secretKey.getEncoded();

      return Base64.getEncoder().encodeToString(key);
   }

   public static String encode(String key , String text) throws Exception {
      //恢复密钥
      byte[] keyByte = Base64.getDecoder().decode(key);

      SecretKey secretKey = new SecretKeySpec(keyByte, "AES");

      //Cipher完成加密或解密工作类
      Cipher cipher = Cipher.getInstance("AES");

      //对Cipher初始化，加密模式
      cipher.init(Cipher.ENCRYPT_MODE, secretKey);

      //加密data
      byte[] cipherByte = cipher.doFinal(text.getBytes());

      return Base64.getEncoder().encodeToString(cipherByte);
   }

   public static String decode(String key, String text) throws Exception{

      byte[] keyByte = Base64.getDecoder().decode(key);

      //恢复密钥
      SecretKey secretKey = new SecretKeySpec(keyByte, "AES");

      //Cipher完成加密或解密工作类
      Cipher cipher = Cipher.getInstance("AES");

      //对Cipher初始化，解密模式
      cipher.init(Cipher.DECRYPT_MODE, secretKey);

      //解密data
      byte[] cipherByte = cipher.doFinal(Base64.getDecoder().decode(text));

      return new String(cipherByte);
   }
}