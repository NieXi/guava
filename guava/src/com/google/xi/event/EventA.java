package com.google.xi.event;

import java.util.StringJoiner;

public class EventA {

    private int code;
    private String msg;

    public EventA(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", EventA.class.getSimpleName() + "[", "]")
                .add("code=" + code)
                .add("msg='" + msg + "'")
                .toString();
    }
}
