package com.ps.bicycleuserservice.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
    public static void main(String[] args) {
        String md5Code = MD5Util.getMd5Code("12345");
        System.out.println(md5Code);
    }

    public static String getMd5Code(String inString) {
        byte[] defaultBytes = null;
        try{
            defaultBytes = inString.getBytes("UTF-8");
        }catch (Exception e){
            defaultBytes = inString.getBytes();
        }

        MessageDigest algorithm = null;
        try {
            algorithm = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        algorithm.reset();

        algorithm.update(defaultBytes);

        byte[] messageDigest = algorithm.digest();
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < messageDigest.length; i++) {
            String hex = Integer.toHexString(0xFF & messageDigest[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
