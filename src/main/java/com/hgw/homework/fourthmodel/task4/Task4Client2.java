package com.hgw.homework.fourthmodel.task4;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author 追风同学
 * @date 2020/05/26 20:09
 * @description
 */
public class Task4Client2 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("请输入账号和密码:");
        String username = sc.next();
        String password = sc.next();
        UserMessage userMessage = new UserMessage("check",new User(username,password));

        Socket socket = null;
        ObjectInputStream objectInputStream = null;
        ObjectOutputStream objectOutputStream = null;

        try {
            socket = new Socket("127.0.0.1",8888);
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            while(true){
                objectOutputStream.writeObject(userMessage);
                UserMessage result = (UserMessage) objectInputStream.readObject();
                if ("success".equals(result.getType())){
                    System.out.println("登录成功!");
                    break;
                }else{
                    System.out.println("登录失败!");
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (null !=objectOutputStream){
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != objectInputStream){
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != socket){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}
