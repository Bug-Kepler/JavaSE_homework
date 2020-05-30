package com.hgw.homework.fourthmodel.task5;

import java.io.IOException;
import java.net.Socket;

/**
 * @author 追风同学
 * @date 2020/05/28 9:07
 * @description
 */
public class Client3 {

    public static void main(String[] args) {

        Socket socket = null;
        String saveDir = "D:\\downloadFile\\Client3\\";
        try {
            //1.创建Socket类型的对象并提供服务器的主机名和端口号
            socket = new Socket("127.0.0.1", 8899);
            System.out.println("连接服务器成功");
            //创建读写的线程
            new ClientSendThread(socket).start();
            new ClientReceiveThread(socket, saveDir).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
