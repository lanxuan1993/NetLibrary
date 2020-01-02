package com.bhfae.net.http.base;


import android.content.Context;

import com.bhfae.net.constant.HttpConstant;
import com.bhfae.net.http.https.SSLSocketFactoryUtils;
import com.bhfae.net.http.tool.HttpResultFunc;
import com.bhfae.net.utils.NetworkUtils;

import org.reactivestreams.Publisher;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * author: zhaobeibei
 * created on: 2019/12/24
 * description:retrofit配置基类
 */

public abstract class BaseRetrofitManager {

    private static final String TAG = "BaseRetrofitManager";
    //超时时间 60s
    private static final int CONNECT_TIME_OUT = 60;

    //设置缓存的大小50M
    private static final int CACHE_MAX = 1024 * 1024 * 50;

    private static Converter.Factory gsonConverterFactory = GsonConverterFactory.create();
    private static CallAdapter.Factory rxJavaCallAdapterFactory = RxJava2CallAdapterFactory.create();

    private Context mContext;
    private static String mBaseUrl;
    private Map<String, String> mHeaderParamsMap = new HashMap<>();

    /**
     * 获取Retrofit实例
     *
     * @return Retrofit
     */
    protected Retrofit getRetrofit(Context context, String url) {
        mContext = context;
        mBaseUrl = url;
        NetworkUtils.getContext(mContext);
        Retrofit retrofit = new Retrofit.Builder()
                .client(getClient())
                .baseUrl(mBaseUrl)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(rxJavaCallAdapterFactory)
                .build();
        return retrofit;
    }

    /**
     * 获取okHttpClient实例
     *
     * @return OkHttpClient
     */
    protected OkHttpClient getClient() {
        //缓存
        Cache cache = new Cache(mContext.getCacheDir(), CACHE_MAX);
        //log信息
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(new ForceCacheInterceptor())
                .retryOnConnectionFailure(true)
                .connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
                .cache(cache)
                .sslSocketFactory(SSLSocketFactoryUtils.createSSLSocketFactory(), SSLSocketFactoryUtils.createTrustAllManager())
                .build();
        return client;
    }

    /**
     * deviceId  Interceptor
     */
    private class HeaderDeviceIdInterceptor implements Interceptor {

        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            Request.Builder builder = chain.request().newBuilder();
            String deviceId = mHeaderParamsMap.get("deviceId");
            builder.addHeader("guestId", deviceId);
            return chain.proceed(builder.build());
        }
    }

    /**
     * token  Interceptor
     */
    private class HeaderTokenInterceptor implements Interceptor {

        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            Request.Builder builder = chain.request().newBuilder();
            String tokenS = "";
            builder.addHeader("TOKEN", tokenS);
            return chain.proceed(builder.build());
        }
    }


    /**
     * Cache Interceptor
     */
    private class ForceCacheInterceptor implements Interceptor {
        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            Request.Builder builder = chain.request().newBuilder();
            if (!NetworkUtils.isConnected()) {
                builder.cacheControl(CacheControl.FORCE_CACHE);
            }
            return chain.proceed(builder.build());
        }
    }


    protected <T> FlowableTransformer<Response<T>, T> applySchedulers() {

        return new FlowableTransformer<Response<T>, T>() {

            @Override
            public Publisher<T> apply(Flowable<Response<T>> upstream) {
                return upstream
                        .onErrorResumeNext(new HttpResultFunc<Response<T>>())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .flatMap(new Function() {
                            @Override
                            public Object apply(Object response) throws Exception {
                                return flatResponse((Response<Object>) response);
                            }
                        });
            }
        };
    }

    public <T> Flowable<T> flatResponse(final Response<T> response) {
        return Flowable.create(new FlowableOnSubscribe<T>() {
            @Override
            public void subscribe(FlowableEmitter<T> emitter) throws Exception {
                if (response != null) {
                    if (response.getStatus() == HttpConstant.CODE_SUCCESS) {
                        T data = response.getData();
                        if (data == null) {
                            try {
                                data = (T) new BaseEntity();
                            } catch (ClassCastException e) {
                                e.printStackTrace();
                            }
                        }
                        emitter.onNext(data);
                    } else {
                        emitter.onError(new Throwable(response.getMsg(), new Throwable(response.getStatus() + "")));
                    }
                    emitter.onComplete();
                }
            }
        }, BackpressureStrategy.BUFFER);
    }
}
