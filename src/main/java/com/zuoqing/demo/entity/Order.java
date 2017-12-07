package com.zuoqing.demo.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * Created by zuoqing on 2017/12/7.
 */
//@Entity
public class Order {

    @Id
    @GeneratedValue
    private int orderId;

    private int orderUserId;

    private int orderAddress;

    private BigDecimal orderTotalprice;

    private Long createTime;

    private Long updateTime;

    private int orderStatus;

    private String orderComments;



}
