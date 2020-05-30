package com.hgw.homework.fourthmodel.task5;

import java.io.*;
import java.net.Socket;
import java.util.List;

/**
 * @author 追风同学
 * @date 2020/05/28 9:09
 * @description
 */
public class ServerReceiveThread extends Thread {

    private Socket socket;
    private List<Socket> socketList;
    private BufferedReader bufferedReader;
    private PrintStream printStream;
    private BufferedInputStream bufferedInputStream;
    private BufferedOutputStream bufferedOutputStream;
    private FileOutputStream fileOutputStream;
    private static File file;
    private static final String saveDir = "D:\\uploadFile\\";
    private String currentUserName;

    static {
        file = new File(saveDir);
        if (!file.exists()) {
            file.mkdir();
        }
    }

    public ServerReceiveThread(Socket socket, List<Socket> socketList) {
        this.socket = socket;
        this.socketList = socketList;
        try {
            printStream = new PrintStream(socket.getOutputStream());
            bufferedInputStream = new BufferedInputStream(socket.getInputStream());
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {

        boolean first = true;

        System.out.println("接收服务已开启!");

        while (true) {
            String command = null;
            //阻塞点
            try {

                if (first) {
                    currentUserName = bufferedReader.readLine();

                    //全服发送上线信息
                    for (Socket socket1 : socketList) {
                        try {
                            printStream = new PrintStream(socket1.getOutputStream());
                            printStream.println(currentUserName + "已上线!");
                        } catch (IOException e) {
                        }
                    }
                    first = !first;
                }


                command = bufferedReader.readLine();
                if (null != command || 0 != command.length()) {
                    if ("file".equalsIgnoreCase(command)) {
                        //阻塞点
                        command = bufferedReader.readLine();
                        String[] split = command.split(",");
                        file = new File(saveDir + split[0]);
//                    bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
                        fileOutputStream = new FileOutputStream(file);

                        System.out.println("开始接收文件了!");
                        byte[] bytes = new byte[1024];
                        int length = 0;
                        while ((length = bufferedInputStream.read(bytes)) != -1) {
                            fileOutputStream.write(bytes, 0, length);
                            fileOutputStream.flush();
                            if (Integer.parseInt(split[1]) == file.length()) {
                                break;
                            }
                        }
                        System.out.println("上传成功!");

                        new ServerSendThread(command, socketList).start();

                        /*printStream.println("file");
                        System.out.println("即将广播该文件!");

                        printStream.println(command);
                        System.out.println(command);

                        //全网广播
                        length = 0;
                        int num = 1;
                        bufferedInputStream = new BufferedInputStream(new FileInputStream(file));

                        for (Socket socket1 : socketList) {
                            bufferedOutputStream = new BufferedOutputStream(socket1.getOutputStream());
                            while ((length = bufferedInputStream.read(bytes)) != -1) {
                                bufferedOutputStream.write(bytes, 0, length);
                                bufferedOutputStream.flush();
                            }
                            System.out.println("发送完一份!");
                        }
                        System.out.println("全部发送完毕!");*/
                    } else {
                        System.out.println("即将广播该消息!");
                        for (Socket socket1 : socketList) {
                            if (socket != socket1) {
                                try {
                                    printStream = new PrintStream(socket1.getOutputStream());
                                    printStream.println(currentUserName + "发来的消息:---" + command);
                                } catch (IOException e) {
                                }
                            }
                            System.out.println("已经成功广播该消息!");
                        }
                    }
                }
            } catch (IOException e) {
//                e.printStackTrace();
            }
        }
    }
}
