package com.xjc.payment.service;

import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConstants;
import com.github.wxpay.sdk.WXPayUtil;
import com.xjc.payment.api.WXPaymentApi;
import com.xjc.payment.bean.*;
import com.xjc.payment.config.Constant;
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
public class WXPaymentService implements WXPaymentApi {

    private static final Logger logger = LoggerFactory.getLogger(WXPaymentService.class);

    private Result checkResult(Map<String, String> resultMap, Result result) {
        String returnCode = resultMap.get(Constant.RETURN_CODE);
        String returnMsg = resultMap.get(Constant.RETURN_MSG);
        if (!WXPayConstants.SUCCESS.equals(returnCode) || !Constant.OK.equals(returnMsg)) {
            return result.fail();
        }
        return result.success();
    }

    @Override
    public Result<WXUnifiedorderVo> unifiedorder(WXUnifiedOrderBean bean) {
        Result<WXUnifiedorderVo> result = new Result<>();
        WXUnifiedorderVo unifiedorderVo = new WXUnifiedorderVo();
        try {
            PayConfig config = new PayConfig();
            WXPay wxpay = new WXPay(config);
            Map<String, String> paramMap = wxpay.fillRequestData(new HashMap<>(13));
            paramMap.put(Constant.BODY, bean.getBody());
            paramMap.put(Constant.OUT_TRADE_NO, bean.getOutTradeNo());
            paramMap.put(Constant.TOTAL_FEE, String.valueOf(ParameUtil.covertFee(bean.getTotalFee())));
            paramMap.put(Constant.SPBILL_CREATE_IP, bean.getSpbillCreateIp());
            paramMap.put(Constant.NOTIFY_URL, bean.getNotifyUrl());
            paramMap.put(Constant.TRADE_TYPE, bean.getTradeType());
            paramMap.put(Constant.PRODUCT_ID, bean.getProductId());
            paramMap.put(Constant.OPEN_ID, bean.getOpenId());
            Map<String, String> unifiedOrderMap = wxpay.unifiedOrder(paramMap, 1000 * 60 * 3, 1000 * 60 * 3);
            if (!checkResult(unifiedOrderMap, result).isSuccess()) {
                return new Result<>(result);
            }

            unifiedorderVo.setPrepayId(unifiedOrderMap.get(Constant.PREPAY_ID));
            unifiedorderVo.setTradeType(unifiedOrderMap.get(Constant.TRADE_TYPE));
            result.success(unifiedorderVo);
        } catch (Exception e) {
            result.fail();
        }

        return result;
    }


    @Override
    public Result<WXOrderRefundVo> refund(WXRefundOrderBean bean) {
        Result<WXOrderRefundVo> result = new Result<>();

        try {
            PayConfig config = new PayConfig();
            WXPay wxpay = new WXPay(config);

            Map<String, String> paramMap = wxpay.fillRequestData(new HashMap<>(10));
            paramMap.put(Constant.OUT_REFUND_NO, bean.getOutRefundNo());
            paramMap.put(Constant.OUT_TRADE_NO, bean.getOutTradeNo());
            paramMap.put(Constant.TOTAL_FEE, String.valueOf(ParameUtil.covertFee(bean.getTotalFee())));
            paramMap.put(Constant.REFUND_FEE, String.valueOf(ParameUtil.covertFee(bean.getRefundFee())));
            paramMap.put(Constant.CASH_FEE, String.valueOf(ParameUtil.covertFee(bean.getRefundFee())));
            Map<String, String> refundMap = wxpay.refund(paramMap, 1000 * 60 * 3, 1000 * 60 * 3);
            if (!checkResult(refundMap, result).isSuccess()) {
                return new Result<>(result);
            }

            WXOrderRefundVo wxOrderRefundVo = new WXOrderRefundVo();
            wxOrderRefundVo.setOutTradeNo(refundMap.get(Constant.OUT_TRADE_NO));
            wxOrderRefundVo.setOutRefundNo(refundMap.get(Constant.OUT_REFUND_NO));
            wxOrderRefundVo.setRefundId(refundMap.get(Constant.REFUND_ID));
            wxOrderRefundVo.setRefundFee(ParameUtil.covertFee(Integer.valueOf(refundMap.get(Constant.REFUND_FEE))));
            result.success(wxOrderRefundVo);

        } catch (Exception e) {
            result.fail();
        }

        return result;
    }

