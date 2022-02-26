package com.xjc.payment.config;

import com.github.wxpay.sdk.WXPayConfig;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @Author jiachenxu
 * @Date 2022/2/26
 * @Descripetion
 */
public class PayConfig implements WXPayConfig {

    private byte[] certData;


    /**
     * 微信退款所需要的配置! 退款只需要证书即可。
     * @throws Exception
     */
    public PayConfig() throws Exception {
        //部署服务器用到的路径 绝对路径。
        //String certPath = "/usr/local/app/tomcat-dev/webapps/shop/WEB-INF/classes/apiclient_cert.p12";//从微信商户平台下载的安全证书存放的目录
        //本地用到的路径 相对路径
        String certPath = "src/main/resources/apiclient_cert.p12";
        File file = new File(certPath);
        InputStream certStream = new FileInputStream(file);
        this.certData = new byte[(int) file.length()];
        certStream.read(this.certData);
        certStream.close();
    }

    @Override
    public String getAppID() {
        return "";  //appid
    }

    public String getAPPSECRET(){
        return "";  //appSecret
    }

    @Override
    public String getMchID() {
        return "";   //商户号id
    }

    @Override
    public String getKey() {
        return "";  //支付API密钥
    }

    public String getNotifyURL(){
        return "";
    }

    @Override
    public InputStream getCertStream() {
        ByteArrayInputStream certBis = new ByteArrayInputStream(this.certData);
        return certBis;
    }

    @Override
    public int getHttpConnectTimeoutMs() {
        return 8000;
    }

    @Override
    public int getHttpReadTimeoutMs() {
        return 10000;
    }

}
