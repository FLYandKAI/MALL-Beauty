package gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import javax.xml.bind.SchemaOutputResolver;

@SpringBootApplication
@EnableEurekaClient
public class MallGateWayApplication {
    public static void main(String[] args) {
        SpringApplication.run(MallGateWayApplication.class, args);
    }
}
