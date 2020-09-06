package cn.ch4u;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "cn.ch4u.*.mapper")
public class MainApplication {
    static final Logger logger = LoggerFactory.getLogger(MainApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

}
