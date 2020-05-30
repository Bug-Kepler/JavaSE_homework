package com.hgw.homework.fourthmodel.task4;

import java.io.Serializable;

/**
 * User实体类
 *
 * @author 追风同学
 * @date 2020/05/26 20:04
 * @description
 */
public class User implements Serializable {

    private static final long serialVersionUID = 7983228716732873401L;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     */
    private String password;

    public User() {
    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
