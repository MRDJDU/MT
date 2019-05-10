package com.djdu.config;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @ClassName payConfig
 * @Description TODO
 * @Author DJDU
 * @Date 2019/5/10 3:01
 * @Version 1.0
 **/
public class payConfig {
    // ↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016100100636908";//例：2016082600317257

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCuN0O5AFwPUF333QEl3ztZVzEjVt3rWyWjt7faY5eLi5NhY9YXbMzwU8szwejAIDTTHDiDXXER7UnjXExg4+FfbihcCuujmElpjcp0V9SOJJIihDgtV1V+ORS7oLKLbYuce3QgsZDoEjnn+Q8D3gnIE2vmaft1EQC8AshxXmF1dGskvIeNvh9oq/c3THHLBortmZ9R1Bfiw3tMrnGtUJpnqfgUIVzV2yoN91r8n9nfl7AYH+aAdrKTiG4qDnhvjMGAytOKf13tUneBrSi8ZFfzN5TTDisw8wT9Nc++O6kGM5aSYKmrBJbAAwCJqBvOv5JTRGsY89Q+SRpOid0OmRqfAgMBAAECggEAHtoeniaYJwYefgSfbWuJARtV0mJRzppHoiSo5H36mnXPVdHuWhukd3L/a10NsOiDMlevEmR736bcDO2NgSMaCGJjt1kfvcr8RTsc/VmIhNupHX+4yx7KPx4ws8GTnOl7bezW9es8NDz2EESlChRKOyplU1q1c1fdf24mvRzvuXLnppTTbgAN1MTRPW6ROeXjzmNHGUeN1xH+ktlUdksMf8lFEX1jE3GGqXunGlGcqCfDfxX7ul04lAmlCN8dDIzUCJ9advJlR2TIbWuS9Su3S708rvKaXifEAyfuOSNVozqgkzhu4FQXEn4isTkE9/LMs5nQAnNt/a5zFXkP+rljYQKBgQDamKbi1U5mCVlaAMBpyFMMrWp3KKWo6EbGqKEaNuKSgteOlAkF+Ocl2JixIRdqxVQu8lPaPvH62EP/1zz8h4N3OWVdINngGwl9Eay7nZ6cCzSpixxBZRbtq9KqrxyeXIo5wBWRaA7KN/aHt6FWqLPb2R1CvdTisHWYhQ21c9fedwKBgQDMBpTqUN2TD/IAd3HP+V5E8GZxxtWa50Gv/Xm/Z/iI6YDTDROx2rCmfFgT0pseBi6aAoQ15JdQQPmtIc2ah5p1ydu2EG09X7d9llZxQCcV9Q7G1n8kDkACFAZAhOsZ3rno+oELoLpBu4EhWrOQlg8jsoVO0YnH1zlaFc0HoDHnGQKBgCTexpdP/y86uHVz1wCAp3Fl9qUul/KLaxSq8LFpPIIVy6Hi2jrFpsc9Z1PJDxgVPIPGn9oZ79qti5qlhUXa3ZPIfI5esiEYrlsIFih3B3+ubC2EpzlogZuI9bmQW7ViSnY/LeQetw/orI5rrKzfO6cDUeol9rhdpxgYqR1H8ss1AoGBAIv53AMiddImt26whCoeuRltIavfsb8z9/IpYnT6fR5ZrWOe7iswkc+3Mapv7LJdpW1OWqyq51FNMr8gmPHzJPq5Af9YJ8pauAX2tdm/HNHIrlmm4dW33RxP5rYvOyklkMER1KVIoWDlpsNeeiGxgjOIkEMyKWgs9hEjMeaQdoxJAoGBAJG7XpewQ2rv37J4VzwJigULxryJk5PCQIsIVTsDW9/zlGvTX7MX7KCAHDyEF/Tnd7Fhw/0vhirw9QezXTBSKAUDZ3VE8XVDgPeYy8tdmkb2PcY/o260lAC30dl3TiK85KRQ+QLw03S3FMuZZdqvIqwWh9hiIOjh2dgH0g7Y0lSw";
    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm
    // 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEArjdDuQBcD1Bd990BJd87WVcxI1bd61slo7e32mOXi4uTYWPWF2zM8FPLM8HowCA00xw4g11xEe1J41xMYOPhX24oXArro5hJaY3KdFfUjiSSIoQ4LVdVfjkUu6Cyi22LnHt0ILGQ6BI55/kPA94JyBNr5mn7dREAvALIcV5hdXRrJLyHjb4faKv3N0xxywaK7ZmfUdQX4sN7TK5xrVCaZ6n4FCFc1dsqDfda/J/Z35ewGB/mgHayk4huKg54b4zBgMrTin9d7VJ3ga0ovGRX8zeU0w4rMPME/TXPvjupBjOWkmCpqwSWwAMAiagbzr+SU0RrGPPUPkkaTondDpkanwIDAQAB";
    // 服务器异步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    /**
     * 返回的时候此页面不会返回到用户页面，只会执行你写到控制器里的地址
     */
    public static String notify_url = "localhost:8080/mt/notify_url";
    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    /**
     * 此页面是同步返回用户页面，也就是用户支付后看到的页面，上面的notify_url是异步返回商家操作，谢谢
     * 要是看不懂就找度娘，或者多读几遍，或者去看支付宝第三方接口API，不看API直接拿去就用，遇坑不怪别人
     */
    public static String return_url = "localhost:8080/mt/return_url";
    // 签名方式
    public static String sign_type = "RSA2";
    // 字符编码格式
    public static String charset = "gbk";
    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
    // 日志地址
    public static String log_path = "D:/logs/";
    // ↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     *
     * @param sWord
     *            要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_"
                    + System.currentTimeMillis() + ".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
