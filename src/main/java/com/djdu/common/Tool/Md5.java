package com.djdu.common.Tool;

import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @ClassName Md5
 * @Description TODO 利用MD5对密码进行加密与解密
 * @Author DJDU
 * @Date 2019/3/11 11:42
 * @Version 1.0
 **/
public class Md5 {
    /**
     * @Author DJDU 利用MD5进行加密
     * @Description TODO
     * @Date 2019/3/11 11:44 
     * @Param [str]
     * @return java.lang.String
     **/
    public static String EncoderByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        //确定计算方法
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BASE64Encoder base64en = new BASE64Encoder();
        //加密后的字符串
        String newstr = base64en.encode(md5.digest(str.getBytes("utf-8")));
        return newstr;
    }

    /**
     * @Author DJDU
     * @Description TODO 判断用户密码是否正确
     * @Date 2019/3/11 11:44
     * @Param [newpasswd 用户输入的密码, oldpasswd 正确密码]
     * @return boolean
     **/
    public static boolean checkpassword(String newpasswd,String oldpasswd) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        if(EncoderByMd5(newpasswd).equals(oldpasswd))
            return true;
        else
            return false;
    }
}
