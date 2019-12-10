package com.mayikt.member.service;

import com.alibaba.fastjson.JSONObject;
import com.mayikt.core.base.BaseResponse;
import com.mayikt.member.input.dto.UserInpDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Api(tags = "会员注册接口")
public interface MemberRegisterService {

    /**
     * 用户注册接口
     *
     * @param userInpDTO
     * @param registCode
     * @return
     */
    @PostMapping("/register")
    @ApiOperation(value = "会员用户注册信息接口")
    BaseResponse<JSONObject> register(@RequestBody UserInpDTO userInpDTO,
                                      @RequestParam("registCode") String registCode);
}
