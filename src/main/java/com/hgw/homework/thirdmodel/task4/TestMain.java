package com.hgw.homework.thirdmodel.task4;

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

    //主方法体
    public static void main(String[] args) {
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
            int command = scanner.nextInt();
            //命令成功的标志
            boolean whetherSuccess=false;
//            int id=0;
//            String name=null;
//            int age=0;
            switch (command) {
                case 1:
                    System.out.println("请输入要添加学生的信息:学号 姓名 年龄:");
                    String[] in1 = getIn(3);
                    whetherSuccess= addStudent(new Student(Integer.parseInt(in1[0]), in1[1], Integer.parseInt(in1[2])));
                    if (whetherSuccess){
                        System.out.println("添加学生信息成功!");
                    }else{
                        System.out.println("添加学生信息失败!");
                    }
                    break;
                case 2:
                    System.out.println("请输入要删除学生的信息:学号 :");
                    String[] in2=getIn(1);
//                    id = scanner.nextInt();
//                    name = scanner.next();
//                    age = scanner.nextInt();
//                    whetherSuccess = deleteStudent(new Student(Integer.parseInt(in2[0]), in2[1], Integer.parseInt(in2[2])));
                    whetherSuccess = deleteStudent(new Student(Integer.parseInt(in2[0]), null, 0));
                    if (whetherSuccess){
                        System.out.println("删除学生信息成功!");
                    }else{
                        System.out.println("删除学生信息失败!");
                    }
                    break;
                case 3:
                    System.out.println("请输入要修改学生的信息:学号(不能修改) 姓名 年龄:");
                    String[] in3=getIn(3);
                    whetherSuccess = modifyStudent(new Student(Integer.parseInt(in3[0]), in3[1], Integer.parseInt(in3[2])));
                    if (whetherSuccess){
                        System.out.println("修改学生信息成功!");
                    }else {
                        System.out.println("修改学生信息失败!");
                    }
                    break;
                case 4:
                    System.out.println("请输入要查找学生的信息:学号 :");
                    String[] in4=getIn(2);
                    List<Student> findResult = findStudent(new Student(Integer.parseInt(in4[0]), in4[1],0));
                    if(findResult.size()!=0){
                        for (int i = 0; i < findResult.size(); i++) {
                            System.out.println("该生信息为: "+findResult.get(i).toString());
                        }
                    }else{
                        System.out.println("没有该生的信息!");
                    }
                    break;
                case 5:
                    printStudent();
                    break;
                case 6:
                    flag = false;
                    System.out.println("已退出学生管理系统.....");
                    break;
                default:
                    System.out.println("该命令有错误,请重新输入!");
                    break;
            }
        }
    }

    /**
     * 获取用户输入的信息
     * @return
     */
    public static String[] getIn(int i){
        Scanner scanner = new Scanner(System.in);
        String[] inArray=new String[3];
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
     * @param newStudent
     * @return
     */
    public static boolean addStudent(Student newStudent) {
        //如果列表中存在将要添加的学生信息
        if (stuList.contains(newStudent)){
            return false;
        }else {
            return stuList.add(newStudent);
        }
    }

    /**
     * 删除学生信息
     * @param deldteStudent
     * @return
     */
    public static boolean deleteStudent(Student deldteStudent) {
        if (0 == stuList.size()){
            return false;
        }
        if (stuList.contains(deldteStudent)){
            int deleteIndex = stuList.indexOf(deldteStudent);
            Student removeStudent= stuList.remove(deleteIndex);
            if (removeStudent.getStuId()!=0){
                return true;
            }else{
                return false;
            }
        }else {
            return false;
        }
    }

    /**
     * 修改学生信息
     * @param modifyStudent
     * @return
     */
    public static boolean modifyStudent(Student modifyStudent){
        for (int i = 0; i < stuList.size(); i++) {
            Student oldStudent = stuList.get(i);
            if (oldStudent.getStuId()==modifyStudent.getStuId()){
                deleteStudent(oldStudent);
                addStudent(modifyStudent);
                return true;
            }
        }
        return false;
    }

    /**
     * 查找学生信息
     * @param findStudent
     * @return
     */
    public static List<Student> findStudent(Student findStudent){
        List<Student> findResultStudentList=new ArrayList<>();
        for (int i = 0; i < stuList.size(); i++) {
            Student oldStudent = stuList.get(i);
            if (oldStudent.getStuId()==findStudent.getStuId()||oldStudent.getStuName()==findStudent.getStuName()){
                findResultStudentList.add(oldStudent);
            }
        }
        return findResultStudentList;
    }

    /**
     * 打印学生信息
     */
    public static void printStudent(){
        for (int i = 0; i < stuList.size(); i++) {
            System.out.println("第"+(i+1)+"个"+stuList.get(i).toString());
        }
    }
}
