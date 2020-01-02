package com.bhfae.net.http.tool;


import io.reactivex.Flowable;
import io.reactivex.functions.Function;

/**
 * @说明: 异常处理
 * @作者: zhaobeibei
 * @时间: 2019-12-24
 */

public class HttpResultFunc<T> implements Function<Throwable, Flowable<T>> {
    @Override
    public Flowable<T> apply(Throwable throwable) throws Exception {
        return Flowable.error(ExceptionConverter.convertException(throwable));
    }
}
