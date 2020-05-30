package com.hgw.homework.fourthmodel.task5;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author 追风同学
 * @date 2020/05/28 9:08
 * @description
 */
public class ClientSendThread extends Thread {

    private Socket socket;
    private BufferedInputStream bufferedInputStream;
    private BufferedOutputStream bufferedOutputStream;
    private PrintStream printStream;;
    private static Scanner scanner;

    static {
        scanner = new Scanner(System.in);
    }

    public ClientSendThread(Socket socket) {
        this.socket = socket;
        try {
            printStream = new PrintStream(socket.getOutputStream());
            bufferedInputStream = new BufferedInputStream(socket.getInputStream());
            bufferedOutputStream = new BufferedOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run(){

        System.out.println("进入聊天室需要输入昵称:");

        String username = scanner.next();

        if (username != null){

            printStream.println(username);

            System.out.println("发送服务已经开启!");

            String keyboardIn =null;
            File file = null;
            String filePath = null;

            while(true){
                System.out.println("请输入您的聊天内容:若发送文件请输入\"file\":");
                keyboardIn = scanner.next();
                printStream.println(keyboardIn);
                if ("file".equalsIgnoreCase(keyboardIn)){
                    System.out.println("请输入文件的路径:");
                    filePath = scanner.next();
                    file = new File(filePath);
                    printStream.println(file.getName()+","+file.length());
                    System.out.println("开始上传文件了!");
                    byte[] bytes = new byte[1024];
                    int length = 0;
                    int num = 0;
                    try {
                        bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
                        while ((length = bufferedInputStream.read(bytes)) != -1) {
                            bufferedOutputStream.write(bytes, 0, length);
                            bufferedOutputStream.flush();
                        }
                    } catch (IOException e) {
                    }
                    System.out.println("上传成功!");
                }else{
//                    printStream.println(keyboardIn);
//                    printStream.close();
                }
            }
        }
    }


}
