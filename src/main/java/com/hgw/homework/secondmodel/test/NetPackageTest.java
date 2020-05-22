package com.hgw.homework.secondmodel.test;

import com.hgw.homework.secondmodel.POJO.CardTypeEnum;
import com.hgw.homework.secondmodel.POJO.NetPackage;
import com.hgw.homework.secondmodel.POJO.SimCard;

/**
 * @author 追风同学
 * @date 2020/05/17 8:40
 * @description
 */
public class NetPackageTest {

    public static void main(String[] args) {

        SimCard simCard=new SimCard(CardTypeEnum.MICRO_CARD, "13888888888", "上网卡", "123456", 12.58, 50, 1000);
        NetPackage netPackage=new NetPackage(2500,35);
        System.out.println("---------显示行为的测试----------");
        netPackage.show();
        System.out.println("----------上网服务的测试----------");
        netPackage.surf(1000,simCard);
    }
}
