package com.bhfae.net.http.tool;

import android.util.Log;

import com.bhfae.net.constant.HttpConstant;
import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.ParseException;

import retrofit2.HttpException;


/**
 * @说明 网络请求异常处理
 * @作者 zhaobeibei
 * @时间 2019/12/24
 */
public class ExceptionConverter {

    private static final String TAG = "ExceptionConverter";

    public static Throwable convertException(Throwable e) {
        Throwable finalThrow;
        Log.e(TAG, e.toString());
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            int httpCode = httpException.code();
            switch (httpCode) {
                case HttpConstant.REQUEST_TIMEOUT:
                case HttpConstant.GATEWAY_TIMEOUT:
                case HttpConstant.BAD_GATEWAY:
                    finalThrow = new Throwable(HttpConstant.NET_ERROR_MSG,
                            new Throwable(String.valueOf(HttpConstant.NET_ERROR_CODE)));
                    break;
                case HttpConstant.SERVICE_UNAVAILABLE:
                case HttpConstant.FORBIDDEN:
                case HttpConstant.NOT_FOUND:
                case HttpConstant.UNAUTHORIZED:
                case HttpConstant.INTERNAL_SERVER_ERROR:
                default:
                    finalThrow = new Throwable(HttpConstant.SERVER_ERROR_MSG,
                            new Throwable(String.valueOf(HttpConstant.SERVER_ERROR_CODE)));
                    break;
            }
            return finalThrow;
        } else if (e instanceof ServerException) {
            ServerException resultException = (ServerException) e;
            finalThrow = new Throwable(resultException.getMessage(), new Throwable(resultException.getCode() + ""));
            return finalThrow;
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {
            finalThrow = new Throwable(HttpConstant.SERVER_ERROR_MSG,
                    new Throwable(String.valueOf(HttpConstant.SERVER_ERROR_CODE)));
            return finalThrow;
        } else if (e instanceof ConnectException || e instanceof SocketTimeoutException) {
            finalThrow = new Throwable(HttpConstant.NET_ERROR_MSG,
                    new Throwable(String.valueOf(HttpConstant.NET_ERROR_CODE)));
            return finalThrow;
        } else if (e instanceof UnknownHostException) {
            finalThrow = new Throwable(HttpConstant.SERVER_ERROR_MSG,
                    new Throwable(String.valueOf(HttpConstant.SERVER_ERROR_CODE)));
            return finalThrow;
        } else {
            finalThrow = new Throwable(HttpConstant.DEFAULT_ERROR_MSG,
                    new Throwable(String.valueOf(HttpConstant.DATA_ERROR_CODE)));
            return finalThrow;
        }
    }
}
