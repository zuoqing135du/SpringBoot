package com.zuoqing.demo.entity;

/**
 * Created by zuoqing on 2017/12/7.
 */
public class UserCityDTO {

    private City city;
    private UUser user;

    public UserCityDTO() {
    }

    public UserCityDTO(City city, UUser user) {
        this.city = city;
        this.user = user;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public UUser getUser() {
        return user;
    }

    public void setUser(UUser user) {
        this.user = user;
    }
}
