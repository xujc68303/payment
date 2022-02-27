package com.xjc.payment.bean;

import com.xjc.payment.enums.BillTypeEnum;

import java.io.Serializable;

/**
 * @Author jiachenxu
 * @Date 2022/2/27
 * @Descripetion
 */
public class DownloadbillBean implements Serializable {

    /**
     * 对账单日期
     */
    private String billDate;

    /**
     * 账单类型
     * @see BillTypeEnum
     */
    private String billType;

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }
}
