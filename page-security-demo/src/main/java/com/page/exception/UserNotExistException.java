package com.page.exception;


//自定义的异常类
public class UserNotExistException extends RuntimeException {
    private static final long serialVersionUID = -7278651877210870872L;
    private String id;

    public UserNotExistException(String id) {
        super("user not exist");
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
