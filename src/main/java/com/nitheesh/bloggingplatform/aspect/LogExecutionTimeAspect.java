package com.nitheesh.bloggingplatform.aspect;

import com.nitheesh.bloggingplatform.annotation.LogExecutionTime;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogExecutionTimeAspect {

    @Around("@annotation(LogExecutionTime)")
    public Object logTime(ProceedingJoinPoint joinPoint, LogExecutionTime logExecutionTime) throws Throwable {
        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long end = System.currentTimeMillis();

        System.out.println(joinPoint.getSignature() + " executed in " + (end - start) + " ms");
        return proceed;
    }
}
