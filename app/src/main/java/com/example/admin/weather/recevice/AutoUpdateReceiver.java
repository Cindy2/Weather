package com.example.admin.weather.recevice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.admin.weather.service.AutoUpdateService;

/**
 * Created by Admin on 2017/3/6.
 * 后台定时更新
 */

public class AutoUpdateReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context, AutoUpdateService.class);
        context.startService(i);
    }
}
