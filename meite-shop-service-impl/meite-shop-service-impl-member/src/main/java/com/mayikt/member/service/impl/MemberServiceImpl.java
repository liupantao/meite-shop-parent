package com.mayikt.member.service.impl;

import com.mayike.weixin.entity.AppEntity;
import com.mayikt.member.feign.WeixinAppServiceFeign;
import com.mayikt.member.service.MemberService;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName MemberServiceImpl
 * @Description: TODO
 * @Author liupantao
 * @Date 2019/11/12
 * @Version V1.0
 **/
@RestController
public class MemberServiceImpl implements MemberService {



    @Autowired
    private WeixinAppServiceFeign weixinAppServiceFeign;
    
    
    @Override
    public AppEntity MemberInvokeWeixin(String userName) {
        System.out.println("-----------开始执行会员调用微信");
        AppEntity appInfo = weixinAppServiceFeign.getAppInfo(userName);
        System.out.println("-----------结束会员调用微信");
        return appInfo;
    }
}
