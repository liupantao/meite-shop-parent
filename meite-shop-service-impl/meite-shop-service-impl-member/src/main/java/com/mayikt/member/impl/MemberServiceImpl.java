package com.mayikt.member.impl;

import com.mayike.weixin.entity.AppEntity;
import com.mayikt.member.feign.WeixinAppServiceFeign;
import com.mayikt.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    @GetMapping("/getMember")
    public AppEntity MemberInvokeWeixin() {
        String appInfo = weixinAppServiceFeign.getAppInfo();
        return new AppEntity("233","000");
    }
}
