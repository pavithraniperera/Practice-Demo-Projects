package com.code.apodemo.aspect;

import com.code.apodemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {
    // This pointcut matches the execution of any method (*)
// in any class (*) inside the package 'com.code.apodemo.Dao',
// regardless of method name or arguments (..)
    // and we can reuse this pointcut expression allow to apply to multiple advices
    @Pointcut("execution(* com.code.apodemo.Dao.*.*(..))")
    private void forDaoPackage(){

    }
    // create a pointcut for getter methods
    @Pointcut("execution(* com.code.apodemo.Dao.*.get*(..))")
    private void getter() {}

    // create a pointcut for setter methods
    @Pointcut("execution(* com.code.apodemo.Dao.*.set*(..))")
    private void setter() {}
    // create pointcut: include package ... exclude getter/setter
    @Pointcut("forDaoPackage() && !(getter() || setter())")
    private void forDaoPackageNoGetterSetter() {

    }

    // this is where we add all of our advices for logging
    //Match only addAccount method in AccountDao
   // @Before("execution(public void com.code.apodemo.Dao.AccountDao.addAccount())") //run this code before target object method
   // @Before("execution(public void add*())") //match with any add method
    //@Before("execution(* add*())")  //match with any return type
   // @Before("execution(* add*(com.code.apodemo.Account))")  //match with specific parameter
    //@Before("execution(* add*(com.code.apodemo.Account,..))")  //match with specific parameter and any other para
    @Before("forDaoPackageNoGetterSetter()")  //match package all classes and methods with any parameter
    public void beforeAddAccountAdvice(JoinPoint joinPoint) {

        System.out.println("\n=====>>> Executing @Before advice on addAccount()");
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        System.out.println("MethodSignature - "+methodSignature);
        // display method arguments

        // get args
        Object[] args = joinPoint.getArgs();

        for (Object tempArg : args){
            System.out.println(tempArg);
            if (tempArg instanceof Account) {

                // downcast and print Account specific stuff
                Account theAccount = (Account) tempArg;

                System.out.println("account name: " + theAccount.getName());
                System.out.println("account level: " + theAccount.getLevel());
            }
        }


    }

    @Before("forDaoPackage()")
    public void performApiAnalytics() {
        System.out.println("\n=====>>> Performing API analytics");
    }
    // add a new advice for @AfterReturning on the findAccounts method

    @AfterReturning(
            pointcut = "execution(* com.code.apodemo.Dao.AccountDao.findAccounts(..))",
            returning = "result")//result is parameter name for return value
    public void afterReturningFindAccountsAdvice(JoinPoint theJoinPoint, List<Account> result) {

        // print out which method we are advising on
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n=====>>> Executing @AfterReturning on method: " + method);

        // print out the results of the method call
        System.out.println("\n=====>>> result is: " + result);

        //post-process the data , lets modify it
        //convert the account name to uppercase
        convertAccountNameToUpperCase(result);
        System.out.println("\n=====>>> result is: " + result);

    }

    private void convertAccountNameToUpperCase(List<Account> result) {
        // loop through accounts

        for (Account tempAccount : result) {

            // get uppercase version of name
            String theUpperName = tempAccount.getName().toUpperCase();

            // update the name on the account
            tempAccount.setName(theUpperName);
        }

    }
    @AfterThrowing(
            pointcut = "execution(* com.code.apodemo.Dao.AccountDao.findAccounts(..))",
            throwing = "theExc")
    public void afterThrowingFindAccountsAdvice(
            JoinPoint theJoinPoint, Throwable theExc) {

        // print out which method we are advising on
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n=====>>> Executing @AfterThrowing on method: " + method);

        // log the exception
        System.out.println("\n=====>>> The exception is: " + theExc);
    }

    @After("execution(* com.code.apodemo.Dao.AccountDao.findAccounts(..))")
    public void afterFinallyFindAccountsAdvice(JoinPoint theJoinPoint) {

        // print out which method we are advising on
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n=====>>> Executing @After (finally) on method: " + method);
    }
   /* @Around("execution(* com.code.apodemo.service.*.getFortune(..))")
    public Object aroundGetFortune(
            ProceedingJoinPoint theProceedingJoinPoint) throws Throwable {

        // print out method we are advising on
        String method = theProceedingJoinPoint.getSignature().toShortString();
        System.out.println("\n=====>>> Executing @Around on method: " + method);

        // get begin timestamp
        long begin = System.currentTimeMillis();

        // now, let's execute the method
        Object result = theProceedingJoinPoint.proceed();

        // get end timestamp
        long end = System.currentTimeMillis();

        // compute duration and display it
        long duration = end - begin;
        System.out.println("\n=====> Duration: " + duration / 1000.0 + " seconds");

        return result;
    }*/

    @Around("execution(* com.code.apodemo.service.*.getFortune(..))")
    public Object aroundGetFortune(
            ProceedingJoinPoint theProceedingJoinPoint) throws Throwable {

        // print out method we are advising on
        String method = theProceedingJoinPoint.getSignature().toShortString();
        System.out.println("\n=====>>> Executing @Around on method: " + method);

        // get begin timestamp
        long begin = System.currentTimeMillis();

        // now, let's execute the method
        Object result = null;

        try {
            result = theProceedingJoinPoint.proceed();
        }
        catch (Exception exc) {
            // log the exception
            System.out.println(exc.getMessage());

            // give user a custom message , exception is never thrown in a main app
            result = "Major accident! But no worries, your private AOP helicopter is on the way!";
        }

        // get end timestamp
        long end = System.currentTimeMillis();

        // compute duration and display it
        long duration = end - begin;
        System.out.println("\n=====> Duration: " + duration / 1000.0 + " seconds");

        return result;
    }



}
