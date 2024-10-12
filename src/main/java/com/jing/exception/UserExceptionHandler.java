package com.jing.exception;

public class UserExceptionHandler extends Exception{
    private String msg;

    public UserExceptionHandler(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
