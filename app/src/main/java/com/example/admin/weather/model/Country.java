package com.example.admin.weather.model;

/**
 * Created by Admin on 2017/3/2.
 */

public class Country {

    private int id;
    private String countryName;
    private String countryCode;
    private int cityId;
    public int getId() {
        return id;
    }

    public Country setId(int id) {
        this.id = id;
        return this;
    }

    public String getCountryName() {
        return countryName;
    }

    public Country setCountryName(String countryName) {
        this.countryName = countryName;
        return this;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public Country setCountryCode(String countryCode) {
        this.countryCode = countryCode;
        return this;
    }

    public int getCityId() {
        return cityId;
    }

    public Country setCityId(int cityId) {
        this.cityId = cityId;
        return this;
    }

}
