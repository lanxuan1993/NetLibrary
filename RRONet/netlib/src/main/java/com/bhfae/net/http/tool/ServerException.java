package com.bhfae.net.http.tool;


/**
 * @说明 server exception
 * @作者 zhaobeibei
 * @时间 2019/12/24
 */
public class ServerException extends RuntimeException {
    private int code;
    private String msg;

    public ServerException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
