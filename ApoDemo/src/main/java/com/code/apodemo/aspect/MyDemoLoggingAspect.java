package com.code.apodemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {
    // This pointcut matches the execution of any method (*)
// in any class (*) inside the package 'com.code.apodemo.Dao',
// regardless of method name or arguments (..)
    // and we can reuse this pointcut expression allow to apply to multiple advices
    @Pointcut("execution(* com.code.apodemo.Dao.*.*(..))")
    private void forDaoPackage(){

    }

    // this is where we add all of our advices for logging
    //Match only addAccount method in AccountDao
   // @Before("execution(public void com.code.apodemo.Dao.AccountDao.addAccount())") //run this code before target object method
   // @Before("execution(public void add*())") //match with any add method
    //@Before("execution(* add*())")  //match with any return type
   // @Before("execution(* add*(com.code.apodemo.Account))")  //match with specific parameter
    //@Before("execution(* add*(com.code.apodemo.Account,..))")  //match with specific parameter and any other para
    @Before("forDaoPackage()")  //match package all classes and methods with any parameter
    public void beforeAddAccountAdvice() {

        System.out.println("\n=====>>> Executing @Before advice on addAccount()");

    }
    @Before("forDaoPackage()")
    public void performApiAnalytics() {
        System.out.println("\n=====>>> Performing API analytics");
    }


}
