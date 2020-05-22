package com.hgw.homework.secondmodel.POJO;

/**手机卡类型的枚举
 * @author 追风同学
 * @date 2020/05/17 0:04
 * @description
 */
public enum CardTypeEnum {
    BIG_CARD("大卡"),SMALL_CARD("小卡"),MICRO_CARD("微型卡");
    private String cardType;

    public String getCardType() {
        return cardType;
    }

    CardTypeEnum(String cardType) {
        this.cardType = cardType;
    }
}
