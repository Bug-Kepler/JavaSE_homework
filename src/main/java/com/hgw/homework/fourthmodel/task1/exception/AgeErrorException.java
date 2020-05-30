package com.hgw.homework.fourthmodel.task1.exception;

/**
 * 年龄异常类
 * @author 追风同学
 * @date 2020/05/25 22:57
 * @description
 */
public class AgeErrorException extends Exception{
    public AgeErrorException() {
    }

    public AgeErrorException(String message) {
        super(message);
    }
}
