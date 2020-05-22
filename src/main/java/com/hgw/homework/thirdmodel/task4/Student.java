package com.hgw.homework.thirdmodel.task4;

import java.util.Objects;

/**
 * 学生信息实体类
 * @author 追风同学
 * @date 2020/05/21 17:24
 * @description
 */
public class Student {
    /**
     * 学号
     */
    private int stuId;
    /**
     * 姓名
     */
    private String stuName;
    /**
     * 年龄
     */
    private int stuAge;

    public Student() {
    }

    public Student(int stuId, String stuName, int stuAge) {
        this.stuId = stuId;
        this.stuName = stuName;
        this.stuAge = stuAge;
    }

    public int getStuId() {
        return stuId;
    }

    public void setStuId(int stuId) {
        this.stuId = stuId;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public int getStuAge() {
        return stuAge;
    }

    public void setStuAge(int stuAge) {
        this.stuAge = stuAge;
    }

    @Override
    public String toString() {
        return "Student{" +
                "学号=" + stuId +
                ", 姓名='" + stuName + '\'' +
                ", 年龄=" + stuAge +
                '}';
    }
    //表示区别的属性只有id
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Student student = (Student) o;
        return stuId == student.stuId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(stuId);
    }
}
