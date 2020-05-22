package com.hgw.homework.secondmodel.test;

import com.hgw.homework.secondmodel.POJO.CardTypeEnum;
import com.hgw.homework.secondmodel.POJO.SimCard;

/**
 * @author 追风同学
 * @date 2020/05/17 8:29
 * @description
 */
public class SimCardTest {
    public static void main(String[] args) {
        SimCard simCard = new SimCard(CardTypeEnum.BIG_CARD, "13888888888", "拉勾", "123456", 12.58, 50, 1000);
        simCard.show();
    }
}
