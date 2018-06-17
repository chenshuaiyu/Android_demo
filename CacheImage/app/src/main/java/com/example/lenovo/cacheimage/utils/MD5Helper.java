package com.example.lenovo.cacheimage.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Coder : chenshuaiyu
 * Time : 2018/5/8 19:18
 */
public class MD5Helper {

    private static MessageDigest mMessageDigest=null;

    static {
        try {
            if (null == mMessageDigest) {
                mMessageDigest = MessageDigest.getInstance("MD5");
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }


    /**
     *
     * 对key进行MD5加密，如果无MD5加密算法，则直接使用key对应的hash值。
     * @param key
     * @return
     */
    public static String toMD5(String key) {
        String cacheKey;
        //获取MD5算法失败时，直接使用key对应的hash值
        if ( mMessageDigest == null ) {
            return String.valueOf(key.hashCode());
        }
        mMessageDigest.update(key.getBytes());
        cacheKey = bytesToHexString(mMessageDigest.digest());
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



}
