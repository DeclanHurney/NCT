package ie.cit.adf.aspects;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class TracingAspect {
	@Before(value="execution(* ie.cit.adf.domain.dao..*.*(..))")
	public void trace(JoinPoint jp) {
		Log log = LogFactory.getLog(jp.getTarget().getClass());
		String className = jp.getTarget().getClass().getName();	
		String methodName = jp.getSignature().getName();		
		log.debug("Method invoked:"+className +"#"+methodName);
	}
}
