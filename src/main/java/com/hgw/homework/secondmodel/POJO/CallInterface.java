package com.hgw.homework.secondmodel.POJO;

/**通话服务接口
 * @author 追风同学
 * @date 2020/05/17 0:14
 * @description
 */
public interface CallInterface {
    /**
     * 通话服务的方法
     * @param callTime 通话分钟
     * @param simCard 手机卡类对象
     */
    public abstract void call(int callTime, SimCard simCard);
}
