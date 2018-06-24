package audit;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.audit4j.core.AuditManager;
import org.audit4j.core.dto.AnnotationAuditEvent;
import org.audit4j.core.exception.Audit4jRuntimeException;


/**
 * The Class AuditAspect.
 * 
 * <pre>
 * {@code
 *  
 *     <aop:aspectj-autoproxy>
 *         ...
 *         <aop:include name="auditAspect"/>
 *     </aop:aspectj-autoproxy>
 *     
 *     <bean id="auditAspect" class="org.audit4j.integration.spring.AuditAspect" />
 * 
 * }
 * </pre>
 * 
 * @author <a href="mailto:janith3000@gmail.com">Janith Bandara</a>
 */
@Aspect
public class AuditAspect {

    /**
     * Audit Aspect.
     * 
     * @param jointPoint
     *            the joint point
     * @throws Throwable
     *             the throwable
     */
    //@Before("@within(org.audit4j.core.annotation.Audit)")// || @annotation(org.audit4j.core.annotation.Audit)")
	@Before("@annotation(org.audit4j.core.annotation.Audit) && execution(@org.audit4j.core.annotation.Audit * *.*(..))")
	public void audit(final JoinPoint joinPoint) throws Throwable {
		//System.out.println(" pointcut:" + joinPoint.getKind());
		//System.out.println("audit start ....");

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();

        if (method.getDeclaringClass().isInterface()) {
            try {
                method = joinPoint.getTarget().getClass()
                        .getDeclaredMethod(joinPoint.getSignature().getName(), method.getParameterTypes());
            } catch (final SecurityException exception) {
                throw new Audit4jRuntimeException(
                        "Exception occured while proceding Audit Aspect in Audit4j Integration", exception);
            } catch (final NoSuchMethodException exception) {
                throw new Audit4jRuntimeException(
                        "Exception occured while proceding Audit Aspect in Audit4j Integration", exception);
            }
        }
        AuditManager.getInstance().audit(joinPoint.getTarget().getClass(), method, joinPoint.getArgs());
    }
	
	  @AfterThrowing(value = "@annotation(org.audit4j.core.annotation.Audit) && execution(* *(..))", throwing = "e")
	  public void auditError(JoinPoint joinPoint, Throwable e){
	        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
	        Method method = methodSignature.getMethod();

	        if (method.getDeclaringClass().isInterface()) {
	            try {
	                method = joinPoint.getTarget().getClass()
	                        .getDeclaredMethod(joinPoint.getSignature().getName(), method.getParameterTypes());
	            } catch (final SecurityException exception) {
	                throw new Audit4jRuntimeException(
	                        "Exception occured while proceding Audit Aspect in Audit4j Integration", exception);
	            } catch (final NoSuchMethodException exception) {
	                throw new Audit4jRuntimeException(
	                        "Exception occured while proceding Audit Aspect in Audit4j Integration", exception);
	            }
	        }

	        AnnotationAuditEvent a = new AnnotationAuditEvent(joinPoint.getTarget().getClass(),method, joinPoint.getArgs());
	        a.setOrigin("Exception "+e.getMessage());
	        
	        AuditManager.getInstance().audit(a);
	  }
}
