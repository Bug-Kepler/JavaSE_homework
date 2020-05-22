package com.hgw.homework.firstmodel;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * @author 郝国伟
 * @Phone 17331232635
 * @date 4/27/2020 10:07 PM
 * @description
 */
public class ExerciseNo1 {
    /*
    1.编程规范：优秀
    2.运行结果：优秀 4/5
    3.扣分项： 1.双色球会产生重复，调用Arrays.binarySearch()方法前先对数组进行排序，不然结果会有偏差
    * */

    @Test
    /**
     * 编程题
     * 提示用户输入年月日信息，判断这一天是这一年中的第几天并打印。
     * 输入信息的格式为:20200427
     * 实现方式:[数组]
     */
    public void Task1PlanA() {
        //向用户屏幕输出提示信息
        System.out.println("请输入年月日信息:");
        //创建扫描器
        Scanner scanner = new Scanner(System.in);
        //获取用户输入的年月日信息
        int yearInfo = scanner.nextInt();
//        int yearInfo=20200202;
        if (yearInfo / 10000000 == 0) {
            System.out.println("输入信息不完整!");
            return;
        }
        //分解以得到年月日的信息
        //得到年的信息
        int year = yearInfo / 10000;
        yearInfo %= 10000;
        if (year < 0 || year < 1970) {
            System.out.println("年份超出限制!");
            return;
        }
        //得到月的信息
        int month = yearInfo / 100;
        yearInfo %= 100;
        System.out.println("yearInfo:" + yearInfo);
        if (month <= 0 || month > 12) {
            System.out.println("月份超出限制!");
            return;
        }
        //得到天的信息
        int day = yearInfo;
        System.out.println("日:" + day);
        if (day <= 0 || day > 31) {
            System.out.println("日期超出限制!");
            return;
        }
        if (month == 2 && day > 29) {
            System.out.println("二月最多只有29天!");
            return;
        }
        //创建存储有各月份天数的数组
        int[] days = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        //总天数
        int sum = 0;
        //判断该年是闰年还是平年
        boolean flag = false;
        if (0 == year % 4 || 0 == year % 400) {
            flag = true;
            days[1] = 29;
        }
        //计算不包含当月的天数的和
        for (int i = 0; i < month - 1; i++) {
            sum += days[i];
        }
        //加上现在的天数
        sum += day;
        if (!flag && month == 2 && day == 29) {
            System.out.println("该年是平年,二月只有28天!");
            return;
        }
        System.out.println("这是这一年中的第" + sum + "天");
    }

