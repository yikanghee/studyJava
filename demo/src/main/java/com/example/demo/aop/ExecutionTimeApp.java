package com.example.demo.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
@Slf4j
public class ExecutionTimeApp {

    // 모든 패키지 내의 controller package에 존재하는 클래스
    @Around("@within(com.example.demo.aop.ExecutionTimeChecker)")
    public Object calculateExecutionTime(ProceedingJoinPoint pjp) throws Throwable {

        StopWatch sw = new StopWatch();
        sw.start();

        // 해당 클래스의 메소드의 실행
        Object result = pjp.proceed();

        // 해당 클래스 처리 후의 시간
        sw.stop();
        long totalTimeMillis = sw.getTotalTimeMillis();

        String className = pjp.getTarget().getClass().getName();
        String methodName = pjp.getSignature().getName();
        String task = className + "." + methodName;

        log.debug("[ExecutionTime] " + task + "-->" + totalTimeMillis + "(ms)");

        return result;
    }

    /**
     *  우선 Aop 클래스로 설정하기 위해 @Aspect 어노테이션을 추가해주었다
     *  Spring Bean으로 등록하기 위해 @Component 어노테이션을 추가해주었다
     *  pjp의 proceed를 통해 실제 핵심 로직을 실행하여 Object 클래스로 메소드의 결과를 받았다
     *  이렇게 하여 모든 api의 실행, 중단 시간을 출력할 수 있다.
     */


}
