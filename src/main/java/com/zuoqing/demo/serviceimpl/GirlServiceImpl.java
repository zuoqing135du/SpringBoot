package com.zuoqing.demo.serviceimpl;

import com.zuoqing.demo.dao.GirlRepository;
import com.zuoqing.demo.entity.Girl;
import com.zuoqing.demo.enums.ResultEnum;
import com.zuoqing.demo.exception.GirlException;
import com.zuoqing.demo.service.GirlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GirlServiceImpl implements GirlService {

    @Autowired
    private GirlRepository girlRepository;

    @Override
    public List<Girl> girls() {
        return girlRepository.findAll();
    }

    @Override
    @Transactional
    public void insertTwo(){
        Girl girlA = new Girl();
        girlA.setCupSize("A");
        girlA.setAge(18);
        girlRepository.save(girlA);

        Girl girlB = new Girl();
        girlB.setCupSize("B111");
        girlB.setAge(19);
        girlRepository.save(girlB);
    }

    //统一异常抛出，交给 ExceptionHandle 处理
    @Override
    public void getAge(Integer id) throws Exception{
        Girl girl = girlRepository.findOne(id);
        Integer age = girl.getAge();
        if(age < 10){
            //小学生
            throw  new GirlException(ResultEnum.PRIMARY_SCHOOL);
        }else if(age > 0 && age < 16){
            //初中生
            throw  new GirlException(ResultEnum.MIDDLE_SCHOOL);
        }
    }

    /**
     * 通过id查询用户信息
     * @param id
     * @return
     */
    @Override
    public Girl finOne(Integer id){
        return girlRepository.findOne(id);
    }
}
