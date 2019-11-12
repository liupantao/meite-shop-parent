package com.mayikt.weixin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @ClassName Appweixin
 * @Description: TODO
 * @Author liupantao
 * @Date 2019/11/12
 * @Version V1.0
 **/
@SpringBootApplication
@EnableEurekaClient
public class Appweixin {
    public static void main(String[] args) {

        SpringApplication.run(Appweixin.class, args);

    }
}
