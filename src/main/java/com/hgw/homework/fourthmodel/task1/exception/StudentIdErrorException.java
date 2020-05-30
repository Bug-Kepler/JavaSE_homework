package com.hgw.homework.fourthmodel.task1.exception;

/**
 * 学号异常类
 * @author 追风同学
 * @date 2020/05/25 22:53
 * @description
 */
public class StudentIdErrorException extends Exception {

    public StudentIdErrorException() {
    }

    public StudentIdErrorException(String message) {
        super(message);
    }
}
