package com.mayikt.weixin.serviceimp;

import com.alibaba.fastjson.JSONObject;
import com.mayikt.core.base.BaseApiService;
import com.mayikt.core.base.BaseResponse;
import com.mayikt.core.constants.Constants;
import com.mayikt.core.utils.RedisUtils;
import com.mayike.weixin.service.VerificaCodeService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName VerificaCodeServiceImpl
 * @Description: TODO
 * @Author liupantao
 * @Date 2019/12/4
 * @Version V1.0
 **/

@RestController
public class VerificaCodeServiceImpl   extends BaseApiService<JSONObject> implements VerificaCodeService {


    @Autowired
    private RedisUtils redisUtil;

    /**
     * 功能说明:根据手机号码验证码token是否正确
     *
     * @param phone
     * @param weixinCode
     * @return
     */
    @Override
    public BaseResponse<JSONObject> verificaWeixinCode(String phone, String weixinCode) {
        if (StringUtils.isEmpty(phone)) {
            return setResultError("手机号码不能为空!");
        }
        if (StringUtils.isEmpty(weixinCode)) {
            return setResultError("注册码不能为空!");
        }
        String code = redisUtil.getString(Constants.WEIXINCODE_KEY + phone);
        if (StringUtils.isEmpty(code)) {
            return setResultError("注册码已经过期,请重新发送验证码");
        }
        if (!code.equals(weixinCode)) {
            return setResultError("注册码不正确");
        }

        return setResultSuccess("注册码验证码正确");
    }
}
