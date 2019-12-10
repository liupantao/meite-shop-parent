package com.mayikt.core.base;

import lombok.Data;

/**
 * @ClassName BaseRespose
 * @Description: TODO
 * @Author liupantao
 * @Date 2019/12/4
 * @Version V1.0
 **/

@Data
public class BaseResponse<T> {

    /**
     * 返回码
     */
    private Integer code;
    /**
     * 消息
     */
    private String msg;

    /**
     * 返回
     */
    private T data;
    // 分页

    public BaseResponse() {

    }

    public BaseResponse(Integer code, String msg, T data) {
        super();
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

}
