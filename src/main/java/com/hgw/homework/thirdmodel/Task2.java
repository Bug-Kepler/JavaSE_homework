package com.hgw.homework.thirdmodel;

/**
 * 编程获取两个指定字符串中的最大相同子串。
 * <p>
 * 如： s1="asdafghjka", s2="aaasdfg" 他们的最大子串为"asd"
 * <p>
 * 提示： 将短的那个串进行长度依次递减的子串与较长的串比较。
 *
 * @author 追风同学
 * @date 2020/05/21 9:40
 * @description
 */
public class Task2 {

    public static void main(String[] args) {

        String s1 = "asdafghjka";
//        String s2 = "aaasdfg";
        String s2 = "bzp";
        String sameStr = maxSameStr(s1, s2);
        System.out.println("最长子串为:" + sameStr);
    }

    /**
     * 判断最长重复字符串
     * 将较短的字符串按照长度减1一直拆分,然后再和较长的字符串进行比较
     * @param s1
     * @param s2
     * @return
     */
    public static String maxSameStr(String s1, String s2) {
        String max = "";
        String min = "";
        max = (s1.length() > s2.length()) ? s1 : s2;
        min = (max == s1) ? s2 : s1;

        for (int x = 0; x < min.length(); x++) {
            for (int y = 0, z = min.length() - x; z != min.length() + 1; y++, z++) {
                String temp = min.substring(y, z);
                System.out.println(temp);
                if (max.contains(temp)) {
                    return temp;
                }
            }
        }
        return "无匹配项";

    }


}
