package com.code.apodemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {

    // this is where we add all of our advices for logging
    //Match only addAccount method in AccountDao
   // @Before("execution(public void com.code.apodemo.Dao.AccountDao.addAccount())") //run this code before target object method
    @Before("execution(public void add*())") //run this code before target object method
    public void beforeAddAccountAdvice() {

        System.out.println("\n=====>>> Executing @Before advice on addAccount()");

    }


}
