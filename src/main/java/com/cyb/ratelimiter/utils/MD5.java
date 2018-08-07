package com.cyb.ratelimiter.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {

    /**
     * 计算字符串的MD5值
     * @param str
     * @return
     */
    public static String md5(String str){
        try {
            if(str == null){
                return null;
            }
            byte[] hash = md5(str.getBytes("utf-8"));
            return Hex.toHexString(hash).toLowerCase();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

	/**
	 * MD5 消息摘要，对输入的内容，进行唯一标识的计算
	 * @param data
	 * @return
	 */
	public static byte[] md5(byte[] data){
        byte[] ret = null;
        if (data != null) {
            try {
                // 创建 消息摘要对象
                MessageDigest digest = MessageDigest.getInstance("MD5");
                ret = digest.digest(data);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }
}
