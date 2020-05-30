package com.hgw.homework.fourthmodel.task4;

import java.io.*;
import java.net.Socket;

/**
 * 使用基于 tcp 协议的编程模型实现将 UserMessage 类型对象由客户端发送给服务器；
 * <p>
 * 服务器接收到对象后判断用户对象信息是否为 "admin"和"123456"，
 * <p>
 * 若是则将UserMessage对象中的类型改为"success"否则将类型改为"fail"并回发给客户端，
 * <p>
 * 客户端接收到服务器发来的对象后判断并给出登录成功或者失败的提示。
 * <p>
 * 其中UserMessage类的特征有：类型(字符串类型)和用户对象(User类型)。
 * <p>
 * 其中 User 类的特征有：用户名、密码(字符串类型)。
 * <p>
 * 如：UserMessage tum = new UserMessage("check", new User("admin", "123456"));
 *
 * @author 追风同学
 * @date 2020/05/26 20:00
 * @description
 */
public class Task4ServerThread extends Thread {

    private Socket serverSocket;

    public Task4ServerThread(Socket s) {
        this.serverSocket = s;
    }

    @Override
    public void run() {
        serverStart();
    }

    /**
     * 启动服务器
     */
    public void serverStart() {

        ObjectInputStream objectInputStream = null;
        ObjectOutputStream objectOutputStream = null;

        try {
            objectInputStream = new ObjectInputStream(serverSocket.getInputStream());
            objectOutputStream = new ObjectOutputStream(serverSocket.getOutputStream());
            while (true) {
                UserMessage result = (UserMessage) objectInputStream.readObject();
                serverSocket.shutdownInput();
                if ("admin".equals(result.getUser().getUserName()) && "123456".equals(result.getUser().getPassword())) {
                    result.setType("success");
                } else {
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
