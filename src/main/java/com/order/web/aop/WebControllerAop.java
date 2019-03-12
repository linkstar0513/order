package com.order.web.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class WebControllerAop {

    @Pointcut("execution(public * com.order.web.controller.UserController.userRole())")
    public void webLog(){
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint){
        System.out.println("我是前置通知!!!");
        Object[] args = joinPoint.getArgs();
        System.out.println("" + joinPoint.getSignature().getName() + " logStart..." + Arrays.asList(args));
    }

}
