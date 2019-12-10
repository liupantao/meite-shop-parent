package com.mayikt.member.mapper.entity;

import com.mayikt.core.base.BaseDo;
import lombok.Data;

/**
 * @ClassName UserTokenDo
 * @Description: TODO
 * @Author liupantao
 * @Date 2019/12/5
 * @Version V1.0
 **/

@Data
public class UserTokenDo extends BaseDo {
    /**
     * id
     */
    private Long id;
    /**
     * 用户token
     */
    private String token;
    /**
     * 登陆类型
     */
    private String loginType;

    /**
     * 设备信息
     */
    private String deviceInfor;
    /**
     * 用户userId
     */
    private Long userId;

}
