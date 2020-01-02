package com.bei.rronet;

import com.bhfae.net.http.base.BaseRetrofitManager;

import java.util.HashMap;

import io.reactivex.Flowable;

/**
 * @author: created by ZhaoBeibei on 2020-01-02 14:03
 * @describe:
 */
public class CommonRetrofitManager extends BaseRetrofitManager {

    private CommonService mCommonService;

    private static final String BASE_URL = "http://10.10.200.71:81/online-server/";

    public CommonRetrofitManager() {
        mCommonService = getRetrofit(MyApplication.getContext(),BASE_URL).create(CommonService.class);
    }

    private static class SingletonHolder {
        private static final CommonRetrofitManager INSTANCE = new CommonRetrofitManager();
    }

    public static CommonRetrofitManager getInstance() {
        return CommonRetrofitManager.SingletonHolder.INSTANCE;
    }

    /**
     * 获取token
     */
    public Flowable<TokenEntity> getToken(HashMap<String, String> map) {
        return mCommonService.getToken(map);
    }


}
