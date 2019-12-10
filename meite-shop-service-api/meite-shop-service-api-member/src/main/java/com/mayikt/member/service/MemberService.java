package com.mayikt.member.service;


import com.mayike.weixin.entity.AppEntity;
import com.mayikt.core.base.BaseResponse;
import com.mayikt.member.output.dto.UserOutDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 */
@Api(tags = "会员服务接口")
public interface MemberService {


    @ApiOperation("会员调用微信接口")
    @GetMapping("/memberInvokeWeixin")
    public AppEntity MemberInvokeWeixin(@RequestParam String userName);

    /**
     * 根据手机号码查询是否已经存在,如果存在返回当前用户信息
     *
     * @param mobile
     * @return
     */
    @ApiOperation(value = "根据手机号码查询是否已经存在")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "mobile", dataType = "String", required = true, value = "用户手机号码"), })
    @PostMapping("/existMobile")
    BaseResponse<UserOutDTO> existMobile(@RequestParam("mobile") String mobile);


    /**
     * 根据token查询用户信息
     *
     * @param token
     * @return
     */
    @GetMapping("/getUserInfo")
    @ApiOperation(value = "/getUserInfo")
    BaseResponse<UserOutDTO> getInfo(@RequestParam("token") String token);
}
