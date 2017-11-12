package com.lwen.netease;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class Encrypt {
    @Test
    public void test() throws UnsupportedEncodingException {
        System.out.println(encryptId("65533"));
    }


    /**
     * id 加密算法
     * @param id
     * @return
     */
    public String encryptId(String id) throws UnsupportedEncodingException {
        int length = Integer.parseInt(id);
        byte[] codes = "3go8&$8*3*3h0k(2)2".getBytes("utf-8");
        byte[] songId = new byte[length];
        for (int i = 0; i < length; i++) {
            songId[i] = (byte) (songId[i] ^ codes[i % codes.length]);
        }
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("md5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        sun.misc.BASE64Encoder base64Encoder = new sun.misc.BASE64Encoder();
        String result =base64Encoder.encode (digest.digest(songId));
        result=result.replace('/', '_');
        result = result.replace('+', '-');
        return result;
    }
}
