package com.hgw.homework.fourthmodel.task4;


import java.io.Serializable;

/**
 * UserMessage实体类
 *
 * @author 追风同学
 * @date 2020/05/26 20:02
 * @description
 */
public class UserMessage implements Serializable {

    private static final long serialVersionUID = -7301851283359562793L;
    private String type;
    private User user;

    public UserMessage() {
    }

    public UserMessage(String type, User user) {
        this.type = type;
        this.user = user;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
