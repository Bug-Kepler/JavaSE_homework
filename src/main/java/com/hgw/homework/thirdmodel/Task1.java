package com.hgw.homework.thirdmodel;

import java.util.Scanner;

/**
 * 编程统计字符串"ABCD123!@#$%ab"中大写字母、小写字母、数字、其它字符的个数并打印出来。
 *
 * charAt() 一维数组 Character类中判断的方法
 *
 * @author 追风同学
 * @date 2020/05/21 8:47
 * @description
 */
public class Task1 {

    public static void main(String[] args) {

        //题目要求被统计字符串
        String str = new String("ABCD123!@#$%ab");
        //转换为字符数组
        char[] chars = str.toCharArray();
        //分类统计的数值
        int[] result = new int[4];
//        int upCharNum=0;
//        int lowerCharNum=0;
//        int numberNum=0;
//        int otherNum=0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入测试方法(编号1/2):");
        int chooseBranch = scanner.nextInt();
        if (1 == chooseBranch) {
            result = method1(chars);
        } else {
            result = method2(chars);
        }
        //输出结果
        printResult(result);
//        System.out.println("字符串中大写字母的个数为:"+upCharNum);
//        System.out.println("字符串中小写字母的个数为:"+lowerCharNum);
//        System.out.println("字符串中数字的个数为:"+numberNum);
//        System.out.println("字符串中其他字符的个数为:"+otherNum);
    }

    /**
     * 封装打印结果的方法
     */
    private static void printResult(int[] result) {
        System.out.println("字符串中大写字母的个数为:" + result[0]);
        System.out.println("字符串中小写字母的个数为:" + result[1]);
        System.out.println("字符串中数字的个数为:" + result[2]);
        System.out.println("字符串中其他字符的个数为:" + result[3]);
    }

    /**
     * 通过ASCII码值进行比较
     *
     * @param chars
     * @return
     */
    public static int[] method1(char[] chars) {
        //打印提示语句
        System.out.println("-----解决方案为ASCII码值判断-----");
//        System.out.println("输入的字符串为:" + chars.toString());打印结果为字符数组的地址值
        System.out.println(chars);
        int[] result = new int[4];
        //遍历字符数组
        for (int i = 0; i < chars.length; i++) {
            //A-Z
            if (chars[i] >= 65 && chars[i] <= 90) {
                result[0]++;
            }
            //a-z
            else if (chars[i] >= 97 && chars[i] <= 122) {
                result[1]++;
            }
            //1-9
            else if (chars[i] >= 48 && chars[i] <= 57) {
                result[2]++;
            }
            //其他符号
            else {
                result[3]++;
            }
        }
        //返回结果数组
        return result;
    }

    private static int[] method2(char[] chars) {

        //打印提示语句
        System.out.println("-----解决方案为正则表达式判断-----");
        System.out.println(chars);
        //定义结果数组
        int[] result = new int[4];
        //正则表达式的定义
        String upCase = "[A-Z]";
        String lowerCase = "[a-z]";
        String numberCase = "\\d";
        //转换接收字符串变量
        String temp;
        //遍历
        for (int i = 0; i < chars.length; i++) {
            temp = Character.toString(chars[i]);
            if (temp.matches(upCase)) {
                result[0]++;
            } else if (temp.matches(lowerCase)) {
                result[1]++;
            } else if (temp.matches(numberCase)) {
                result[2]++;
            } else {
                result[3]++;
            }
        }
        //返回结果数组
        return result;
    }
}
