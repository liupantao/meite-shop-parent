package com.mayikt.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @ClassName AppEureka
 * @Description: TODO
 * @Author liupantao
 * @Date 2019/11/7
 * @Version V1.0
 */
@SpringBootApplication
@EnableEurekaServer
public class AppEureka {

    public static void main(String[] args) {
        SpringApplication.run(AppEureka.class, args);
    }

}