    @Override
    public Result closeOrder(WXCloseOrderBean bean) {
        Result result = new Result();

        try {
            PayConfig config = new PayConfig();
            WXPay wxpay = new WXPay(config);
            Map<String, String> paramMap = wxpay.fillRequestData(new HashMap<>(6));
            paramMap.put(Constant.OUT_TRADE_NO, bean.getOutTradeNo());
            Map<String, String> closeOrderMap = wxpay.closeOrder(paramMap, 1000 * 60 * 3, 1000 * 60 * 3);
            if (!checkResult(closeOrderMap, result).isSuccess()) {
                return new Result(result);
            }
        } catch (Exception e) {
            result.fail();
        }

        return result.success();
    }

    @Override
    public Result<WXOrderQueryVo> orderQuery(WXOrderQueryBean bean) {
        Result<WXOrderQueryVo> result = new Result<>();

        try {
            PayConfig config = new PayConfig();
            WXPay wxpay = new WXPay(config);
            Map<String, String> paramMap = wxpay.fillRequestData(new HashMap<>(6));
            paramMap.put(Constant.OUT_TRADE_NO, bean.getOutTradeNo());
            Map<String, String> orderQueryMap = wxpay.orderQuery(paramMap, 1000 * 60 * 3, 1000 * 60 * 3);
            if (!checkResult(orderQueryMap, result).isSuccess()) {
                return new Result<>(result);
            }

            WXOrderQueryVo wxOrderQueryVo = new WXOrderQueryVo();
            wxOrderQueryVo.setOpenId(orderQueryMap.get(Constant.OPEN_ID));
            wxOrderQueryVo.setTradeType(orderQueryMap.get(Constant.TRADE_TYPE));
            wxOrderQueryVo.setTradeState(orderQueryMap.get(Constant.TRADE_STATE));
            wxOrderQueryVo.setBankType(orderQueryMap.get(Constant.BANK_TYPE));
            wxOrderQueryVo.setTotalFee(ParameUtil.covertFee(Integer.valueOf(orderQueryMap.get(Constant.TOTAL_FEE))));
            wxOrderQueryVo.setCashFee(ParameUtil.covertFee(Integer.valueOf(orderQueryMap.get(Constant.CASH_FEE))));
            wxOrderQueryVo.setTransactionId(orderQueryMap.get(Constant.TRANSACTION_ID));
            wxOrderQueryVo.setOutTradeNo(orderQueryMap.get(Constant.OUT_TRADE_NO));
            wxOrderQueryVo.setTimeEnd(orderQueryMap.get(Constant.TIME_END));
            result.success(wxOrderQueryVo);

        } catch (Exception e) {
            result.fail();
        }

        return result;
    }

    @Override
    public Result<WXRefundQueryVo> refundQuery(WXRefundQueryBean bean) {
        Result<WXRefundQueryVo> result = new Result<>();

        try {
            PayConfig config = new PayConfig();
            WXPay wxpay = new WXPay(config);
            Map<String, String> paramMap = wxpay.fillRequestData(new HashMap<>(6));
            paramMap.put(Constant.OUT_REFUND_NO, bean.getOutRefundNo());
            Map<String, String> refundQueryMap = wxpay.refundQuery(paramMap, 1000 * 60 * 3, 1000 * 60 * 3);
            if (checkResult(refundQueryMap, result).isSuccess()) {
                return new Result<>(result);
            }

            WXRefundQueryVo wxRefundQueryVo = new WXRefundQueryVo();
            wxRefundQueryVo.setOutTradeNo(refundQueryMap.get(Constant.OUT_TRADE_NO));
            wxRefundQueryVo.setOutRefundNo(refundQueryMap.get(Constant.TOUT_REFUND_NO_$N));
            wxRefundQueryVo.setTotalFee(refundQueryMap.get(Constant.TOTAL_FEE));
            wxRefundQueryVo.setRefundFee(refundQueryMap.get(Constant.REFUND_FEE_$N));
            wxRefundQueryVo.setRefundChanne(refundQueryMap.get(Constant.REFUND_CHANNEL_$N));
            wxRefundQueryVo.setRefundStatus(refundQueryMap.get(Constant.REFUND_STATUS_$N));
            wxRefundQueryVo.setRefundRecvAccout(refundQueryMap.get(Constant.REFUND_RECV_ACCOUT_$N));
            wxRefundQueryVo.setRefundSuccessTime(refundQueryMap.get(Constant.REFUND_SUCCESS_TIME_$N));
            result.success(wxRefundQueryVo);

        } catch (Exception e) {
            result.fail();
        }

        return result;
    }

