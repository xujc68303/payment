package com.xjc.payment.api;

import com.xjc.payment.bean.*;
import com.xjc.payment.result.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author jiachenxu
 * @Date 2022/2/27
 * @Descripetion
 */
public interface WXPaymentApi {

    /**
     * 统一下单
     *
     * @param bean
     * @return
     */
    Result<WXUnifiedorderVo> unifiedorder(WXUnifiedOrderBean bean);

    /**
     * 退款申请
     *
     * @param bean
     * @return
     */
    Result<WXOrderRefundVo> refund(WXRefundOrderBean bean);

    /**
     * 关闭订单
     *
     * @param bean
     * @return
     */
    Result closeOrder(WXCloseOrderBean bean);

    /**
     * 根据微信商户订单号查询订单
     *
     * @param bean
     * @return
     */
    Result<WXOrderQueryVo> orderQuery(WXOrderQueryBean bean);

    /**
     * 根据商户退款单号查询退款
     *
     * @param bean
     * @return
     */
    Result<WXRefundQueryVo> refundQuery(WXRefundQueryBean bean);

    /**
     * 下单回调处理
     *
     * @param request
     * @return
     */
    Result<WXPayOrderNotifyVo> payOrderNotify(HttpServletRequest request);

    /**
     * 退款回调处理
     *
     * @param request
     * @return
     */
    Result<WXRefundOrderNotifyVo> refundOrderNotify(HttpServletRequest request);

    /**
     * 下载交易账单
     *
     * @param bean
     * @return
     */
    Result<String> downloadbill(DownloadbillBean bean);
}
