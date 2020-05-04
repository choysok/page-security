package com.page.web.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeAspect {
    // 第一个星号代表  任何的返回值   第二个星号代表 任何方法   括号里的点点代表 任何参数
    @Around("execution(* com.page.web.controller.UserController.*(..))")

    public Object handleControllerMethod(ProceedingJoinPoint joinPoint) throws Throwable {


        System.out.println("切片Aspect---开启");


        Object[] args = joinPoint.getArgs();
        for (Object arg:args){
            System.out.println("切片---方法的参数："+arg);
        }

        Object proceed = joinPoint.proceed();
        System.out.println("切片---方法的返回值："+proceed);

        System.out.println("切片Aspect---结束");

        return proceed;
    }

}
