package usr.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2 //开启swagger2
public class swaggerconfig {
    @Bean
//    配置swagger的docket的bean实例
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(true)
                .groupName("树凯")
                .select()
//                RequestHandlerSelectors 配置扫描的接口的方式
//                basePackage扫描指定的包
//                any 扫描全部
//                none全不扫描
//                withMethodAnnotation 扫描方法注解  withMethodAnnotation(RestController.class)
//                withClassAnnotation  扫描类的注解
                .apis(RequestHandlerSelectors.basePackage("usr.controller"))
//                过滤路径
//                .paths(PathSelectors.ant("/kai/**"))
                .build();
    }

    //    配置swagger信息=apiInfo
    public ApiInfo apiInfo() {
        return new ApiInfo(
                "树凯",
                "用户",
                "1.0",
                "https://github.com/", new Contact("郑树凯", "https://github.com/", "583378942@qq.com"),
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
    }
}
