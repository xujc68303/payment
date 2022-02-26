package com.xjc.payment.service;

import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConstants;
import com.github.wxpay.sdk.WXPayUtil;
import com.xjc.payment.bean.*;
import com.xjc.payment.config.PayConfig;
import com.xjc.payment.result.*;
import com.xjc.payment.utils.HttpUtil;
import com.xjc.payment.utils.ParameUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author jiachenxu
 * @Date 2022/2/26
 * @Descripetion
 */
public class WXPaymentService {

    public static final Logger logger = LoggerFactory.getLogger(WXPaymentService.class);

    /**
     * 统一下单
     *
     * @param bean
     * @return
     */
    public Result<WXUnifiedorderVo> unifiedorder(WXUnifiedOrderBean bean) throws Exception {
        Result<WXUnifiedorderVo> resultBean = new Result<>();
        PayConfig config = new PayConfig();
        WXPay wxpay = new WXPay(config);

        Map<String, String> paramMap = wxpay.fillRequestData(new HashMap<>());
        paramMap.put("body", bean.getBody());
        paramMap.put("out_trade_no", bean.getOutTradeNo());
        paramMap.put("fee_type", bean.getTotalFee().toString());
        paramMap.put("spbill_create_ip", bean.getTotalFee().toString());
        paramMap.put("notify_url", bean.getNotifyUrl());
        paramMap.put("trade_type", bean.getTradeType());
        paramMap.put("product_id", bean.getProductId());
        paramMap.put("openid", bean.getOpenId());
        Map<String, String> unifiedOrderMap = wxpay.unifiedOrder(paramMap, 1000 * 60 * 3, 1000 * 60 * 3);
        String returnCode = unifiedOrderMap.get("return_code");
        String returnMsg = unifiedOrderMap.get("return_msg");
        if (!"SUCCESS".equals(returnCode) || !"OK".equals(returnMsg)) {
            return resultBean.fail();
        }

        WXUnifiedorderVo unifiedorderVo = new WXUnifiedorderVo();
        unifiedorderVo.setPrepayId(unifiedOrderMap.get("prepay_id"));
        unifiedorderVo.setTradeType(unifiedOrderMap.get("trade_type"));

        return resultBean.success(unifiedorderVo);
    }


    /**
     * 退款申请
     *
     * @param bean
     * @return
     * @throws Exception
     */
    public Result<WXOrderRefundVo> refund(WXRefundOrderBean bean) throws Exception {
        Result<WXOrderRefundVo> result = new Result<>();
        PayConfig config = new PayConfig();
        WXPay wxpay = new WXPay(config);

        Map<String, String> paramMap = wxpay.fillRequestData(new HashMap<>());

        int refundFee = (int) (Double.parseDouble(bean.getRefundFee().toString()) * 100);
        int totalFee = (int) (Double.parseDouble(bean.getTotalFee().toString()) * 100);
        paramMap.put("out_refund_no", bean.getOutRefundNo());
        paramMap.put("out_trade_no", bean.getOutTradeNo());
        paramMap.put("total_fee", String.valueOf(totalFee));
        paramMap.put("refund_fee", String.valueOf(refundFee));
        paramMap.put("cash_fee", String.valueOf(refundFee));
        Map<String, String> refundMap = wxpay.refund(paramMap, 1000 * 60 * 3, 1000 * 60 * 3);
        String returnCode = refundMap.get("return_code");
        String returnMsg = refundMap.get("return_msg");
        if (!"SUCCESS".equals(returnCode) || !"OK".equals(returnMsg)) {
            return result.fail();
        }

        WXOrderRefundVo wxOrderRefundVo = new WXOrderRefundVo();
        wxOrderRefundVo.setOutTradeNo(refundMap.get("out_trade_no"));
        wxOrderRefundVo.setOutRefundNo(refundMap.get("out_refund_no"));
        wxOrderRefundVo.setRefundId(refundMap.get("refund_id"));
        wxOrderRefundVo.setRefundFee(refundMap.get("refund_fee"));

        return result.success(null);
    }

    /**
     * 关闭订单
     *
     * @return 回调信息
     */
    public Result closeOrder(WXCloseOrderBean bean) throws Exception {
        Result result = new Result();
        PayConfig config = new PayConfig();
        WXPay wxpay = new WXPay(config);
        Map<String, String> paramMap = wxpay.fillRequestData(new HashMap<>());
        paramMap.put("out_trade_no", bean.getOutTradeNo());
        Map<String, String> closeOrderMap = wxpay.closeOrder(paramMap, 1000 * 60 * 3, 1000 * 60 * 3);
        String returnCode = closeOrderMap.get("return_code");
        String returnMsg = closeOrderMap.get("return_msg");
        if (!"SUCCESS".equals(returnCode) || !"OK".equals(returnMsg)) {
            return result.fail();
        }
        return result.success();
    }

