package com.hgw.homework.fourthmodel.task5;

import java.io.*;
import java.net.Socket;

/**
 * @author 追风同学
 * @date 2020/05/28 9:08
 * @description
 */
public class ClientReceiveThread extends Thread {

    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedInputStream bufferedInputStream;
    private FileOutputStream fileOutputStream;
    private File file;
    private String saveDir;


    public ClientReceiveThread(Socket socket , String saveDir) {
        this.saveDir = saveDir;
        this.socket = socket;
        try {
            bufferedInputStream = new BufferedInputStream(socket.getInputStream());
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        file = new File(saveDir);
        if (!file.exists()) {
            file.mkdirs();
        }
        //打印上上线信息
        try {
            System.out.println(bufferedReader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("接收服务已开启!");
        String type = null;
        String message = null;
        while (true) {
            try {

                type = bufferedReader.readLine();
                if ("file".equals(type)) {
                    message = bufferedReader.readLine();
                    String[] split = message.split(",");
                    System.out.println(message);
                    file = new File(saveDir + split[0]);
                    System.out.println("开始下载文件了!");
                    byte[] bytes = new byte[1024];
                    int length = 0;
                    int num = 1;
                    fileOutputStream = new FileOutputStream(file);
                    while ((length = bufferedInputStream.read(bytes, 0, bytes.length)) != -1) {
                        fileOutputStream.write(bytes, 0, length);
                        fileOutputStream.flush();
                        if (Integer.parseInt(split[1]) == file.length()) {
                            break;
                        }
                    }
                    System.out.println("下载成功!");
                } else {
                    System.out.println(type);
                }
            } catch (IOException e) {
//                e.printStackTrace();
            }
        }
    }
}
