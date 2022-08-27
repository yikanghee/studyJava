package com.example.demo.basic.v2;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * Aspect에는 2가지가 필요하다
 * pointcut -> 어디에 적용할 것인가
 * advice -> 해야할 일, 기능
 */

@Component
@Aspect
@EnableAspectJAutoProxy
public class BasicAspect {

    @Around("* basic.PaymentService")
    public Object logPref(ProceedingJoinPoint pjp) throws Throwable {
        long begin = System.currentTimeMillis();
        Object proceed = pjp.proceed();
        System.out.println(System.currentTimeMillis() - begin);
        return proceed;
    }
}