    /**
     * 根据微信商户订单号查询订单
     *
     * @return
     */
    public Result<WXOrderQueryVo> orderQuery(WXOrderQueryBean bean) throws Exception {
        Result<WXOrderQueryVo> result = new Result<>();

        PayConfig config = new PayConfig();
        WXPay wxpay = new WXPay(config);
        Map<String, String> paramMap = wxpay.fillRequestData(new HashMap<>());
        paramMap.put("out_trade_no", bean.getOutTradeNo());
        Map<String, String> orderQueryMap = wxpay.orderQuery(paramMap, 1000 * 60 * 3, 1000 * 60 * 3);
        String returnCode = orderQueryMap.get("return_code");
        String returnMsg = orderQueryMap.get("return_msg");
        if (!"SUCCESS".equals(returnCode) || !"OK".equals(returnMsg)) {
            return result.fail();
        }
        WXOrderQueryVo wxOrderQueryVo = new WXOrderQueryVo();
        wxOrderQueryVo.setOpenId(orderQueryMap.get("openid"));
        wxOrderQueryVo.setTradeType(orderQueryMap.get("trade_type"));
        wxOrderQueryVo.setTradeState(orderQueryMap.get("trade_state"));
        wxOrderQueryVo.setBankType(orderQueryMap.get("bank_type"));
        wxOrderQueryVo.setTotalFee(orderQueryMap.get("total_fee"));
        wxOrderQueryVo.setCashFee(orderQueryMap.get("cash_fee"));
        wxOrderQueryVo.setTransactionId(orderQueryMap.get("transaction_id"));
        wxOrderQueryVo.setOutTradeNo(orderQueryMap.get("out_trade_no"));
        wxOrderQueryVo.setTimeEnd(orderQueryMap.get("time_end"));
        return result.success(wxOrderQueryVo);
    }

    /**
     * 根据商户退款单号查询退款
     *
     * @param bean
     * @return
     */
    public Result<WXRefundQueryVo> refundQuery(WXRefundQueryBean bean) throws Exception {
        Result<WXRefundQueryVo> result = new Result<>();

        PayConfig config = new PayConfig();
        WXPay wxpay = new WXPay(config);
        Map<String, String> paramMap = wxpay.fillRequestData(new HashMap<>());
        paramMap.put("out_refund_no", bean.getOutRefundNo());
        Map<String, String> refundQueryMap = wxpay.refundQuery(paramMap, 1000 * 60 * 3, 1000 * 60 * 3);
        String returnCode = refundQueryMap.get("return_code");
        String returnMsg = refundQueryMap.get("return_msg");
        if (!"SUCCESS".equals(returnCode) || !"OK".equals(returnMsg)) {
            return result.fail();
        }
        WXRefundQueryVo wxRefundQueryVo = new WXRefundQueryVo();
        wxRefundQueryVo.setOutTradeNo(refundQueryMap.get("out_trade_no"));
        wxRefundQueryVo.setOutRefundNo(refundQueryMap.get("tout_refund_no_$n"));
        wxRefundQueryVo.setTotalFee(refundQueryMap.get("total_fee"));
        wxRefundQueryVo.setRefundFee(refundQueryMap.get("refund_fee_$n"));
        wxRefundQueryVo.setRefundChanne(refundQueryMap.get("refund_channel_$n"));
        wxRefundQueryVo.setRefundStatus(refundQueryMap.get("refund_status_$n"));
        wxRefundQueryVo.setRefundRecvAccout(refundQueryMap.get("refund_recv_accout_$n"));
        wxRefundQueryVo.setRefundSuccessTime(refundQueryMap.get("refund_success_time_$n"));
        return result.success(wxRefundQueryVo);
    }

    /**
     * 退款回调处理
     *
     * @param request
     * @return
     * @throws Exception
     */
    public Result<String> refundOrderNotify(HttpServletRequest request) {
        Result<String> result = new Result<>();

        String returnXmlMessage;

        try {
            PayConfig payConfig = new PayConfig();

            String readData = HttpUtil.readData(request);
            Map<String, String> refundOrderNotifyMap = WXPayUtil.xmlToMap(readData);
            String returnCode = refundOrderNotifyMap.get("return_code");
            String returnMsg = refundOrderNotifyMap.get("return_msg");
            if (!"SUCCESS".equals(returnCode) || !"OK".equals(returnMsg)) {
                returnXmlMessage = setReturnXml(WXPayConstants.FAIL, "return_code不为success");
                result.setData(returnXmlMessage);
                return result.fail();
            }

            String reqInfo = refundOrderNotifyMap.get("req_info");
            String refundDecrypt = ParameUtil.getRefundDecrypt(reqInfo, payConfig.getKey());
            Map<String, String> toMap = WXPayUtil.xmlToMap(refundDecrypt);

            returnXmlMessage = setReturnXml(WXPayConstants.SUCCESS, "OK");
            result.setData(returnXmlMessage);
            result.success();

            logger.info("[refundAsyncNotify]  [out_trade_no:{}] [out_refund_no:{}] [退款异步消息处理成功:{}]",
                    toMap.get("out_trade_no"), toMap.get("out_refund_no"), returnXmlMessage);

        } catch (Exception e) {
            returnXmlMessage = setReturnXml(WXPayConstants.FAIL, e.getMessage());
            result.setData(returnXmlMessage);
            result.fail();
        }

        return result;
    }

    /**
     * 设置返回给微信服务器的xml信息
     *
     * @param returnCode
     * @param returnMsg
     * @return
     */
    private String setReturnXml(String returnCode, String returnMsg) {
        return "<xml><return_code><![CDATA[" + returnCode + "]]></return_code><return_msg><![CDATA[" + returnMsg + "]]></return_msg></xml>";
    }

}
