package com.hgw.homework.secondmodel.POJO;

/**手机卡实体类
 * @author 追风同学
 * @date 2020/05/16 23:43
 * @description
 */
public class SimCard {
    /**
     * 卡类型
     */
    private CardTypeEnum cardType;
    /**
     * 卡号
     */
    private String cardNumber;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     */
    private String passWord;
    /**
     * 账户余额
     */
    private double balance;
    /**
     * 通话时长(分钟)
     */
    private int callTime;
    /**
     * 上网流量
     */
    private double internetData;

    public SimCard() {
    }

    public SimCard(CardTypeEnum cardType, String cardNumber, String userName, String passWord, double balance, int callTime, double internetData) {
        this.cardType = cardType;
        this.cardNumber = cardNumber;
        this.userName = userName;
        this.passWord = passWord;
        this.balance = balance;
        this.callTime = callTime;
        this.internetData = internetData;
    }

    public CardTypeEnum getCardType() {
        return cardType;
    }

    public void setCardType(CardTypeEnum cardType) {
        this.cardType = cardType;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
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

    /**
     * 显示
     */
    public void show(){
        System.out.println("卡号为: "+cardNumber+";");
        System.out.println("用户名为: "+userName+";");
        System.out.println("当前余额为: "+balance+";");
    }

}
