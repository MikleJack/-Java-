package com.example.demo.utils;


import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA_256 {

    private static String byte2Hexa(byte[] bytes){
        StringBuffer stringbuffer = new StringBuffer();
        String temp = null;
        for (int i = 0;i < bytes.length;i++){
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length() == 1){
                stringbuffer.append("0");
            }
            stringbuffer.append(temp);
        }
        return stringbuffer.toString();
    }

    public static String getSHA256(String str){
        MessageDigest messageDigest;
        String encodeStr = "";

        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes(StandardCharsets.UTF_8));
            encodeStr = byte2Hexa(messageDigest.digest());
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return  encodeStr;
    }

}
