package constant;

import java.lang.annotation.*;

/**
 * Created by Knight_JXNU on 2016/10/9.
 * 自定义注解 拦截方法名称描述
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AdviceLog {
    String description() default "";
}
