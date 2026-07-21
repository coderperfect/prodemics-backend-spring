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
	@Around("execution(* com.mrityunjoy.prodemics.service..*(..))")
	public Object logServices(ProceedingJoinPoint joinPoint) throws Throwable {
		return log(joinPoint);
	}

	@Around("@annotation(com.mrityunjoy.prodemics.annotation.LogAspect)")
	public Object logAnnotation(ProceedingJoinPoint joinPoint) throws Throwable {
		return log(joinPoint);
	}

	private Object log(ProceedingJoinPoint joinPoint) throws Throwable {
		Instant start = Instant.now();

		log.info("{} started", joinPoint.getSignature());

		try {
			return joinPoint.proceed();
		} finally {
			long millis = Duration.between(start, Instant.now()).toMillis();
			log.info("{} took {} ms", joinPoint.getSignature(), millis);
		}
	}
}
