package com.hgw.homework.fourthmodel.task1;

import com.hgw.homework.fourthmodel.task1.exception.AgeErrorException;
import com.hgw.homework.fourthmodel.task1.exception.StudentIdErrorException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author 追风同学
 * @date 2020/05/21 17:26
 * @description
 */
public class TestMain {

    /**
     * 存放学生信息的列表
     */
    private static List<Student> stuList = new ArrayList<>();

    /**
     * 学生信息管理系统的主入口
     *
     * @param args
     */
    public static void main(String[] args) {
        //系统启动时调用初始化方法
        readFromFile();
        //判断输入的标志
        boolean flag = true;
        //扫描器
        Scanner scanner = new Scanner(System.in);
        //输入ing...
        while (flag) {
            //打印信息
            System.out.println("----------------");
            System.out.println("|学生信息管理系统|");
            System.out.println("----------------");
            System.out.println("输入以下命令的编号:\n1.新增学生信息\n2.删除学生信息\n3.修改学生信息\n4.查找学生信息\n5.打印所有学生信息\n6.退出系统");
            //获取命令号
            int command = 0;
            String temp = scanner.next();
            if (temp.matches("[\\d]{1}")) {
                command = Integer.parseInt(temp);
            }
//            int command = scanner.nextInt();
            //命令成功的标志
            boolean whetherSuccess = false;
//            int id=0;
//            String name=null;
//            int age=0;
            switch (command) {
                case 1:
                    System.out.println("请输入要添加学生的信息:学号 姓名 年龄:");
                    String[] in1 = getIn(3);
                    try {
                        whetherSuccess = addStudent(new Student(in1[0], in1[1], Integer.parseInt(in1[2])));
                    } catch (AgeErrorException e) {
                        e.printStackTrace();
                    } catch (StudentIdErrorException e) {
                        e.printStackTrace();
                    }
                    if (whetherSuccess) {
                        System.out.println("添加学生信息成功!");
                    } else {
                        System.out.println("添加学生信息失败!");
                    }
                    break;
                case 2:
                    System.out.println("请输入要删除学生的信息:学号 :");
                    String[] in2 = getIn(1);
//                    id = scanner.nextInt();
//                    name = scanner.next();
//                    age = scanner.nextInt();
//                    whetherSuccess = deleteStudent(new Student(Integer.parseInt(in2[0]), in2[1], Integer.parseInt(in2[2])));
                    try {
                        whetherSuccess = deleteStudent(new Student(in2[0], null, 0));
                    } catch (AgeErrorException e) {
                        e.printStackTrace();
                    } catch (StudentIdErrorException e) {
                        e.printStackTrace();
                    }
                    if (whetherSuccess) {
                        System.out.println("删除学生信息成功!");
                    } else {
                        System.out.println("删除学生信息失败!");
                    }
                    break;
                case 3:
                    System.out.println("请输入要修改学生的信息:学号(不能修改) 姓名 年龄:");
                    String[] in3 = getIn(3);
                    try {
                        whetherSuccess = modifyStudent(new Student(in3[0], in3[1], Integer.parseInt(in3[2])));
                    } catch (AgeErrorException e) {
                        e.printStackTrace();
                    } catch (StudentIdErrorException e) {
                        e.printStackTrace();
                    }
                    if (whetherSuccess) {
                        System.out.println("修改学生信息成功!");
                    } else {
                        System.out.println("修改学生信息失败!");
                    }
                    break;
                case 4:
                    System.out.println("请输入要查找学生的信息:学号 :");
                    String[] in4 = getIn(2);
                    List<Student> findResult = null;
                    try {
                        findResult = findStudent(new Student(in4[0], in4[1], 0));
                    } catch (AgeErrorException e) {
                        e.printStackTrace();
                    } catch (StudentIdErrorException e) {
                        e.printStackTrace();
                    }
                    if (findResult.size() != 0) {
                        for (int i = 0; i < findResult.size(); i++) {
                            System.out.println("该生信息为: " + findResult.get(i).toString());
                        }
                    } else {
                        System.out.println("没有该生的信息!");
                    }
                    break;
                case 5:
                    printStudent();
                    break;
                case 6:
                    flag = false;
                    System.out.println("已退出学生管理系统.....");
                    save2File(stuList);
                    break;
                default:
                    System.out.println("该命令有错误,请重新输入!");
                    break;
            }
        }
    }

