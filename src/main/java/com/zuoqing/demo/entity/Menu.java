package com.zuoqing.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Menu {

    @Id
    @GeneratedValue
    int menuId;

    String menuCode;

    String menuType;

    String menuLevel;

    String menuUrl;

    String menuName;

    String menuParentId;

    int state;

    String menuNote;

}
