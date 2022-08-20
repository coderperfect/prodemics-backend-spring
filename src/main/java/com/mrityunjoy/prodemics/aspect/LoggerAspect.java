package com.mrityunjoy.prodemics.aspect;

import java.time.Duration;
import java.time.Instant;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class LoggerAspect {
	@Around("execution(* com.mrityunjoy.prodemics.service.*.*(..))")
	public Object logExecutionDesignator(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		return log(proceedingJoinPoint);
	}
	
	@Around("@annotation(com.mrityunjoy.prodemics.annotation.LogAspect)")
	public Object logAnnotationDesignator(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		return log(proceedingJoinPoint);
	}
	
	public Object log(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		Instant startInstant = Instant.now();
		log.info(String.format("%s method started", proceedingJoinPoint.getSignature().toString()));
		Object returnObject = proceedingJoinPoint.proceed();
		log.info(String.format("%s method finished", proceedingJoinPoint.getSignature().toString()));
		Instant endInstant = Instant.now();
		long millisTook = Duration.between(startInstant, endInstant).toMillis();
		log.info(String.format("%s method took %d milliseconds", proceedingJoinPoint.getSignature().toString(), millisTook));
		return returnObject;
	}
}