    /**
     * 系统初始化读取文件中的学生信息存入到系统中
     */
    public static void readFromFile() {

        ObjectInputStream ois = null;
        try {
            File configFile = new File("./stuList.txt");
            if (configFile.exists()) {
                //创建ObjectInputStream类型的对象与sytList.txt文件关联
                ois = new ObjectInputStream(new FileInputStream("./stuList.txt"));
                //从输入流中读取对象并存入到集合中
                List<Student> readStudentList = (List<Student>) ois.readObject();
//            System.out.println(readStudentList);
//            System.out.println(readStudentList.size());
                for (Student student : readStudentList) {
                    //添加到成员变量列表中
                    stuList.add(student);
                }
                System.out.println("系统初始化读取信息成功");
                System.out.println("当前系统中有" + stuList.size() + "条学生数据.");
            } else {
                System.out.println("当前系统中并无学生信息");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            //关闭流对象并释放有关的资源
            if (null != ois) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 将系统中存入的学生信息保存到本地文件中
     *
     * @param stuList 学生信息对象列表
     */
    public static void save2File(List<Student> stuList) {
        //创建ObjectOutputStream类型的对象与文件相关联
        ObjectOutputStream oos = null;
//        OutputStreamWriter oos =null;
        try {
            //默认文件一直重置当前系统中存入的学生信息,即覆盖文件,
            // 后期考虑加入学生信息加入时间,以及本次操作后,该条数据是否还存在当前的系统中
            //待解决的问题,中文乱码输出到文件中
            oos = new ObjectOutputStream(new FileOutputStream("./stuList.txt", false));
//            oos = new OutputStreamWriter(new FileOutputStream("d:/stuList.txt",true),"utf-8");
            //将学生信息列表的信息存写入到输出流
            oos.writeObject(stuList);
            System.out.println("信息已经保存到stuInfo.txt中");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭流对象并释放有关资源
            if (null != oos) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 获取用户输入的信息
     *
     * @return
     */
    public static String[] getIn(int i) {
        Scanner scanner = new Scanner(System.in);
        String[] inArray = new String[3];
        for (int j = 0; j < i; j++) {
            inArray[j] = scanner.next();
        }
//        inArray[0] = scanner.next();
//        inArray[1] = scanner.next();
//        inArray[2] = scanner.next();
        return inArray;
    }

    /**
     * 添加学生信息
     *
     * @param newStudent
     * @return
     */
    public static boolean addStudent(Student newStudent) {
        //如果列表中存在将要添加的学生信息
        if (stuList.contains(newStudent)) {
            return false;
        } else {
            return stuList.add(newStudent);
        }
    }

    /**
     * 删除学生信息
     *
     * @param deldteStudent
     * @return
     */
    public static boolean deleteStudent(Student deldteStudent) {
        if (0 == stuList.size()) {
            return false;
        }
        if (stuList.contains(deldteStudent)) {
            int deleteIndex = stuList.indexOf(deldteStudent);
            Student removeStudent = stuList.remove(deleteIndex);
            if (removeStudent.getStuId() != null) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * 修改学生信息
     *
     * @param modifyStudent
     * @return
     */
    public static boolean modifyStudent(Student modifyStudent) {
        for (int i = 0; i < stuList.size(); i++) {
            Student oldStudent = stuList.get(i);
            if (oldStudent.getStuId() == modifyStudent.getStuId()) {
                deleteStudent(oldStudent);
                addStudent(modifyStudent);
                return true;
            }
        }
        return false;
    }

    /**
     * 查找学生信息
     *
     * @param findStudent
     * @return
     */
    public static List<Student> findStudent(Student findStudent) {
        List<Student> findResultStudentList = new ArrayList<>();
        for (int i = 0; i < stuList.size(); i++) {
            Student oldStudent = stuList.get(i);
            if (oldStudent.getStuId() == findStudent.getStuId() || oldStudent.getStuName() == findStudent.getStuName()) {
                findResultStudentList.add(oldStudent);
            }
        }
        return findResultStudentList;
    }

    /**
     * 打印学生信息
     */
    public static void printStudent() {
        if (0 != stuList.size()) {
            for (int i = 0; i < stuList.size(); i++) {
                System.out.println("第" + (i + 1) + "个" + stuList.get(i).toString());
            }
        } else {
            System.out.println("当前该系统中无学生信息!");
        }
    }
}
