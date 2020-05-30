package com.hgw.homework.fourthmodel.task3;

import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 使用线程池将一个目录中的所有内容拷贝到另外一个目录中，包含子目录中的内容
 *
 * @author 追风同学
 * @date 2020/05/26 16:22
 * @description
 */
public class Task3 implements Runnable {

    /**
     * 用于接收文件目录
     */
    private String originalFile;
    /**
     * 目标路径
     */
    private String destinationPath;

    /**
     * 空参构造
     */
    public Task3() {
    }

    /**
     * 全参构造
     *
     * @param originalFile
     */
    public Task3(String originalFile, String destinationPath) {
        this.originalFile = originalFile;
        this.destinationPath = destinationPath;
    }

    /**
     * 文件路径的set方法
     *
     * @param originalFile
     */
    public void setOriginalFile(String originalFile) {
        this.originalFile = originalFile;
    }

    /**
     * 目标路径的set方法
     *
     * @param destinationPath
     */
    public void setDestinationPath(String destinationPath) {
        this.destinationPath = destinationPath;
    }

    @Override
    public void run() {
        copyDir(originalFile, destinationPath);
    }

    /**
     * 复制文件夹
     * @param srcDir
     * @param desDir
     */
    public void copyDir(String srcDir, String desDir) {
        File srcfile = new File(srcDir);
        File desfile = new File(desDir);
        //目标路径不存在,则创建出来
        if (!desfile.exists()) {
            desfile.mkdirs();
        }
        File[] files = srcfile.listFiles();
        for (File file : files) {
            if (file.isFile()){
                copyFile(file.getAbsolutePath(),desDir+"\\"+file.getName());
            }else{
                copyDir(file.getAbsolutePath(),desDir+"\\"+file.getName());
            }
        }
    }

    /**
     * 复制文件
     * @param srcDir
     * @param desDir
     */
    public void copyFile(String srcDir,String desDir) {
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(new FileInputStream(srcDir));
            bos = new BufferedOutputStream(new FileOutputStream(desDir));
            byte[] bArr = new byte[1024];
            int res = 0;
            while ((res = bis.read(bArr)) != -1) {
                bos.write(bArr, 0, res);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != bos){
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != bis){
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 运行测试
     * @param args
     */
    public static void main(String[] args) {
        //原始目录
        String srcDir =  "src/main/java/com/hgw/homework/fourthmodel/testDir";
        //目标目录
        String desPath = "src/main/java/com/hgw/homework/fourthmodel/testDirCopy";
//        Task3Thread task3Thread = new Task3Thread(oriFile,desPath);
        // 1.创建一个线程池
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        // 2.向线程池中布置任务
        File oriFile = new File(srcDir);
        File[] files = oriFile.listFiles();
        for (File file : files) {
            if (file.isDirectory()){
                //有几个文件夹就创建几个线程执行
                executorService.execute(new Task3(srcDir, desPath));
            }
        }
        // 3.关闭线程池
        executorService.shutdown();
    }
}
