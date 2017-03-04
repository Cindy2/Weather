package com.example.admin.weather.util;

import android.text.TextUtils;

import com.example.admin.weather.db.CoolWeatherDB;
import com.example.admin.weather.model.City;
import com.example.admin.weather.model.Country;
import com.example.admin.weather.model.Province;

/**
 * Created by Admin on 2017/3/3.
 * 工具类 解析和处理服务器返回的数据
 * “代号|城市,代号|城市”这种格式
 */

public class Utility {
    public synchronized static boolean handleProvincesResponse(CoolWeatherDB coolWeatherDB, String stringBuilder){
        if (!TextUtils.isEmpty(stringBuilder)){
            String[] allProvinces = stringBuilder.split(",");
            if (allProvinces != null && allProvinces.length > 0){
                for (String p : allProvinces){
                    String[] array = p.split("\\|");
                    Province province = new Province();
                    province.setProvinceCode(array[0]);
                    province.setProvinceName(array[1]);
                    // 将解析出来的数据存储到Province表
                    coolWeatherDB.saveProvince(province);
                }
                return true;
            }
        }
        return false;
    }
    public synchronized static boolean handleCitiesResponse(CoolWeatherDB coolWeatherDB, String stringBuilder,int provinceId){
        if (!TextUtils.isEmpty(stringBuilder)){
            String[] allCities = stringBuilder.split(",");
            if (allCities != null && allCities.length > 0){
                for (String p : allCities){
                    String[] array = p.split("\\|");
                    City city = new City();
                    city.setCityCode(array[0]);
                    city.setCityName(array[1]);
                    // 将解析出来的数据存储到City表
                    coolWeatherDB.saveCity(city);
                }
                return true;
            }
        }
        return false;
    }
    public synchronized static boolean handleCountryResponse(CoolWeatherDB coolWeatherDB, String stringBuilder,int cityId){
        if (!TextUtils.isEmpty(stringBuilder)){
            String[] allCountry = stringBuilder.split(",");
            if (allCountry != null && allCountry.length > 0){
                for (String p : allCountry){
                    String[] array = p.split("\\|");
                    Country country = new Country();
                    country.setCountryCode(array[0]);
                    country.setCountryName(array[1]);
                    // 将解析出来的数据存储到Country表
                    coolWeatherDB.saveCounty(country);
                }
                return true;
            }
        }
        return false;
    }
}
