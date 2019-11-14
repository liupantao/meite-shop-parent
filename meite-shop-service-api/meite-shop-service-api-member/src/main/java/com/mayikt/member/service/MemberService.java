package com.mayikt.member.service;


import com.mayike.weixin.entity.AppEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 */
@Api(tags = "会员服务接口")
public interface MemberService {


    @ApiOperation("会员调用微信接口")
    @GetMapping("/memberInvokeWeixin")
    public AppEntity MemberInvokeWeixin(@RequestParam String userName);
}
