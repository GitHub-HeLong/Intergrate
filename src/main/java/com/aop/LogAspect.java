package com.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

@Aspect
// 该注解标示该类为切面类
@Component
// 注入依赖
public class LogAspect {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	// 标注该方法体为后置通知，当目标方法执行成功后执行该方法体,jp:目标方法体的参数,rl:目标方法标记的标记,rvt:目标方法返回的结果
	@AfterReturning(returning = "rvt", pointcut = "within(com..*) && @annotation(rl)")
	public void suc(JoinPoint jp, LogAnnotation rl, Object rvt) {
		log.info("【LogAspect】 rvt={},rl={}", rvt, rl);

		Object[] parames = jp.getArgs();// 目标方法体的参数
		log.info("parames size : {}", parames.length);

		String signature = jp.getSignature().toString();
		String methodName = signature.substring(signature.lastIndexOf(".") + 1,
				signature.indexOf("(")); // 获取目标方法名
		log.info("方法名 ：signature: {}  ;methodName : {}", signature, methodName);

		String whitelog = rl.whitelog();

		log.info("whitelog :{}", whitelog);

		JSONObject jsonRvt = null;
		if (rvt instanceof JSONObject) {
			String strRvt = JSONObject.toJSONString(rvt);
			jsonRvt = JSONObject.parseObject(strRvt);
			log.info("jsonRvt :{}", jsonRvt);
		}

		String userName = (String) parames[0];
		String userId = (String) parames[1];
		log.info("userName : {}", userName);
		log.info("userId : {}", userId);
	}
}