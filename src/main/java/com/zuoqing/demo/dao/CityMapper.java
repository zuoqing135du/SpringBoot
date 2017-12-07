package com.zuoqing.demo.dao;

import com.zuoqing.demo.entity.City;
import com.zuoqing.demo.entity.CityExample;
import com.zuoqing.demo.entity.UserCityDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CityMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table city
     *
     * @mbggenerated
     */
    int countByExample(CityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table city
     *
     * @mbggenerated
     */
    int deleteByExample(CityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table city
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table city
     *
     * @mbggenerated
     */
    int insert(City record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table city
     *
     * @mbggenerated
     */
    int insertSelective(City record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table city
     *
     * @mbggenerated
     */
    List<City> selectByExample(CityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table city
     *
     * @mbggenerated
     */
    City selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table city
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") City record, @Param("example") CityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table city
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") City record, @Param("example") CityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table city
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(City record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table city
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(City record);

    /*//根据主码查数据*/
    City selectById(Integer id);

    //查询List
    List<City> selectList(Integer provinceId);

    //查询List
    List<City> selectList2();

    List<City> selectList3(City city);

    int insert2(City city);

    //查询所有List
    List<City> selectAllList();

    int updateCity(@Param("record") City record);

    int deleteById(Integer id);

    //模拟多表连接查询
    List<UserCityDTO> selectUserCityDTOList(Integer id);

}