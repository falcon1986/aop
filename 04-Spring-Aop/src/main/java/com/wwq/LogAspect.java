package com.wwq;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

//切面类
//通知执行顺序：环绕-》前置-》后置返回（后置抛出）-》后置-》环绕
@Component
@Aspect
public class LogAspect {
	
	//切点表达式
	@Pointcut("execution(* com.wwq.*.*(..))")
	public void pointCut() {}
	
	//通知（前置）
	@Before("pointCut()")
	public void before(JoinPoint jp) {
		System.out.println("------proxy-before-----");
		System.out.println("---proxy-执行对象类名：" + jp.getTarget().getClass().getName());
		System.out.println("---proxy-执行方法：" + jp.getSignature().getName());
		System.out.println("---proxy-代理类：" + jp.getThis().getClass().getName());
		Object[] args = jp.getArgs();
		if(args != null) {
			System.out.println("-------proxy-参数如下--------");
			for(Object arg : args) {
				System.out.println("proxy-参数：" + arg);
			}
		}
	}
	
	
	//通知（后置）
	@After("execution(* com.wwq.*.*(..))")
	public void after(JoinPoint jp) {
		System.out.println("------proxy-after-----");
	}
	
	//通知（后置返回）
	@AfterReturning(value = "execution(* com.wwq.*.*(..))", argNames = "retVal", returning = "retVal")
	public void afterReturn(JoinPoint joinPoint, Object retVal) {
		System.out.println("------proxy-afterReturn-----");
	}
	
	//通知（后置异常）
	@AfterThrowing(value = "execution(* com.wwq.*.*(..))")
	public void afterThrowing(JoinPoint joinPoint) {
		System.out.println("------proxy-afterThrowing-----");
	}
	
	//通知（环绕）
	@Around("pointCut()")
	public void around(ProceedingJoinPoint pjp) {
		System.out.println("------proxy-around|start-----");
		try {
			Object res = pjp.proceed();
			System.out.println("---proxy-方法返回值：" + res);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		System.out.println("------proxy-around|end-----");
	}
}