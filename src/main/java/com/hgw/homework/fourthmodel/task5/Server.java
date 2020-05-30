package com.hgw.homework.fourthmodel.task5;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 追风同学
 * @date 2020/05/28 9:07
 * @description
 */
public class Server {

    private static List<Socket> socketList = new ArrayList<>();

    public static void main(String[] args) {

        Socket socket = null;
        ServerSocket serverSocket = null;

        try {
            //1.创建ServerSocket类型的对象并提供端口号超过1025就可以
            serverSocket = new ServerSocket(8899);
            while (true) {
                //2.等待客户端的连接请求,调用accept方法
                System.out.println("等待客户端的连接请求...");
                socket = serverSocket.accept();
                if (null != socket){
                    System.out.println(socket.getInetAddress() + "连接成功!");
                    socketList.add(socket);
                    new ServerReceiveThread(socket, socketList).start();
                }
            }
        } catch (IOException e) {
//            e.printStackTrace();
        }
    }
}
