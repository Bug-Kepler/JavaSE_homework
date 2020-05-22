package com.hgw.homework.secondmodel.POJO;

/**上网套餐实体类
 * @author 追风同学
 * @date 2020/05/16 23:58
 * @description
 */
public class NetPackage extends Package implements NetInterface {
    /**
     * 上网流量
     */
    private double internetData;
    /**
     * 每月资费
     */
    private double price;

    public NetPackage() {
    }

    public NetPackage(double internetData, double price) {
        this.internetData = internetData;
        this.price = price;
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

    @Override
    public void show(){
        System.out.println("上网流量为:"+internetData);
        System.out.println("每月资费:"+price);
    }

    @Override
    public void surf(double internetData, SimCard simCard) {
        System.out.println("上网流量:"+internetData);
        System.out.println("手机卡信息:");
        simCard.show();
    }
}
