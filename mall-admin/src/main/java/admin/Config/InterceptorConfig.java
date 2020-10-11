package admin.Config;

import admin.handle.InterceptorHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @Author: YRB
 * @Description:
 * @Date: Create in 10:38 2020/10/7
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Resource
    private InterceptorHandle interceptorHandle;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptorHandle)
                .addPathPatterns("/**");
    }
}
