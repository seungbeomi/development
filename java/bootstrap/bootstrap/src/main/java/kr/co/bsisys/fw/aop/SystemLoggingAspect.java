/**
 * Copyright (c) 2013 BS Information System
 */
package kr.co.bsisys.fw.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

/**
 * 시스템 로깅 Aspect
 * 
 * @since 2013. 3. 10.
 * @author BS정보시스템/손승범
 */
@Aspect
public class SystemLoggingAspect {
  
  private static final Logger LOGGER = LoggerFactory.getLogger(SystemLoggingAspect.class);
  
  /**
   * Controller 로그출력
   * 
   * @param joinPoint
   * @return
   * @throws Throwable
   */
  @Around("execution(* *..*Controller.*(..))")
  public Object loggingController(ProceedingJoinPoint joinPoint) throws Throwable {
    StopWatch stopWatch = new StopWatch();
    try {
      final Signature signature = joinPoint.getStaticPart().getSignature();
      String clazzName = joinPoint.getTarget().getClass().getSimpleName();
      String methodName = signature.getName();
      String classDotMethod = clazzName + "." + methodName;
      //log.debug("1. Begin controller : {}", target);
      //log.debug("1. Begin controller : {}", joinPoint.getSignature().toString());
      StringBuilder sb = new StringBuilder();
      int i = 0;
      for (Object arg : joinPoint.getArgs()) {
        if (i > 0) {
          sb.append(", ");
        }
        sb.append(arg.toString());
        i++;
      }
      String target = classDotMethod + "(" + sb.toString() + ")";
      LOGGER.debug("1. Begin controller : {}", target);
      //log.debug("1. Parmas : {}", sb.toString());
      stopWatch.start();
      Object result = joinPoint.proceed();
      //LOGGER.debug("1. End controller : {}", target);
      return result;
    } catch (Throwable e) {
      throw e;
    } finally {
      if (stopWatch.isRunning())
        stopWatch.stop();
      //LOGGER.debug("   - Controller Process time : {} seconds", stopWatch.getTotalTimeSeconds());
    }
  }
  
  /**
   * Service 로그 출력
   * 
   * @param joinPoint
   * @return
   * @throws Throwable
   */
  @Around("execution(* *..*Service.*(..))")
  public Object loggingService(ProceedingJoinPoint joinPoint) throws Throwable {
    StopWatch stopWatch = new StopWatch();
    try {
      final Signature signature = joinPoint.getStaticPart().getSignature();
      String clazzName = joinPoint.getTarget().getClass().getSimpleName();
      String methodName = signature.getName();
      String classDotMethod = clazzName + "." + methodName;
      StringBuilder sb = new StringBuilder();
      int i = 0;
      for (Object arg : joinPoint.getArgs()) {
        if (i > 0) {
          sb.append(", ");
        }
        sb.append(arg.toString());
        i++;
      }
      String target = classDotMethod + "(" + sb.toString() + ")";
      LOGGER.debug("2. Begin service : {}", target);
      stopWatch.start();
      Object result = joinPoint.proceed();
      //LOGGER.debug("2. End service : {}", target);
      return result;
    } catch (Throwable e) {
      throw e;
    } finally {
      if (stopWatch.isRunning())
        stopWatch.stop();
      //LOGGER.debug("   - Service Process time : {} seconds", stopWatch.getTotalTimeSeconds());
    }
  }
  
  /**
   * Dao 로그출력
   * 
   * @param joinPoint
   * @return
   * @throws Throwable
   */
  @Around("execution(* *..*Dao.*(..))")
  public Object loggingDao(ProceedingJoinPoint joinPoint) throws Throwable {
    StopWatch stopWatch = new StopWatch();
    String target = "";
    try {
      final Signature signature = joinPoint.getStaticPart().getSignature();
      String clazzName = joinPoint.getTarget().getClass().getSimpleName();
      String methodName = signature.getName();
      String classDotMethod = clazzName + "." + methodName;
      StringBuilder sb = new StringBuilder();
      int i = 0;
      for (Object arg : joinPoint.getArgs()) {
        if (i > 0) {
          sb.append(", ");
        }
        sb.append(arg.toString());
        i++;
      }
      target = classDotMethod + "(" + sb.toString() + ")";
      
      if (!target.startsWith("MapperFactoryBean.")) { // mybatis 로그회피
        LOGGER.debug("3. Begin dao : {}", target);
      }
      
      if (signature instanceof MethodSignature) {
        final MethodSignature ms = (MethodSignature) signature;
        final Class<?>[] parameterTypes = ms.getParameterTypes();
        for (int j = 0; j < parameterTypes.length; j++) {
          Class<?> type = parameterTypes[j];
          if ("java.lang.String".equals(type.getName())) {
            LOGGER.debug("   -> Sql id : {}", joinPoint.getArgs()[j]);
            break;
          }
        }
      }
      stopWatch.start();
      Object result = joinPoint.proceed();
      if (!target.startsWith("MapperFactoryBean.")) {
        LOGGER.debug("3. End dao : {}", target);
      }
      return result;
    } catch (Throwable e) {
      throw e;
    } finally {
      if (stopWatch.isRunning())
        stopWatch.stop();
      if (!target.startsWith("MapperFactoryBean.")) {
        LOGGER.debug("   - Dao Process time : {} seconds", stopWatch.getTotalTimeSeconds());
      }
    }
  }
  
}
