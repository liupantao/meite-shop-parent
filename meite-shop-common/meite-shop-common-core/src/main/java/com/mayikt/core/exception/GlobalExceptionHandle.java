package com.mayikt.core.exception;

import com.alibaba.fastjson.JSONObject;
import com.mayikt.core.base.BaseApiService;
import com.mayikt.core.base.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName GlobalExceptionHandle
 * @Description: TODO
 * @Author liupantao
 * @Date 2019/12/4
 * @Version V1.0
 **/
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandle  extends BaseApiService<JSONObject> {

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public BaseResponse<JSONObject> exceptionHandler(Exception e) {
        log.info("###全局捕获异常###,error:{}", e);
        return setResultError("系统错误!");
    }
}
