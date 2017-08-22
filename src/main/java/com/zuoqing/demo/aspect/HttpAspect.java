package com.zuoqing.demo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Component  注解用来将 HttpAspect 引入到spring容器里面
 */

@Aspect
@Component
public class HttpAspect {

    //Spring 自带的日志工具
    private final static Logger logger = LoggerFactory.getLogger(HttpAspect.class);

    //log方法在GirlController里面的方法执行之前就已经执行了。
   /* @Before(("execution(public * com.zuoqing.demo.controller.GirlController.*(..))"))
    public void log(){
        System.out.println(1111111);
    }

    @After(("execution(public * com.zuoqing.demo.controller.GirlController.*(..))"))
    public void doAfter(){
        System.out.println("com.zuoqing.demo.controller.GirlController,doAfter");
    }*/

    //简写版，功能同上
    @Pointcut(("execution(public * com.zuoqing.demo.controller.GirlController.*(..))"))
    public void log2(){
    }

    @Before("log2()")
    public void doBefore2(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        //import javax.servlet.http.HttpServletRequest;
        HttpServletRequest request = attributes.getRequest();
        //url
        logger.info("url={}",request.getRequestURL());
        //method
        logger.info("method={}",request.getMethod());
        //ip
        logger.info("ip={}",request.getRemoteAddr());
        //类方法
        logger.info("class_method={}",joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName());
        //参数
        logger.info("args={}",joinPoint.getArgs());
    }

    @After(("log2()"))
    public void doAfter2(){
//        System.out.println("com.zuoqing.demo.controller.GirlController,doAfter");
        logger.info("222222222222");
    }

    //Controller里面方法的返回值
    @AfterReturning(returning = "object", pointcut = "log2()")
    public void doAfterReturning(Object object){
        logger.info("response={}",object.toString());
    }
}

















