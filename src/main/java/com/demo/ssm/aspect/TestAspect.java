package com.demo.ssm.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * Created by gmy on 15/8/10.
 */
//@Component
//@Aspect
public class TestAspect {
    @Pointcut("execution(* com.demo.ssm.controller.ItemsController.*(..))")
    public void aspectTest() {

    }

    @Before("aspectTest()")
    public void printMessage() {
        System.out.println("Before calling the function in the controller..");
    }

    @AfterReturning("aspectTest()")
    public void printReturn() {
        System.out.println("After returning from the controller..");
    }
    @AfterThrowing("aspectTest()")
    public void printWrong() {
        System.out.println("After throwing from the controller..");
    }

//    @Around("aspectTest()")
//    public void printAroundMessage(ProceedingJoinPoint joinPoint) throws Throwable {
//        System.out.println("Before in the around..");
//        joinPoint.proceed();
//        System.out.println("After in the around..");
//    }
}
