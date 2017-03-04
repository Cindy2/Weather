package com.example.admin.weather.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Admin on 2017/3/3.
 * 和服务器的交互
 */

public class HttpUtil {
    public static  void sendHttpRequest(final String address,final HttpCallbackListener listener){
        new Thread(new Runnable() {
            public Exception e;

            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    URL url = new URL(address);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream inputStream = connection.getInputStream();
//                   reader
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//                    response
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line);
                    }
                    if (listener != null) {
                        // 回调onFinish()方法
                        listener.onFinish(stringBuilder.toString());
                    }
                    if (listener != null) {
                        // 回调onError()方法
                        listener.onError(e);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
                finally {
                    if (connection != null){
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }
}
