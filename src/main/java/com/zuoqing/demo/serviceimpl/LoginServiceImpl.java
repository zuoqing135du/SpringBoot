package com.zuoqing.demo.serviceimpl;

import com.zuoqing.demo.dao.GirlRepository;
import com.zuoqing.demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {


    @Override
    public String login(String userName, String passWord) {
        return "";
    }

    @Autowired
    private GirlRepository girlRepository;

}
