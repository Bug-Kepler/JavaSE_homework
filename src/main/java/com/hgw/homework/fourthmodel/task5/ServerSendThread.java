package com.hgw.homework.fourthmodel.task5;

import java.io.*;
import java.net.Socket;
import java.util.List;

/**
 * @author 追风同学
 * @date 2020/05/30 9:56
 * @description
 */
public class ServerSendThread extends Thread {

    private String fileInfo;
    private List<Socket> socketList;
    private static final String saveDir = "D:\\uploadFile\\";

    public ServerSendThread(String fileInfo, List<Socket> socketList) {
        this.fileInfo = fileInfo;
        this.socketList = socketList;
    }

    @Override
    public void run() {

        System.out.println("转发服务已开启!");

        int length = 0;
        byte[] bytes = new byte[1024];
        String[] split = fileInfo.split(",");
        File file = new File(saveDir + split[0]);

        PrintStream printStream = null;
        BufferedInputStream bufferedInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;

        for (Socket socket : socketList) {

            try {
                printStream = new PrintStream(socket.getOutputStream());
                printStream.println("file");
                System.out.println("即将广播该文件!");
                printStream.println(fileInfo);
                System.out.println(fileInfo);
                //全网广播
                bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
                bufferedOutputStream = new BufferedOutputStream(socket.getOutputStream());
                while ((length = bufferedInputStream.read(bytes)) != -1) {
                    bufferedOutputStream.write(bytes, 0, length);
                    bufferedOutputStream.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("发送完一份!");
        }
        System.out.println("全部发送完毕!");
    }
}
