package kh.lclass.jjap.common;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;


@Aspect
@Component
public class AdviceLog {
	private static final Logger logger = LoggerFactory.getLogger(AdviceLog.class);
	
	@Pointcut("execution(public * kh.lclass.jjap..*Dao.*(..))")
	public void daoPointCut() {}
	@Pointcut("execution(public * kh.lclass.jjap..*Service.*(..))")
	public void servicePointCut() {}
	@Pointcut("execution(public * kh.lclass.jjap..*Controller.*(..))")
	public void controllerPointCut() {}
	
	@Around("daoPointCut()")
	public Object aroundDaoLog(ProceedingJoinPoint pjp) throws Throwable {
		logger.debug("["+pjp.getThis()+":"+pjp.getSignature().getName()+"]");

		Object[] args = pjp.getArgs();
		for(int i = 0; i<args.length; i++) {
			logger.debug("-args["+i+"] "+args[i]+"");
		}
		
		Object robj = null;
		StopWatch stopwatch = new StopWatch();
		stopwatch.start();
		robj = pjp.proceed();
		stopwatch.stop();
		
		logger.debug("-[ Dao - "+stopwatch.getTotalTimeMillis()+"초 ]= "+robj);
		return robj;
		
	}
	@Around("servicePointCut()")
	public Object aroundSrvLog(ProceedingJoinPoint pjp) throws Throwable {
		logger.debug("["+pjp.getThis()+":"+pjp.getSignature().getName()+"]");
		Object[] args = pjp.getArgs();
		for(int i = 0; i<args.length; i++) {
			logger.debug("--args["+i+"] "+args[i]+"");
		}
		Object robj = null;
		robj = pjp.proceed();
		logger.debug("--[ Srv-- ]= "+robj);
		return robj;
		
	}
	@Around("controllerPointCut()")
	public Object aroundCtrLog(ProceedingJoinPoint pjp) throws Throwable {
		logger.debug("["+pjp.getThis()+":"+pjp.getSignature().getName()+"]");
		Object[] args = pjp.getArgs();
		for(int i = 0; i<args.length; i++) {
			logger.debug("---args["+i+"] "+args[i]+"");
		}
		Object robj = null;
		robj = pjp.proceed();
		logger.debug("---[ Ctr--- ]= "+robj);
		return robj;
		
	}
	
}
