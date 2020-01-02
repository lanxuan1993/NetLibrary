package com.bei.rronet;

import java.util.HashMap;

import io.reactivex.Flowable;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * @author: created by ZhaoBeibei on 2020-01-02 14:13
 * @describe:
 */
public interface CommonService {
    //刷新token
    @POST("api")
    Flowable<TokenEntity> getToken(@QueryMap HashMap<String, String> body);

}
