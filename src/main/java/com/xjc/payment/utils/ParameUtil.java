package com.xjc.payment.utils;

import com.xjc.payment.config.Constant;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.util.DigestUtils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigDecimal;
import java.security.Security;
import java.util.Base64;

import static java.math.BigDecimal.ROUND_HALF_UP;

/**
 * @Author jiachenxu
 * @Date 2022/2/26
 * @Descripetion
 */
public class ParameUtil {

    public static String getRefundDecrypt(String reqInfoSecret, String key) {
        String result = "";
        try {
            Security.addProvider(new BouncyCastleProvider());
            byte[] bytes = Base64.getDecoder().decode(reqInfoSecret);
            String md5key = DigestUtils.md5DigestAsHex(key.getBytes()).toLowerCase();
            SecretKey secretKey = new SecretKeySpec(md5key.getBytes(), Constant.AES);
            Cipher cipher = Cipher.getInstance(Constant.ALGORITHMMODEPADDING, Constant.BC);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] resultbt = cipher.doFinal(bytes);
            result = new String(resultbt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static Integer covertFee(BigDecimal param) {
        return param.multiply(new BigDecimal("100")).intValue();
    }

    public static BigDecimal covertFee(Integer param) {
        return new BigDecimal(String.valueOf(Double.parseDouble(param.toString()) / 100)).setScale(2, ROUND_HALF_UP);
    }

    /**
     * 设置返回给微信服务器的xml信息
     *
     * @param returnCode
     * @param returnMsg
     * @return
     */
    public static String setReturnXml(String returnCode, String returnMsg) {
        return "<xml><return_code><![CDATA[" + returnCode + "]]></return_code><return_msg><![CDATA[" + returnMsg + "]]></return_msg></xml>";
    }

}
