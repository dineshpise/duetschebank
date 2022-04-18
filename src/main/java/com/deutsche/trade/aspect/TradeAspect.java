package com.deutsche.trade.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j

public class TradeAspect {
	@Before("execution(*  com.deutsche.trade.*.*.*(..))")
	public void beforeAdvice(JoinPoint joinPoint) {
		log.debug("Before advice : " + joinPoint.getSignature());
	}

	@After("execution(*  com.deutsche.trade.*.*.*(..))")
	public void afterAdvice(JoinPoint joinPoint) {
		log.debug("After advice: " + joinPoint.getSignature());
	}

	@AfterThrowing(pointcut = "execution(*  com.deutsche.trade.*.*.*(..))", throwing = "e")
	public void myAfterThrowing(JoinPoint joinPoint, Exception e) {
		log.error("Exception occured ", e);
	}
}
