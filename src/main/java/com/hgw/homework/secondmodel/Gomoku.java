package com.hgw.homework.secondmodel;


import java.util.Scanner;

/**五子棋
 * @author 追风同学
 * @date 2020/05/16 9:45
 * @description
 */
public class Gomoku {

    /**
     * 1. 编程实现控制台版并支持两人对战的五子棋游戏。
     * <p>
     * （1）绘制棋盘 - 写一个成员方法实现
     * <p>
     * （2）提示黑方和白方分别下棋并重新绘制棋盘 - 写一个成员方法实现。
     * <p>
     * （3）每当一方下棋后判断是否获胜 - 写一个成员方法实现。
     * <p>
     * （4）提示： 采用二维数组来模拟并描述棋盘，棋盘如下：
     */
    /**
     * 棋盘大小
     */
    private static final int SIZE = 17;
    /**
     * 存放棋盘的二维数组
     */
    private static char[][] chessBoard = new char[SIZE][SIZE];
    /**
     * 棋盘边际的字符常亮数组
     */
    private final char[] cross = {' ', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    /**
     * 黑白棋子的字符数组
     */
    private final char[] chess = {'●', '○'};

    /**
     * 五子棋游戏主入口
     * 运行主方法
     *
     * @param args
     */
    public static void main(String[] args) {

        //创建扫描器
        Scanner scanner = new Scanner(System.in);
        //开始五子棋游戏的提示信息
        System.out.println("开始五子棋?(y/n):");
        //判读是否要开始游戏
        if ("y".equals(scanner.next())) {
            //创建游戏对象
            Gomoku newGame = new Gomoku();
            //黑棋先手的标志
            boolean blackFirst = true;
            //棋盘初始化
            newGame.chessboardInit();
            //游戏回合数
            int num = 1;
            //游戏输赢的标志
            boolean winFlag = true;
            //当前落子数量
            int chessNum = 0;
            //下棋的过程
            while (winFlag) {
                //......下棋ing......
                //双方下棋
                for (int i = 0; i < 2; i++) {
                    System.out.println("请下棋:(位置[水平方向][1-16] 位置[垂直方向][1-16]):");

                    int x = scanner.nextInt();
                    int y = scanner.nextInt();

                    //其中一方下棋
                    boolean isSuccess = newGame.play(x, y, blackFirst);
                    //加入该棋子位置合法且不重复,则记为有效输入
                    if (isSuccess) {
                        chessNum++;
                        //棋手身份互换
                        blackFirst = !blackFirst;
                    }
                    //判断输赢
                    //最快结束游戏的情况是第一个人刚好5个棋子,第二个人刚好4个棋子,
                    //这样可以忽略前面的判断
                    //可以稍微提升性能
                    if (chessNum >= 9) {
                        //通过四个方向判断是否满足同种颜色的棋子有五颗相连
                        boolean win = newGame.win(x, y, !blackFirst);
                        if (win) {
                            //判断赢的游戏的角色
                            String winner = null;
                            if (blackFirst) {
                                winner = "白";
                            } else {
                                winner = "黑";
                            }
                            System.out.println(winner + "方获胜,游戏结束!");
                            //游戏结束的标志
                            winFlag = false;
                            break;
                        }
                    }
                }
                System.out.println("第" + num + "回合!");
                num++;
            }
        } else {
            //除y以外的其他字符都退出游戏
            System.out.println("退出游戏...");
            return;
        }
    }

    /**
     * 初始化棋盘
     */
    private void chessboardInit() {

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                //特殊位置的处理
                //(0,0)位置
                if (0 == i && 0 == j) {
                    chessBoard[0][0] = cross[0];
                }
                //第0行
                else if (0 == i) {
                    chessBoard[0][j] = cross[j];
                }
                //第0列
                else if (0 == j) {
                    chessBoard[i][0] = cross[i];
                }
                //其余位置
                else {
                    chessBoard[i][j] = '+';
                }
            }
        }
        //打印初始棋盘
        show();
    }

    /**
     * 打印棋盘的方法
     */
    private void show() {

        for (int i = 0; i < chessBoard.length; i++) {
            for (int j = 0; j < chessBoard[i].length; j++) {
                System.out.print(chessBoard[i][j] + "\t");
            }
            System.out.println();//换行
        }
    }

    /**
     * 下棋的方法
     */
    public boolean playChess(int x, int y, boolean blackFirst) {

        if (!isActiveArea(x) || !isActiveArea(y)) {
            System.out.println("输入的位置非法!");
            return false;
        } else {
            if ('+' == chessBoard[x][y]) {
                if (blackFirst) {
                    chessBoard[x][y] = chess[0];
                } else {
                    chessBoard[x][y] = chess[1];
                }
                return true;
            } else {
                System.out.println("该位置已存在棋子!");
                return false;
            }
        }
    }

    /**
     * 判断输入是否落在棋盘的有效区域
     * @param num     1<x<16,1<y<16
     * @return
     */
    private boolean isActiveArea(int num) {

        if (num < 0 || num > 16) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 获取棋子输入位置的操作的封装
     */
    @Deprecated
    private int[] getXAndY() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请下棋(位置 位置):");
        int[] spots = new int[2];
        spots[0] = scanner.nextInt();
        spots[1] = scanner.nextInt();
        return spots;
    }

    /**
     * 封装下棋的操作
     * @param x 水平方向的坐标位置
     * @param y 垂直方向的坐标位置
     * @param blackFirst 当前的游戏角色
     * @return
     */
    private boolean play(int x, int y, boolean blackFirst) {

        Gomoku newGame = new Gomoku();
        boolean isSuccess = newGame.playChess(x, y, blackFirst);
        if (!isSuccess) {
            return false;
        } else {
            newGame.show();
            return true;
        }
    }


    /**
     * 判断胜负的方法
     *
     * @return 是否赢得棋局的标志
     */
    private boolean win(int x, int y, boolean blackFirst) {

        char winStandFlag;
        if (blackFirst) {
            winStandFlag = chess[0];
        } else {
            winStandFlag = chess[1];
        }
        //水平方向检查是否够5个连续棋子
        //向左遍历
        int countXL = 1;
        for (int i = y - 1; i > 0; i--) {
//            System.out.println("当前棋子为:" + chessBoard[x][i]);
            System.out.println(chessBoard[x][i] == chessBoard[x][y]);
            if (chessBoard[x][i] == chessBoard[x][y]) {
                countXL++;
                if (countXL >= 5) {
//                    System.out.println("是这里哦!");
                    return true;
                }
            } else {
                break;
            }
        }
        //向右遍历
        int countXR = 1;
        for (int i = y + 1; i < 16; i++) {
//            System.out.println("当前棋子为:" + chessBoard[x][i]);
            System.out.println(chessBoard[x][i] == chessBoard[x][y]);
            if (chessBoard[x][i] == chessBoard[x][y]) {
                countXL++;
                if (countXL >= 5) {
                    return true;
                }
            } else {
                break;
            }
        }
        //中间落子,最后满足5颗棋子
        if (countXL + countXR > 5) {
            return true;
        }
        //垂直方向检查是否够5个连续棋子
        //向上遍历
        int countYU = 1;
        for (int i = x - 1; i > 0; i--) {
//            System.out.println("当前棋子为:" + chessBoard[x][i]);
            System.out.println(chessBoard[i][y] == chessBoard[x][y]);
            if (chessBoard[i][y] == chessBoard[x][y]) {
                countYU++;
                if (countYU >= 5) {
                    return true;
                }
            } else {
                break;
            }
        }
        //向下遍历
        int countYD = 1;
        for (int i = x + 1; i < 16; i++) {
//            System.out.println("当前棋子为:" + chessBoard[x][i]);
            System.out.println(chessBoard[i][y] == chessBoard[x][y]);
            if (chessBoard[i][y] == chessBoard[x][y]) {
                countYD++;
                if (countYD >= 5) {
                    return true;
                }
            } else {
                break;
            }
        }
        //中间落子,最后满足5颗棋子
        if (countYU + countYD > 5) {
            return true;
        }
        //斜线方向检查是否够5个连续棋子
        //斜向上遍历
        int countIU = 1;
        for (int i = x - 1, j = y - 1; i > 0 && j > 0; i--, j--) {
//            System.out.println("当前棋子为：" + chessBoard[i][j]);
            if (chessBoard[x][y] == chessBoard[i][j]) {
                countIU++;
                if (countIU >= 5) {
                    return true;
                }
            } else {
                break;
            }
        }
        //斜向下遍历
        int countID = 1;
        for (int i = x + 1, j = y + 1; i < 16 && j < 16; i++, j++) {
//            System.out.println("当前棋子为：" + chessBoard[i][j]);
            if (chessBoard[x][y] == chessBoard[i][j]) {
                countID++;
                if (countID >= 5) {
                    return true;
                }
            } else {
                break;
            }
        }
        //中间落子,最后满足5颗棋子
        if (countIU + countID > 5) {
            return true;
        }
        return false;
    }
}
