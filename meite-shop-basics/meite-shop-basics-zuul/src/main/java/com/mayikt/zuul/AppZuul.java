package com.mayikt.zuul;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigChangeListener;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName AppZuul
 * @Description: TODO
 * @Author liupantao
 * @Date 2019/11/14
 * @Version V1.0
 **/

@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
@EnableSwagger2Doc
@EnableApolloConfig
public class AppZuul {

    // 获取ApolloConfig
    @ApolloConfig
    private Config appConfig;


    public static void main(String[] args) {
        SpringApplication.run(AppZuul.class, args);
    }

    // 添加文档来源
    @Component
    @Primary
    class DocumentationConfig implements SwaggerResourcesProvider {
        @Override
        public List<SwaggerResource> get() {
            return resources();
        }
    }


    private List<SwaggerResource> resources() {
        // 从阿波罗平台获取配置文件
        // String swaDocJson =
        // config.getProperty("mayikt.zuul.swagger.document", null);
        String swaggerDocJson = swaggerDocument();
        JSONArray docJsonArray = JSONArray.parseArray(swaggerDocJson);
        List resources = new ArrayList<>();
        // 遍历集合数组
        for (Object object : docJsonArray) {
            JSONObject jsonObject = (JSONObject) object;
            String name = jsonObject.getString("name");
            String location = jsonObject.getString("location");
            String version = jsonObject.getString("version");
            resources.add(swaggerResource(name, location, version));
        }
        return resources;
    }

    /**
     * 获取swaggerDocument配置
     *
     * @return
     */
    private String swaggerDocument() {
        String property = appConfig.getProperty("mayikt.zuul.swagger.document", "");
        return property;
    }

    private SwaggerResource swaggerResource(String name, String location, String version) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion(version);
        return swaggerResource;
    }
}