    @Override
    public Result<WXPayOrderNotifyVo> payOrderNotify(HttpServletRequest request) {
        Result<WXPayOrderNotifyVo> result = new Result<>();

        WXPayOrderNotifyVo wxPayOrderNotifyVo = new WXPayOrderNotifyVo();

        try {
            PayConfig payConfig = new PayConfig();
            WXPay wxpay = new WXPay(payConfig);
            String readData = HttpUtil.readData(request);

            Map<String, String> payOrderNotifyMap = WXPayUtil.xmlToMap(readData);

            String returnCode = payOrderNotifyMap.get(Constant.RETURN_CODE);
            String returnMsg = payOrderNotifyMap.get(Constant.RETURN_MSG);
            if (!WXPayConstants.SUCCESS.equals(returnCode) || !Constant.OK.equals(returnMsg)) {
                wxPayOrderNotifyVo.setWxResult(ParameUtil.setReturnXml(WXPayConstants.FAIL, "return_code不为success"));
                return result.fail(wxPayOrderNotifyVo);
            }
            if (!wxpay.isResponseSignatureValid(payOrderNotifyMap)) {
                wxPayOrderNotifyVo.setWxResult(ParameUtil.setReturnXml(WXPayConstants.FAIL, "支付结果通知中的sign无效"));
                return result.fail(wxPayOrderNotifyVo);
            }

            wxPayOrderNotifyVo.setOpenId(payOrderNotifyMap.get(Constant.OPEN_ID));
            wxPayOrderNotifyVo.setTradeType(payOrderNotifyMap.get(Constant.TRADE_TYPE));
            wxPayOrderNotifyVo.setBankType(payOrderNotifyMap.get(Constant.BANK_TYPE));
            wxPayOrderNotifyVo.setTotalFee(ParameUtil.covertFee(Integer.valueOf(payOrderNotifyMap.get(Constant.TOTAL_FEE))));
            wxPayOrderNotifyVo.setSettlementTotalFee(ParameUtil.covertFee(Integer.valueOf(payOrderNotifyMap.get(Constant.SETTLEMENT_TOTAL_FEE))));
            wxPayOrderNotifyVo.setOutTradeNo(payOrderNotifyMap.get(Constant.OUT_TRADE_NO));
            wxPayOrderNotifyVo.setTransactionId(payOrderNotifyMap.get(Constant.TRANSACTION_ID));
            wxPayOrderNotifyVo.setTimeEnd(payOrderNotifyMap.get(Constant.TIME_END));
            wxPayOrderNotifyVo.setWxResult(ParameUtil.setReturnXml(WXPayConstants.SUCCESS, Constant.OK));
            result.success(wxPayOrderNotifyVo);

        } catch (Exception e) {
            wxPayOrderNotifyVo.setWxResult(ParameUtil.setReturnXml(WXPayConstants.FAIL, e.getMessage()));
            result.fail(wxPayOrderNotifyVo);
        }

        return result;
    }

