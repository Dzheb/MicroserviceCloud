package org.example.gb.timing;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.example.gb.TimingProperties;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class TimingAspect {
    private final TimingProperties properties;
    @Pointcut("within(@org.example.gb.timing.Timer *)")
    public void beansAnnotatedWithTimer() {

    }

    @Pointcut("@annotation(org.example.gb.timing.Timer)")
    public void methodsAnnotatedWithTimer() {

    }

    @Around("beansAnnotatedWithTimer() " +
            "|| methodsAnnotatedWithTimer()")
    public Object aroundMyServiceBeanMethodsPointcut(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        if (joinPoint.getTarget().getClass()
                .isAnnotationPresent(Timer.class) ||
                method.isAnnotationPresent(Timer.class)) {
            long start = System.nanoTime();
            Object proceed = joinPoint.proceed();
            long finish = System.nanoTime();
            log.info("Время выполнения метода {} " +
                    " {} наносекунд", method.getName().toLowerCase(), finish - start);
            return proceed;
        } else {
            Object proceed = joinPoint.proceed();
            return proceed;
        }
    }
}
