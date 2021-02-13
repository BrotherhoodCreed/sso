package com.soso.product1;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@MapperScan("com.soso.product1")
@SpringBootApplication(exclude={HibernateJpaAutoConfiguration.class})
public class Springboot1Application {


    public static void main(String[] args) {
        SpringApplication.run(Springboot1Application.class, args);
    }
}
