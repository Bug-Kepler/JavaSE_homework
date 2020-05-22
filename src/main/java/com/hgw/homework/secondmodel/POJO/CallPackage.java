package com.hgw.homework.secondmodel.POJO;

/**通话套餐实体类
 * @author 追风同学
 * @date 2020/05/16 23:51
 * @description
 */
public class CallPackage extends Package implements CallInterface {
    /**
     * 通话时长
     */
    private int callTime;
    /**
     * 短信条数
     */
    private int messageNum;
    /**
     * 每月资费
     */
    private double price;

    public CallPackage() {
    }

    public CallPackage(int callTime, int messageNum, double price) {
        this.callTime = callTime;
        this.messageNum = messageNum;
        this.price = price;
    }

    public int getCallTime() {
        return callTime;
    }

    public void setCallTime(int callTime) {
        this.callTime = callTime;
    }

    public int getMessageNum() {
        return messageNum;
    }

    public void setMessageNum(int messageNum) {
        this.messageNum = messageNum;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public void show(){
        System.out.println("通话时长为:"+callTime);
        System.out.println("短信条数为:"+messageNum);
        System.out.println("每月资费为:"+price);
    }

    @Override
    public void call(int callTime, SimCard simCard) {
        System.out.println("通话分钟:"+callTime);
        System.out.println("手机卡信息:");
        simCard.show();
    }
}
