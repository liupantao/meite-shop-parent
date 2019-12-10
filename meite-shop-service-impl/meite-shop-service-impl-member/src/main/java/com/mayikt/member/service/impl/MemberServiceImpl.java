package com.mayikt.member.service.impl;

import com.mayike.weixin.entity.AppEntity;
import com.mayikt.core.base.BaseApiService;
import com.mayikt.core.base.BaseResponse;
import com.mayikt.core.bean.MeiteBeanUtils;
import com.mayikt.core.constants.Constants;
import com.mayikt.core.token.GenerateToken;
import com.mayikt.core.type.TypeCastHelper;
import com.mayikt.member.feign.WeixinAppServiceFeign;
import com.mayikt.member.input.dto.UserInpDTO;
import com.mayikt.member.mapper.UserMapper;
import com.mayikt.member.mapper.entity.UserDo;
import com.mayikt.member.output.dto.UserOutDTO;
import com.mayikt.member.service.MemberService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName MemberServiceImpl
 * @Description: TODO
 * @Author liupantao
 * @Date 2019/11/12
 * @Version V1.0
 **/
@RestController
public class MemberServiceImpl extends BaseApiService<UserOutDTO> implements MemberService {



    @Autowired
    private WeixinAppServiceFeign weixinAppServiceFeign;


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private GenerateToken generateToken;



    @Override
    public AppEntity MemberInvokeWeixin(String userName) {
        System.out.println("-----------开始执行会员调用微信");
        AppEntity appInfo = weixinAppServiceFeign.getAppInfo(userName);
        System.out.println("-----------结束会员调用微信");
        return appInfo;
    }

    /**
     * 根据手机号码查询是否已经存在,如果存在返回当前用户信息
     *
     * @param mobile
     * @return
     */
    @Override
    public BaseResponse<UserOutDTO> existMobile(String mobile) {
        // 1.验证参数
        if (StringUtils.isEmpty(mobile)) {
            return setResultError("手机号码不能为空!");
        }
        UserDo userDo = userMapper.existMobile(mobile);
        if (userDo == null) {
            return setResultError(Constants.HTTP_RES_CODE_EXISTMOBILE_203, "用户不存在");
        }
        // 注意需要将敏感数据进行脱敏
        userDo.setPassword(null);
        // 3.将do转换成dto
        return setResultSuccess(MeiteBeanUtils.doToDto(userDo, UserOutDTO.class));
    }

    /**
     * 根据token查询用户信息
     *
     * @param token
     * @return
     */
    @Override
    public BaseResponse<UserOutDTO> getInfo(String token) {
        // 1.验证token参数
        if (StringUtils.isEmpty(token)) {
            return setResultError("token不能为空!");
        }
        // 2.使用token查询redis 中的userId
        String redisUserId = generateToken.getToken(token);
        if (StringUtils.isEmpty(redisUserId)) {
            return setResultError("token已经失效或者token错误!");
        }
        // 3.使用userID查询 数据库用户信息
        Long userId = TypeCastHelper.toLong(redisUserId);
        UserDo userDo = userMapper.findByUserId(userId);
        if (userDo == null) {
            return setResultError("用户不存在!");
        }
        // 下节课将 转换代码放入在BaseApiService
        return setResultSuccess(MeiteBeanUtils.doToDto(userDo, UserOutDTO.class));
    }
}
