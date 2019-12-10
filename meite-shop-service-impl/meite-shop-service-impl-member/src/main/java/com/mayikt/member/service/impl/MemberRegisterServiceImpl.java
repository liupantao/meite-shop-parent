package com.mayikt.member.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.mayikt.core.base.BaseApiService;
import com.mayikt.core.base.BaseResponse;
import com.mayikt.core.bean.MeiteBeanUtils;
import com.mayikt.core.constants.Constants;
import com.mayikt.core.utils.MD5Utils;
import com.mayikt.member.feign.VerificaCodeServiceFeign;
import com.mayikt.member.input.dto.UserInpDTO;
import com.mayikt.member.mapper.UserMapper;
import com.mayikt.member.mapper.entity.UserDo;
import com.mayikt.member.service.MemberRegisterService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName MemberRegisterServiceImpl
 * @Description: TODO
 * @Author liupantao
 * @Date 2019/12/4
 * @Version V1.0
 **/
@RestController
public class MemberRegisterServiceImpl extends BaseApiService<JSONObject> implements MemberRegisterService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private VerificaCodeServiceFeign verificaCodeServiceFeign;



    /**
     * 用户注册接口
     *
     * @param userInpDTO
     * @param registCode
     * @return
     */
    @Override
    public BaseResponse<JSONObject> register(@RequestBody UserInpDTO userInpDTO, @RequestParam("registCode")  String registCode) {

        // 1.验证参数
        String userName = userInpDTO.getUserName();
        if (StringUtils.isEmpty(userName)) {
            return setResultError("用户名称不能为空!");
        }
        String mobile = userInpDTO.getMobile();
        if (StringUtils.isEmpty(mobile)) {
            return setResultError("手机号码不能为空!");
        }
        String password = userInpDTO.getPassword();
        if (StringUtils.isEmpty(password)) {
            return setResultError("密码不能为空!");
        }

        String newPassWord = MD5Utils.MD5(password);
        // 将密码采用MD5加密
        userInpDTO.setPassword(newPassWord);
        // 调用微信接口,验证注册码是否正确
        BaseResponse<JSONObject> resultVerificaWeixinCode = verificaCodeServiceFeign.verificaWeixinCode(mobile,registCode);
        if (!resultVerificaWeixinCode.getCode().equals(Constants.HTTP_RES_CODE_200)) {
            return setResultError(resultVerificaWeixinCode.getMsg());
        }

        // 4.调用数据库插入数据 将请求的dto参数转换DO
        UserDo userDo = MeiteBeanUtils.dtoToDo(userInpDTO, UserDo.class);
        return userMapper.register(userDo) > 0 ? setResultSuccess("注册成功") : setResultError("注册失败!");
    }
}
