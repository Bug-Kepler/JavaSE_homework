package com.hgw.homework.fourthmodel.task4;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author 追风同学
 * @date 2020/05/26 21:07
 * @description
 */
public class Task4Server {

    public static void main(String[] args) {

        ServerSocket serverSocket =null;
        Socket socket = null;
        ObjectInputStream objectInputStream = null;
        ObjectOutputStream objectOutputStream = null;

        try {

            serverSocket = new ServerSocket(8888);

            while (true) {
                System.out.println("等待客户端的连接请求...");
                socket = serverSocket.accept();
                System.out.println("客户端" + socket.getInetAddress() + "连接成功！");
                InputStream inputStream = socket.getInputStream();
                objectInputStream = new ObjectInputStream(socket.getInputStream());
                objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

                UserMessage result = (UserMessage) objectInputStream.readObject();
//                boolean equals1 = "admin".equals(result.getUser().getUserName());
//                boolean equals2 = "123456".equals(result.getUser().getPassword());
                if ("admin".equals(result.getUser().getUserName()) && "123456".equals(result.getUser().getPassword())) {
//                if (equals1 && equals2) {

                    result.setType("success");
                }
                 else {
                    result.setType("fail");
                }

                objectOutputStream.writeObject(result);
            }
        } catch (IOException e) {
//            e.printStackTrace();
        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
        } finally {
            if (null != objectOutputStream) {
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != objectInputStream) {
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != serverSocket) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
