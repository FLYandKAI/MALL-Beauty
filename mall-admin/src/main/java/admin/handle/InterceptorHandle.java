package admin.handle;

import admin.anno.PassToken;
import admin.anno.UserLoginToken;
import admin.service.AdminService;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import utils.JWTUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.lang.reflect.Method;

/**
 * @Author: 郑树凯
 * @Description:
 * @Date: Create in 10:36 2020/10/7
 */
@Component
public class InterceptorHandle implements HandlerInterceptor {
    public InterceptorHandle() {
        System.out.println("注册拦截器");
    }
    @Autowired
    private AdminService adminService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token =request.getHeader("token");
//        如果不是映射到方法，就放开执行
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        Method method = ((HandlerMethod) handler).getMethod();
//        如果带有passtoken注解，就放开执行
        if (method.isAnnotationPresent(PassToken.class)) {
            if (method.getAnnotation(PassToken.class).require()) {
                return true;
            }
        }
//        检查是否有userLoginToken注解，需要token才能通过
        if(method.isAnnotationPresent(UserLoginToken.class)) {
            if (method.getAnnotation(UserLoginToken.class).require()) {
//                执行token认证
                if (StringUtils.isEmpty(token)) {
                    response.setStatus(HttpStatus.UNAUTHORIZED.value());
                    return false;
                }
                Claims claims = JWTUtil.CheckToken(token);
                System.out.println(claims);
                if (claims== null) {
                    System.out.println("token错误");
                    System.out.println(HttpStatus.FORBIDDEN.value());
                    response.setStatus(HttpStatus.FORBIDDEN.value());
                    return false;
                } else if (adminService.getById((Serializable) claims.get("usrid")) == null) {
                    response.setStatus(HttpStatus.FORBIDDEN.value());
                    System.out.println("查无此人");
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
