package com.mayikt.session;

import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;

/**
 * @ClassName SessionInitializer
 * @Description: 初始化session配置
 * @Author liupantao
 * @Date 2019/12/11
 * @Version V1.0
 **/
public class SessionInitializer extends AbstractHttpSessionApplicationInitializer {

    public SessionInitializer() {
        super(SessionConfig.class);
    }
}
