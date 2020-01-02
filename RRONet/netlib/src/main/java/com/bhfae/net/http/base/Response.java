package com.bhfae.net.http.base;

/**
 * author: zhaobeibei
 * created on: 2019/12/24
 * description:response基类
 */

public class Response<T> {

    private int status;
    private String msg;
    private T data;

    public Response() {
    }


    public Response(int status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(T data) {
        this.data = data;
    }
}
