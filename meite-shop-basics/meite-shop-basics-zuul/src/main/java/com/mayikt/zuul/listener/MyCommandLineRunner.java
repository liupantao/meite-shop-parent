package com.mayikt.zuul.listener;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigChangeListener;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @ClassName MyCommandLineRunner
 * @Description: 监听器：监听配置文件改动（项目启动时执行一次，增加监听器）
 * @Author liupantao
 * @Date 2019/11/20
 * @Version V1.0
 **/

@Component
@Slf4j
public class MyCommandLineRunner implements CommandLineRunner {


    @ApolloConfig
    private Config config;

    /**
     * Callback used to run the bean.
     *
     * @param args incoming main method arguments
     * @throws Exception on error
     */
    @Override
    public void run(String... args) throws Exception {
        log.info("------》项目启动执行一次");
        config.addChangeListener(new ConfigChangeListener() {

            @Override
            public void onChange(ConfigChangeEvent changeEvent) {
                log.info("####分布式配置中心监听#####" + changeEvent.changedKeys().toString());
            }
        });
    }
}
