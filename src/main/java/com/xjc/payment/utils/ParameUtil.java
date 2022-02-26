package com.xjc.payment.utils;

import org.apache.commons.codec.Charsets;
import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.util.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.Security;
import java.util.Base64;
import java.util.Map;
import java.util.TreeMap;

/**
 * @Author jiachenxu
 * @Date 2022/2/26
 * @Descripetion
 */
public class ParameUtil {

//    /**
//     * 组装签名的字段
//     * @param params 参数
//     * @param urlEncoder 是否urlEncoder
//     * @return String
//     */
//    public static String packageSign(Map<String, String> params, boolean urlEncoder) {
//        // 先将参数以其参数名的字典序升序进行排序
//        TreeMap<String, String> sortedParams = new TreeMap<String, String>(params);
//        // 遍历排序后的字典，将所有参数按"key=value"格式拼接在一起
//        StringBuilder sb = new StringBuilder();
//        boolean first = true;
//        for (Map.Entry<String, String> param : sortedParams.entrySet()) {
//            String value = param.getValue();
//            if (!StringUtils.hasText(value)) {
//                continue;
//            }
//            if (first) {
//                first = false;
//            } else {
//                sb.append("&");
//            }
//            sb.append(param.getKey()).append("=");
//            if (urlEncoder) {
//                try { value = urlEncode(value); } catch (UnsupportedEncodingException e) {}
//            }
//            sb.append(value);
//        }
//        return sb.toString();
//    }
//
//    /**
//     * urlEncode
//     * @param src 微信参数
//     * @return String
//     * @throws UnsupportedEncodingException 编码错误
//     */
//    public static String urlEncode(String src) throws UnsupportedEncodingException {
//        return URLEncoder.encode(src, Charsets.UTF_8.name()).replace("+", "%20");
//    }
//
//    /**
//     * 生成签名
//     * @param params 参数
//     * @param paternerKey 支付密钥
//     * @return sign
//     */
//    public static String createSign(Map<String, Object> params, String paternerKey) {
//        // 生成签名前先去除sign
//        params.remove("sign");
//        String stringA = packageSign(params, false);
//        String stringSignTemp = stringA + "&key=" + paternerKey;
//        return MD5Util.md5(stringSignTemp).toUpperCase();
//    }
//
//    /**
//     * 支付异步通知时校验sign
//     * @param params 参数
//     * @param paternerKey 支付密钥
//     * @return {boolean}
//     */
//    public static boolean verifyNotify(Map<String, Object> params, String paternerKey){
//        String sign = params.get("sign");
//        String localSign = ParameUtil.createSign(params, paternerKey);
//        return sign.equals(localSign);
//    }

    /**
     * 微信下单，map to xml
     * @param params 参数
     * @return String
     */
    public static String toXml(Map<String, Object> params) {
        StringBuilder xml = new StringBuilder();
        xml.append("<xml>");
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            String key   = entry.getKey();
            Object value = entry.getValue();
            // 略过空值
            if (StringUtils.isEmpty(value)) {
                continue;
            }
            xml.append("<").append(key).append(">");
            xml.append(entry.getValue());
            xml.append("</").append(key).append(">");
        }
        xml.append("</xml>");
        return xml.toString();
    }

    /**
     * 针对支付的xml，没有嵌套节点的简单处理
     * @param xmlStr xml字符串
     * @return map集合
     */
    public static Map<String, String> xmlToMap(String xmlStr) {
        XmlUtil xmlUtil = XmlUtil.of(xmlStr);
        return xmlUtil.toMap();
    }

    /**
     * AES-256-ECB(PKCS7Padding)解密
     * @param secretInfo 秘文
     * @param rawKey 商户的秘钥
     */
    public static String aesDecrypt(String secretInfo, String rawKey) {
        try {
            SecretKeySpec key = new SecretKeySpec(DigestUtils.md5Hex(getContentBytes(rawKey, "utf-8")).toLowerCase().getBytes(), "AES");
            Security.addProvider(new BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding", "BC");
            cipher.init(Cipher.DECRYPT_MODE, key);
            return new String(cipher.doFinal(Base64.getDecoder().decode(secretInfo)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static byte[] getContentBytes(String content, String charset) {
        if (charset == null || "".equals(charset)) {
            return content.getBytes();
        }
        try {
            return content.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("MD5签名过程中出现错误" + charset);
        }
    }



}
