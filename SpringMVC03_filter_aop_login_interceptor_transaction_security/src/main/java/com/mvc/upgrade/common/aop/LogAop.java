package com.mvc.upgrade.common.aop;

import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogAop {
	
	//slf4j: log를 사용할 수 있게 해주는 인터페이스
	//실제로 구현시키는애 Log4j
	//logger의 기능이 더 많다
	//logfilter와  logAOP는 응답하는 위치가 다르다
	//logAop : 서버내부 pointcut에 따라 그 위치에서 실행됨
	//logfilter : 서버 외부에서 걸러주는 애
	//interceptor: 

	//다오의 앞뒤로 출력할려고 함
	public void before(JoinPoint join) {
		Logger logger = LoggerFactory.getLogger(join.getTarget()+"");	//대상 객체
		logger.info("-----------AOP Start-----------");
		
		Object[] args = join.getArgs();									//대상 파라미터
		if(args != null) {
			logger.info("method : " +join.getSignature().getName());	//대상 메소드
			for(int i = 0; i < args.length; i++) {
				logger.info(i+"번째 : " + args[i]);
			}
		}
	}
	
	public void after(JoinPoint join) {
		Logger logger = LoggerFactory.getLogger(join.getTarget()+"");
		logger.info("-----------AOP End-----------");
	}
	
	public void afterThrowing(JoinPoint join) {
		Logger logger = LoggerFactory.getLogger(join.getTarget()+"");
		logger.info("-----------AOP Error Log-----------");
		logger.info("Error : "+join.getArgs());
		logger.info("Error : "+join.toString());
		
	}
}
