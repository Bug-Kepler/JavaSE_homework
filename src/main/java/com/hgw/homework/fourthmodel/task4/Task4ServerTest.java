package com.hgw.homework.fourthmodel.task4;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author 追风同学
 * @date 2020/05/26 20:54
 * @description
 */
public class Task4ServerTest {

    public static void main(String[] args) {

        ServerSocket serverSocket =null;
        Socket socket = null;
        try {
            serverSocket = new ServerSocket(8888);
            while (true){
                System.out.println("等待客户端的连接请求...");
                socket = serverSocket.accept();
                System.out.println("客户端" + socket.getInetAddress() + "连接成功！");
                new Task4ServerThread(socket).start();
            }
        } catch (IOException e) {
//            e.printStackTrace();
        } finally {
            if (null != socket){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != serverSocket){
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
