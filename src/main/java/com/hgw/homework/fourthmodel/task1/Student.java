package com.hgw.homework.fourthmodel.task1;

import com.hgw.homework.fourthmodel.task1.exception.AgeErrorException;
import com.hgw.homework.fourthmodel.task1.exception.StudentIdErrorException;

import java.io.Serializable;
import java.util.Objects;

/**
 * 学生信息实体类
 *
 * @author 追风同学
 * @date 2020/05/21 17:24
 * @description
 */
public class Student implements Serializable {

    private static final long serialVersionUID = -9169584544096257165L;
    /**
     * 学号:7位数字,
     */
    private String stuId;
    /**
     * 姓名
     */
    private String stuName;
    /**
     * 年龄:合理的范围[0~120]
     */
    private int stuAge;

    public Student() {
    }

    public Student(String stuId, String stuName, int stuAge) throws AgeErrorException, StudentIdErrorException {
        setStuId(stuId);
        setStuName(stuName);
        setStuAge(stuAge);
//        this.stuId = stuId;
//        this.stuName = stuName;
//        this.stuAge = stuAge;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) throws StudentIdErrorException {
        if (stuId.matches("[\\d]{7}")) {
            this.stuId = stuId;
        } else {
            throw new StudentIdErrorException("学号信息输入有误,请输入7位数字!");
        }
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

    public void setStuAge(int stuAge) throws AgeErrorException {
        if (stuAge < 0 || stuAge > 120) {
            throw new AgeErrorException("输入年龄不合理,请输入0-120之间的年龄!");
        } else {
            this.stuAge = stuAge;
        }
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
        return Objects.equals(stuId, student.stuId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stuId);
    }
}
