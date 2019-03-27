package com.djdu.common.Tool;

import org.apache.commons.codec.binary.Base64;

import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * @ClassName Base64ToImg
 * @Description TODO 前台传入的Base64转为img然后存储
 * @Author DJDU
 * @Date 2019/3/24 21:22
 * @Version 1.0
 **/
public class Base64ToImg {
    /**
     * @Author DJDU
     * @Description TODO 传入分割好的base64数据和指定的目录就可以将其转换为图片存储在指定路径
     * @Date 2019/3/25 0:17
     * @Param [imgStr, imageName]
     * @return boolean
     **/
    public static boolean GenerateImage(String imgStr,String imageName){
        //对字节数组字符串进行Base64解码并生成图片
        if (imgStr == null) //图像数据为空
            return false;
        try{
            //Base64解码
            byte[] b = Base64.decodeBase64(imgStr.replace("data:image/png;base64,",""));
            imgStr = imgStr.replace("base64,","");
            for(int i=0;i<b.length;++i) {
                if(b[i]<0){
                    //调整异常数据
                    b[i]+=256;
                }
            }
            //生成jpeg图片
            String imgFilePath = imageName;//新生成的图片
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
