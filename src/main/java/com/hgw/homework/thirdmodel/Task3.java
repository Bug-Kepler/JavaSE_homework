package com.hgw.homework.thirdmodel;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 准备一个 HashMap 集合，统计字符串"123,456,789,123,456"中每个数字字符串出现的次数并打印出来。
 *
 *  如：
 *
 *         123 出现了 2 次
 *
 *         456 出现了 2 次
 *
 *         789 出现了 1 次
 *
 * @author 追风同学
 * @date 2020/05/21 9:43
 * @description
 */
public class Task3 {

    public static void main(String[] args) {

        String str = "123,456,789,123,456";

        //准备一个HashMap集合
        Map<String,Integer> result=new HashMap<>();

        String[] splitArray = str.split(",");

        int num1=0;
        int num4=0;
        int num7=0;

        for (int i = 0; i < splitArray.length; i++) {
            int parseInt = Integer.parseInt(splitArray[i]);
            if (123==parseInt){
                num1++;
            }else if (456==parseInt){
                num4++;
            }else if(789==parseInt){
                num7++;
            }
        }
        result.put("123",num1);
        result.put("456",num4);
        result.put("789",num7);

        Set<String> keyMap = result.keySet();
        System.out.println("123 出现次数为: "+result.get("123") +" 次");
        System.out.println("456 出现次数为: "+result.get("456") +" 次");
        System.out.println("789 出现次数为: "+result.get("789") +" 次");
    }




}
