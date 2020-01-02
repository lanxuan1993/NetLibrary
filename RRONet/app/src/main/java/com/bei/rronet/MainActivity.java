package com.bei.rronet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HashMap<String, String> map = new HashMap<>();
        map.put("apiName", "fetchToken");
        map.put("apiVersion", "1.0");
        map.put("token", "");
        map.put("appVersion", "2.8.0");
        map.put("saleSystem", "WMS");
        map.put("clientType", "02");
        map.put("clientIp", "");
        map.put("stationId", "T00000");
        map.put("visitorId", "");

        CommonRetrofitManager.getInstance().getToken(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<TokenEntity>() {
                    @Override
                    public void accept(TokenEntity entity) throws Exception {
                        Log.i("retrofit", "accept: " + entity.getToken());
                        Toast.makeText(MainActivity.this, entity.getToken(), Toast.LENGTH_SHORT).show();

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.i("retrofit", "accept: 错误");
                    }
                });
    }
}
