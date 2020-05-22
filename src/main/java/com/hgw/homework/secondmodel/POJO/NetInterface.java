package com.hgw.homework.secondmodel.POJO;

/**上网服务接口
 * @author 追风同学
 * @date 2020/05/17 0:14
 * @description
 */
public interface NetInterface {
    /**
     * 上网服务方法
     * @param internetData 上网流量
     * @param simCard 手机卡类对象
     */
    public abstract void surf(double internetData, SimCard simCard);
}
