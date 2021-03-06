package com.example.admin.weather.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;

import com.example.admin.weather.util.HttpCallbackListener;
import com.example.admin.weather.util.HttpUtil;
import com.example.admin.weather.util.Utility;

/**
 * Created by Admin on 2017/3/4.
 */

public class AutoUpdateService extends Service {
    private String response;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                updateWeather();
            }
        }).start();
        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        int anHour = 8 * 60 * 60 * 1000; // 这是8小时的毫秒数
        long triggerTime = SystemClock.elapsedRealtime()+anHour;
        Intent i =new Intent(this,AutoUpdateService.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,0,i,0);
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,triggerTime,pendingIntent);
        return super.onStartCommand(intent, flags, startId);
    }
    /**
     * 更新天气信息。
     */
    private void updateWeather() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String weatherCode = sharedPreferences.getString("weather_code","");
        String address = "http://www.weather.com.cn/data/cityinfo/"+weatherCode + ".html";
        HttpUtil.sendHttpRequest(address, new HttpCallbackListener() {
            @Override
            public void onFinish(String stringBuilder) {
                Utility.handleWeatherRespones(AutoUpdateService.this,response);
            }
            @Override
            public void onError(Exception e) {
                e.printStackTrace();
            }
        });
    }
}
