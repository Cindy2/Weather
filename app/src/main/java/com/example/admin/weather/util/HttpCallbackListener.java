package com.example.admin.weather.util;

/**
 * Created by Admin on 2017/3/3.
 *  HttpCallbackListener 接口来回调服务返回的结果
 */
public interface HttpCallbackListener {
     void onFinish(String stringBuilder);
     void onError(Exception e);
}
