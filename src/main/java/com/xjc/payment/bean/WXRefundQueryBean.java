package com.xjc.payment.bean;

import java.io.Serializable;

/**
 * @Author jiachenxu
 * @Date 2022/2/26
 * @Descripetion
 */
public class WXRefundQueryBean implements Serializable {
    /**
     * 商户退款单号
     */
    private String outRefundNo;

    public String getOutRefundNo() {
        return outRefundNo;
    }

    public void setOutRefundNo(String outRefundNo) {
        this.outRefundNo = outRefundNo;
    }
}
