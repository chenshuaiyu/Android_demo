package com.example.lenovo.base.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Coder : chenshuaiyu
 * Time : 2018/4/21 14:29
 */
public class Md5Helper {

    private static MessageDigest sMessageDigest=null;

    static {
        try {
            sMessageDigest=MessageDigest.getInstance("MD5");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /*
     * 对key进行MD5加密，如果无MD5加密，返回key的哈希值
     */
    public static String toMD5(String key){

        if(null==sMessageDigest){
            return String.valueOf(key.hashCode());
        }
        String  cacheKey;
        sMessageDigest.update(key.getBytes());
        cacheKey = bytesToHexString(sMessageDigest.digest());

        return cacheKey;
    }

    private static String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }

    public static String MD5(String md5) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

}
