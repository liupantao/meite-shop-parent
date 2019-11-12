package com.mayikt.member;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @ClassName AppMember
 * @Description: TODO
 * @Author liupantao
 * @Date 2019/11/12
 * @Version V1.0
 **/
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class AppMember {
     public static  void main(String[] args){
         SpringApplication.run(AppMember.class,args);
     }
}
