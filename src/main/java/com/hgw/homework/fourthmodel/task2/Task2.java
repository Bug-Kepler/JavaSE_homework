package com.hgw.homework.fourthmodel.task2;

import javax.crypto.NullCipher;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Scanner;

/**
 * 实现将指定目录中的所有内容删除，包含子目录中的内容都要全部删除。
 *
 * @author 追风同学
 * @date 2020/05/26 8:09
 * @description
 */
public class Task2 {

    public static void main(String[] args) {

        File testFile = new File("src/main/java/com/hgw/homework/fourthmodel/testDir");
        System.out.println("请输入以下命令:1.生成测试目录以及文件\t2.删除目录以及所有文件");
        Scanner scanner = new Scanner(System.in);
        String commandStr=scanner.next();
        if(commandStr.matches("[\\d]{1}")) {
            if (1 == Integer.parseInt(commandStr)){
                makeTestDir();
            }else{
                show(testFile);
                deleteAppointDir(testFile);
            }
        }
    }

    /**
     * 生成用于测试的多级目录和文件
     */
    public static void makeTestDir(){
        String[] filePath = new String[]{
               "src/main/java/com/hgw/homework/fourthmodel/testDir",
               "src/main/java/com/hgw/homework/fourthmodel/testDir/dir1",
                "src/main/java/com/hgw/homework/fourthmodel/testDir/dir2",
                "src/main/java/com/hgw/homework/fourthmodel/testDir/dir3/dir31",
                "src/main/java/com/hgw/homework/fourthmodel/testDir/dir4/dir41/dir42",
                "src/main/java/com/hgw/homework/fourthmodel/testDir/dir1/1.doc",
                "src/main/java/com/hgw/homework/fourthmodel/testDir/dir1/2.docx",
                "src/main/java/com/hgw/homework/fourthmodel/testDir/dir1/3.ppt",
                "src/main/java/com/hgw/homework/fourthmodel/testDir/dir1/5.txt",
                "src/main/java/com/hgw/homework/fourthmodel/testDir/dir2/6.txt",
        };
        File targetTestDir = null;
        for (int i = 0; i < filePath.length; i++) {
            targetTestDir =new File(filePath[i]);
            if ( i < 3){//0,1,2
                targetTestDir.mkdir();
            }else if (i > 2 && i < 5 ){//3,4
                targetTestDir.mkdirs();
            }else{//5~end
                try {
                    targetTestDir.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("测试目录已经生成!");
    }

    /**
     * 删除指定目录下的所有内容
     * @param target
     */
    public static void deleteAppointDir(File target) {

        if (target.exists()){
            File[] filesArray = target.listFiles();
            for (File file : filesArray) {
                if (file.isFile()) {
                    System.out.println("删除" + ":" + file + (file.delete() ? "成功" : "失败"));
                }
                if (file.isDirectory()) {
                    deleteAppointDir(file);
                }
            }
            //将目录都删除
            System.out.println("删除" + ":" + target + (target.delete() ? "成功" : "失败"));
        }else{
            System.out.println("该文件路径不存在文件!");
        }
    }

    //指定目录以及子目录中所有内容的打印
    public static void show(File file) {
        System.out.println("该目录下的文件夹以及文件:");
        if (file.exists()){
            File[] filesArray = file.listFiles();
            for (File tf : filesArray) {
                String name = tf.getName();
                // 判断是否为文件，若是则直接打印文件名称
                if (tf.isFile()) {
                    System.out.println(name);
                }
                // 若是目录，则使用[]将目录名称括起来
                if (tf.isDirectory()) {
                    System.out.println("[" + name + "]");
                    show(tf);
                }
            }
        }else{
            System.out.println("该文件路径不存在文件!");
        }

    }
}
