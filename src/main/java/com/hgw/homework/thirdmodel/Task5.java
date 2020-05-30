package com.hgw.homework.thirdmodel;

import java.util.*;

/**
 * 使用集合实现斗地主游戏的部分功能，要求如下：
 * <p>
 * （1）首先准备 54 张扑克牌并打乱顺序。
 * <p>
 * （2）由三个玩家交替摸牌，每人 17 张扑克牌，最后三张留作底牌。
 * <p>
 * （3）查看三个玩家手中的扑克牌和底牌。
 * <p>
 * （4）其中玩家手中的扑克牌需要按照大小顺序打印，规则如下：
 * <p>
 * 手中扑克牌从大到小的摆放顺序：大王,小王,2,A,K,Q,J,10,9,8,7,6,5,4,3
 *
 * @author 追风同学
 * @date 2020/05/21 9:43
 * @description
 */
public class Task5 {

    public static void main(String[] args) {

        System.out.println("----------------");
        System.out.println("|----斗地主------|");

        //存储牌的索引和组装好的牌
        HashMap<Integer, String> poker = new HashMap<>();
        //存储牌的索引
        List<Integer> pokerIndex = new ArrayList<>();
        //定义两个集合,分别存储花色和牌的序号
        List<String> colors = List.of("♠", "♥", "♣", "♦");
        List<String> numbers = List.of("2", "A", "K", "Q", "J", "10", "9", "8", "7", "6", "5", "4", "3");

        //将大王和小王存储到集合中
        //定义一个牌的索引
        int index = 0;
        poker.put(index, "大王");
        pokerIndex.add(index);
        index++;
        poker.put(index, "小王");
        pokerIndex.add(index);
        index++;

        //组装牌
        //A-K的牌加入牌组中
        //循环嵌套遍历两个集合,组装52张牌,存储到集合中
        for (String number : numbers) {
            for (String color : colors) {
                poker.put(index, color + number);
                pokerIndex.add(index);
                index++;
            }
        }
        System.out.println("牌");
        Set<Integer> pokerKey = poker.keySet();
        for (Integer integer : pokerKey) {
            System.out.println(poker.get(integer));
        }
        //洗牌
        Collections.shuffle(pokerIndex);

        for (int i = 0; i < pokerIndex.size(); i++) {
            System.out.println(pokerIndex.get(i));
        }
        //发牌
        //定义四个集合,存储玩家的牌的索引和底牌的索引
        ArrayList<Integer> player01 = new ArrayList<>();
        ArrayList<Integer> player02 = new ArrayList<>();
        ArrayList<Integer> player03 = new ArrayList<>();
        ArrayList<Integer> dipai = new ArrayList<>();

        /**
         * 添加牌的临时变量
         */
        Integer temp =Integer.valueOf(0);

        for (int i = 0; i < pokerIndex.size(); i++) {
            temp = pokerIndex.get(i);
            if (i >= 51) {
                dipai.add(temp);
            } else if (i % 3 == 0) {
                player01.add(temp);
            } else if (i % 3 == 1) {
                player02.add(temp);
            } else if (i % 3 == 2){
                player03.add(temp);
            }
        }
        System.out.println("----排序前----");
        //排序前的牌组
        System.out.println(Arrays.toString(player01.toArray()));
        System.out.println(Arrays.toString(player02.toArray()));
        System.out.println(Arrays.toString(player03.toArray()));

        //给牌按照从大到小的顺序排序
        Collections.sort(player01);
        Collections.sort(player02);
        Collections.sort(player03);
        Collections.sort(dipai);

        System.out.println("---排序后---");
        System.out.println(Arrays.toString(player01.toArray()));
        System.out.println(Arrays.toString(player02.toArray()));
        System.out.println(Arrays.toString(player03.toArray()));

        //看牌
        lookPoker("玩家1",poker,player01);
        lookPoker("玩家2",poker,player02);
        lookPoker("玩家3",poker,player03);
        lookPoker("底牌",poker,dipai);
    }

    /**
     * 封装看牌的方法
     * @param name
     * @param poker
     * @param list
     */
    public static void lookPoker(String name,HashMap<Integer,String> poker,ArrayList<Integer> list){
        //输出玩家名称,不换行
        System.out.print(name+":");
        //遍历玩家或者底牌集合,获取牌的索引
        for (Integer key : list) {
            //使用牌的索引,去Map集合中,找到对应的牌
            String value = poker.get(key);
            System.out.print(value+" ");
        }
        System.out.println();//打印完每一个玩家的牌,换行
    }

}
