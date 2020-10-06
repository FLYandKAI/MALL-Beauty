package images;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ImgApplication {
    public static void main(String[] args) {
        SpringApplication.run(ImgApplication.class, args);
    }
}
