package admin.anno;/**
 * @Author 郑树凯
 * @Date 2020-10-08 15-34
 * @version 1.0
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 *@Author 郑树凯
 *@Date 2020-10-08 15-34  
 *@version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
public @interface UserLoginToken {
    boolean require() default false;
}
