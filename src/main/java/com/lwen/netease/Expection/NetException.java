package com.lwen.netease.Expection;


public class NetException extends RuntimeException {
    private Integer code;
    private Object data;

    public NetException(Integer code, String msg,Object data) {
        super(msg);
        this.code = code;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
