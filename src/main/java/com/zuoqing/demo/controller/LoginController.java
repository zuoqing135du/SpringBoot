package com.zuoqing.demo.controller;

import com.zuoqing.demo.Properties.GirlProperties;
import com.zuoqing.demo.entity.Girl;
import com.zuoqing.demo.entity.UUser;
import com.zuoqing.demo.serviceimpl.LoginServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/login")
public class LoginController {

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    protected LoginServiceImpl LoginService;

    @Autowired
    private GirlProperties girlProperties;

    //前端
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseBody
    public Object login() {
        Girl girl = new Girl();
        girl.setAge(11);
        girl.setCupSize("A");

        return girl;
    }

    //跳转到登录页面
    @RequestMapping(value = "/toLogin", method = RequestMethod.GET)
    public String toLogin() {

        return "login";
    }

    //登陆验证，这里方便url测试，正式上线需要使用POST方式提交
    @RequestMapping(value = "/pcLogin", method = RequestMethod.GET)
    public String pcLogin(UUser user) {
        if (user.getNickname() != null && user.getPswd() != null) {
            UsernamePasswordToken token = new UsernamePasswordToken(user.getNickname(), user.getPswd(), "login");
            Subject currentUser = SecurityUtils.getSubject();
            logger.info("对用户[" + user.getNickname() + "]进行登录验证..验证开始");
            try {
                currentUser.login( token );
                //验证是否登录成功
                if (currentUser.isAuthenticated()) {
                    logger.info("用户[" + user.getNickname() + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
                    System.out.println("用户[" + user.getNickname() + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
                    return "redirect:/admin/toMain";
                } else {
                    token.clear();
                    System.out.println("用户[" + user.getNickname() + "]登录认证失败,重新登陆");
                    return "redirect:/login/toLogin";
                }
            } catch ( UnknownAccountException uae ) {
                logger.info("对用户[" + user.getNickname() + "]进行登录验证..验证失败-username wasn't in the system");
            } catch ( IncorrectCredentialsException ice ) {
                logger.info("对用户[" + user.getNickname() + "]进行登录验证..验证失败-password didn't match");
            } catch ( LockedAccountException lae ) {
                logger.info("对用户[" + user.getNickname() + "]进行登录验证..验证失败-account is locked in the system");
            } catch ( AuthenticationException ae ) {
                logger.error(ae.getMessage());
            }
        }
        return null;
    }



}
