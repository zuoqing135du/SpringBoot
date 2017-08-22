package com.zuoqing.demo.service;

import com.zuoqing.demo.entity.Girl;

import java.util.List;

public interface GirlService {
    List<Girl> girls();

    void insertTwo();

    void getAge(Integer id) throws Exception;

    Girl finOne(Integer id);
}
