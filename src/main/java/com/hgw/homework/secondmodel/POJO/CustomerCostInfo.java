package com.hgw.homework.secondmodel.POJO;

/**用户消费信息实体类
 * @author 追风同学
 * @date 2020/05/17 0:00
 * @description
 */
public class CustomerCostInfo {

    /**
     * 通话时长
     */
    private int callTime;
    /**
     * 上网流量
     */
    private  double internetData;
    /**
     * 消费金额
     */
    private double price;

    public CustomerCostInfo() {
    }

    public CustomerCostInfo(int callTime, double internetData, double price) {
        this.callTime = callTime;
        this.internetData = internetData;
        this.price = price;
    }

    public int getCallTime() {
        return callTime;
    }

    public void setCallTime(int callTime) {
        this.callTime = callTime;
    }

    public double getInternetData() {
        return internetData;
    }

    public void setInternetData(double internetData) {
        this.internetData = internetData;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