    @Test
    /**
     * 编程题
     * 提示用户输入年月日信息，判断这一天是这一年中的第几天并打印。
     * 输入信息的格式为:20200427
     * 实现方式[switch]
     */
    public void Task1PlanB() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入年份信息:");
        int year = scanner.nextInt();
        if (year < 0||year/1000==0||year/1000>9) {
            System.out.println("输入的年份非法!");
            return;
        }
        System.out.println("请输入月份信息:");
        int month = scanner.nextInt();
        if (month <= 0 || month > 12) {
            System.out.println("输入的月份非法!");
            return;
        }
        System.out.println("请输入天数信息");
        int day = scanner.nextInt();
        if (day <= 0 || day > 31) {
            System.out.println("输入的天数非法!");
            return;
        }
        if (month == 2&&day>29) {
            System.out.println("二月天数最大不超过29天!");
            return;
        }
        boolean flag = false;
        if (0 == year % 4 || 0 == year % 400) {
            flag = true;
        }
        int days = 0;
        switch (month - 1) {
            case 11:
                days += 30;
            case 10:
                days += 31;
            case 9:
                days += 30;
            case 8:
                days += 31;
            case 7:
                days += 31;
            case 6:
                days += 30;
            case 5:
                days += 31;
            case 4:
                days += 30;
            case 3:
                days += 31;
            case 2:
                if (flag) {
                    days += 29;
                } else {
                    days += 28;
                };
            case 1:
                days += 31;
        }
        days += day;
        System.out.println("这是这一年中的第" + days + "天");
    }

    @Test
    /**
     *编程题
     * 编程找出1000以内的所有完数并打印出来。
     * 所谓完数就是一个数恰好等于它的因子之和，
     * 如：6=1+2+3
     */
    public void Task2(){
        System.out.print("1000以内的完数有:");
        for (int i = 2; i < 1000; i++) {
            int sum=0;
            for (int j = 1; j < i; j++) {
                if(i%j==0){
                    sum+=j;
                }
            }
            if(i==sum){
                System.out.print(i+" ");
            }
        }
    }
    @Test
    /**
     * 编程题
     * 实现双色球抽奖游戏中奖号码的生成，中奖号码由6个红球号码和1个蓝球号码组成。
     * 其中红球号码要求随机生成6个1~33之间不重复的随机号码。
     * 其中蓝球号码要求随机生成1个1~16之间的随机号码。
     */
    public void Task3(){
        //创建双色球存储数组
        int [] balls=new int[7];
//        int [] redBalls=new int[6];
//        int blueBall;
        Random random=new Random();
        //随机生成红球并存入结果数组中
        for (int i = 0; i < balls.length-1; i++) {
            int temp=random.nextInt(33)+1;
            // 3.扣分项： 1.双色球会产生重复，调用Arrays.binarySearch()方法前先对数组进行排序，不然结果会有偏差
            // TODO: 4/28/2020  数组的排序问题
//            Arrays.sort(balls);
            int index = Arrays.binarySearch(balls, temp);
            if(index<0){
                balls[i]=temp;
            }
        }
        //随机生成蓝球并存入结果数组中
        balls[balls.length-1]=random.nextInt(16)+1;
        System.out.print("双色球的中奖号码为:");
        for (int i = 0; i < balls.length; i++) {
            if (i==balls.length-1){
                System.out.print(" 蓝色球:"+balls[i]+"。\n");
            }else {
                if(i==0){
                    System.out.print("红色球:"+balls[i]+",");
                }else{
                    System.out.print(balls[i]+",");
                }
            }
        }
//        for (int i = 0; i < redBalls.length; i++) {
//            int temp=random.nextInt(33)+1;
//            int index = Arrays.binarySearch(redBalls, temp);
//            System.out.println(index);
//            if(index<0){
//                redBalls[i]=temp;
//            }
//            for (int j = 0; j < redBalls.length; j++) {
//                if(temp==redBalls[j]){
//                    continue;
//                }else{
//                    redBalls[j]=temp;
//                }
//            }
//        }
//        System.out.println(Arrays.toString(redBalls));
//        blueBall=random.nextInt(16)+1;
//        System.out.println("双色球中奖号码为:[红球]"+Arrays.toString(redBalls)+"[蓝球]"+blueBall);
    }

    @Test
    /**
     *编程题
     * 自定义数组扩容规则，当已存储元素数量达到总容量的80%时，扩容1.5倍。
     * 例如，总容量是10，
     * 当输入第8个元素时，数组进行扩容，
     * 容量从10变15。
     */
    public void Task4(){
        System.out.println("请输入要创建数组的大小:");
        Scanner scanner=new Scanner(System.in);
        int size=scanner.nextInt();
        //初始容量的数组
        int[] arr=new int[size];
        //模拟向数组中插入元素
        int expendCap= (int) (arr.length*0.8);
        for (int i = 0; i < arr.length; i++) {
            //当插入到容量的80%,调用数组扩容的方法
            if(i>=expendCap){
                break;
            }else{
                //向数组中插入值
                arr[i]=i;
            }
        }
        //调用数组扩容的方法
        arr=arrayExpend(arr,expendCap,arr.length);
        //打印验证
        System.out.println("扩容之后的数组大小为:"+arr.length);
        System.out.println("扩容之后的数组内容为:"+Arrays.toString(arr));
    }

    /**
     *
     * @param arr 需要扩容的数组
     * @param realCapacity 数组的真实容量
     * @param capacity 实际存储的容量
     * @return
     */
    public static int[] arrayExpend(int[] arr,int realCapacity,int capacity){
        //扩充之后的容量
        int ExpendCapacity= (int) (capacity*1.5);
        //扩充之后的数组
        int brr[]=new int[ExpendCapacity];
        //将之前的数组拷贝到新数组中
        System.arraycopy(arr,0,brr,0,realCapacity);
        return brr;
    }

    @Test
    /**
     * 编程题
     * 使用二维数组和循环实现五子棋游戏棋盘的绘制
     *
     */
    public void Task5(){
        //定义行/列标
        char [] cross={'\t','0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
        // TODO: 4/30/2020 可以尝试打印16进制 
        //行/列数
        int size=cross.length;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                //特殊位置的处理
                //(0,0)位置
                if (0==i&&0==j){
                    System.out.print("\t");
                }
                //第0行
                else if (0==i){
                    System.out.print(cross[j]+"\t");
                }
                //第0列
                else if (0==j){
                    System.out.print(cross[i]+"\t");
                }
                //其余位置
                else {
                    System.out.print("+\t");
                }
            }
            //换行
            System.out.println();
        }

        /*for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == 0 && j == 0) {
                    System.out.print(cross[j]);
                }*/
//                if (0 == i && 0 != j) {
//                    System.out.print(cross[j] + "\t");
//                }
//                if (0!=i){
//                    System.out.print(cross[i]);
//                }
//                if (i!=0&&j!=0){
//                    System.out.print("+"+"\t");
//                }
//            }
//            if(0!=i){
//                System.out.print(cross[i]);
//            }
                /*if (0==i||0==j){
                    System.out.print("+"+"\t");
                }else if(i==j&&i==0&&j==0){
                    System.out.print("\t");
                }else {
                    System.out.print(cross[i]+"\t");
                }
            }
            System.out.println();*/
//        }
    }

    @Test
    public void Task(){
        Random r=new Random();
        for (int i = 0; i < 50; i++) {
            System.out.print(r.nextInt(33)+1+"\t");
        }
    }
    /*
     * 时间：2020-04-28
     * 作者：赵雨航
     * 描述：实现双色球抽奖游戏中奖号码的生成，中奖号码由 6 个红球号码和 1 个蓝球号码组成。
     *       其中红球号码要求随机生成 6 个 1~33 之间不重复的随机号码。
     *       其中蓝球号码要求随机生成 1 个 1~16 之间的随机号码。
     */


    @Test
    public void test() {

        System.out.println("本期双色球中奖号码如下：");

        Random rd = new Random(); // 随机数工具类
        int[] reds = new int[6]; // 存放红球
        int red,i = 0; // 临时存放红球、红球数组索引

        // 甩出红球
        System.out.print("红球号码：");
        boolean isRepeat = false; // 标记是否重复
        while(i < 6){

            // 生成一个红球
            red = rd.nextInt(33) + 1;

            // 判断是否重复
            for(int j = 0; j < i; j++){
                if(reds[j] == red){
                    isRepeat = true; // 重复标记
                    break;
                }
            }
            // 根据查询结果处理
            if(isRepeat){
                continue; // 跳过当前循环
            }else{
                // 输出红球
                System.out.print(red+" ");
                // 记录
                reds[i] = red;
                i++;
            }
//            isRepeat = false; // 标记复位
        }

        // 甩出蓝球
        System.out.println("蓝球号码："+(rd.nextInt(16)+1));
    }
}
