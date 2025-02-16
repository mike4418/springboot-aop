package com.mike.curso.springboot.app.aop.springboot_aop.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(2)
@Aspect
@Component
public class GreetingAspect {

    private final Logger logger = LoggerFactory.getLogger(GreetingAspect.class);

    @Before("GreetingServicePointcuts.greetingLoggerPointCut()")
    public void loggerBefore(JoinPoint joinPoint){
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString( joinPoint.getArgs());

        logger.info("Antes: " + method + " con los argumentos " + args);
    }

    @After("GreetingServicePointcuts.greetingLoggerPointCut()")
    public void loggerAfter(JoinPoint joinPoint){
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString( joinPoint.getArgs());

        logger.info("Despues: " + method + " con los argumentos " + args);
    }

    @AfterReturning("GreetingServicePointcuts.greetingLoggerPointCut()")
    public void loggerAfterReturning(JoinPoint joinPoint){
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString( joinPoint.getArgs());

        logger.info("Despues de retornar: " + method + " con los argumentos " + args);
    }

    @AfterThrowing("GreetingServicePointcuts.greetingLoggerPointCut()")
    public void loggerAfterThrowing(JoinPoint joinPoint){
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString( joinPoint.getArgs());

        logger.info("Despues de lanzar la excepcion: " + method + " con los argumentos " + args);
    }

    @Around("GreetingServicePointcuts.greetingLoggerPointCut()")
    public Object loggerAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        String method = proceedingJoinPoint.getSignature().getName();
        String args = Arrays.toString( proceedingJoinPoint.getArgs());
        Object result;
        try {

            logger.info("The method: " + method  + "() with the arguments: " + args); 

            result = proceedingJoinPoint.proceed();

            logger.info("The method: " + method  + "() return the result: " + result);

            return result;
        } catch (Throwable ex) {
            logger.error("Error in the method call: " + method + "()");
            throw ex;
        }
        
        
    }

}