    @Override
    public Result<WXRefundOrderNotifyVo> refundOrderNotify(HttpServletRequest request) {
        Result<WXRefundOrderNotifyVo> result = new Result<>();
        WXRefundOrderNotifyVo wxRefundOrderNotifyVo = new WXRefundOrderNotifyVo();

        try {
            PayConfig payConfig = new PayConfig();

            String readData = HttpUtil.readData(request);
            Map<String, String> refundOrderNotifyMap = WXPayUtil.xmlToMap(readData);
            String returnCode = refundOrderNotifyMap.get(Constant.RETURN_CODE);
            String returnMsg = refundOrderNotifyMap.get(Constant.RETURN_MSG);
            if (!WXPayConstants.SUCCESS.equals(returnCode) || !Constant.OK.equals(returnMsg)) {
                wxRefundOrderNotifyVo.setWxResult(ParameUtil.setReturnXml(WXPayConstants.FAIL, "return_code不为success"));
                return result.fail(wxRefundOrderNotifyVo);
            }

            String reqInfo = refundOrderNotifyMap.get(Constant.REQ_INFO);
            String refundDecrypt = ParameUtil.getRefundDecrypt(reqInfo, payConfig.getAPPSECRET());
            Map<String, String> toMap = WXPayUtil.xmlToMap(refundDecrypt);
            String refundStatus = toMap.get(Constant.REFUND_STATUS);
            if (!WXPayConstants.SUCCESS.equals(refundStatus)) {
                wxRefundOrderNotifyVo.setWxResult(ParameUtil.setReturnXml(WXPayConstants.FAIL, "退款失败"));
                return result.fail(wxRefundOrderNotifyVo);
            }
            String returnXmlMessage = ParameUtil.setReturnXml(WXPayConstants.SUCCESS, Constant.OK);

            wxRefundOrderNotifyVo.setTransactionId(toMap.get(Constant.TRANSACTION_ID));
            wxRefundOrderNotifyVo.setOutTradeNo(toMap.get(Constant.OUT_TRADE_NO));
            wxRefundOrderNotifyVo.setRefundId(toMap.get(Constant.REFUND_ID));
            wxRefundOrderNotifyVo.setOutRefundNo(toMap.get(Constant.OUT_REFUND_NO));
            wxRefundOrderNotifyVo.setTotalFee(ParameUtil.covertFee(Integer.valueOf(toMap.get(Constant.TOTAL_FEE))));
            wxRefundOrderNotifyVo.setSettlementTotalFee(ParameUtil.covertFee(Integer.valueOf(toMap.get(Constant.SETTLEMENT_TOTAL_FEE))));
            wxRefundOrderNotifyVo.setRefundFee(ParameUtil.covertFee(Integer.valueOf(toMap.get(Constant.REFUND_FEE))));
            wxRefundOrderNotifyVo.setSettlementRefundFee(ParameUtil.covertFee(Integer.valueOf(toMap.get(Constant.SETTLEMENT_REFUND_FEE))));
            wxRefundOrderNotifyVo.setRefundStatus(refundStatus);
            wxRefundOrderNotifyVo.setTimeEnd(toMap.get(Constant.SUCCESS_TIME));
            wxRefundOrderNotifyVo.setRefundRecvAccout(toMap.get("refund_recv_accout"));
            wxRefundOrderNotifyVo.setWxResult(returnXmlMessage);
            result.success(wxRefundOrderNotifyVo);

            logger.info("[refundAsyncNotify]  [out_trade_no:{}] [out_refund_no:{}] [退款异步消息处理成功:{}]",
                    toMap.get("out_trade_no"), toMap.get("out_refund_no"), returnXmlMessage);

        } catch (Exception e) {
            wxRefundOrderNotifyVo.setWxResult(ParameUtil.setReturnXml(WXPayConstants.FAIL, e.getMessage()));
            result.fail(wxRefundOrderNotifyVo);
        }

        return result;
    }

    @Override
    public Result<String> downloadbill(DownloadbillBean bean) {
        Result<String> result = new Result<>();

        try {
            PayConfig payConfig = new PayConfig();
            WXPay wxPay = new WXPay(payConfig);
            Map<String, String> paramMap = wxPay.fillRequestData(new HashMap<>(7));
            paramMap.put(Constant.BILL_DATE, bean.getBillDate());
            paramMap.put(Constant.BILL_TYPE, bean.getBillType());
            Map<String, String> downloadBillMap = wxPay.downloadBill(paramMap, 1000 * 60 * 3, 1000 * 60 * 3);
            if (!checkResult(downloadBillMap, result).isSuccess()) {
                result.fail();
            }

            result.success(downloadBillMap.get("data"));

        } catch (Exception e) {
            result.fail();
        }

        return result;
    }
}
