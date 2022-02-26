package com.xjc.payment.utils;

import com.github.wxpay.sdk.WXPayConstants;
import com.xjc.payment.config.PayConfig;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

/**
 * @Author jiachenxu
 * @Date 2022/2/26
 * @Descripetion
 */
public class MD5Util {

    public static String md5(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte b[] = md.digest();

            int i;

            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            str = buf.toString();
        } catch (Exception e) {
            e.printStackTrace();

        }
        return str;
    }

    public static String getSign(Map<String,String> data) throws Exception{
        PayConfig config = new PayConfig();
        Set<String> keySet = data.keySet();
        String[] keyArray = keySet.toArray(new String[keySet.size()]);
        Arrays.sort(keyArray);
        StringBuilder sb = new StringBuilder();
        for(String k : keyArray){
            if(k.equals(WXPayConstants.FIELD_SIGN)){
                continue;
            }
            if(data.get(k).trim().length() > 0) // 参数值为空，则不参与签名
                sb.append(k).append("=").append(data.get(k).trim()).append("&");
        }
        sb.append("key=").append(config.getKey());
        MessageDigest md = null;
        try{
            md = MessageDigest.getInstance("MD5");
        }catch(NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        byte[] array = new byte[0];
        array = md.digest(sb.toString().getBytes(StandardCharsets.UTF_8));
        StringBuilder sb2 = new StringBuilder();
        for(byte item : array){
            sb2.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1 , 3));
        }
        System.out.println("签名为:"+sb2);
        return sb2.toString().toUpperCase();
    }

}
