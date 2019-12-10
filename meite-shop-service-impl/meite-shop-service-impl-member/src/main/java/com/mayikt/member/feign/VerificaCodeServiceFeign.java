package com.mayikt.member.feign;

import com.mayike.weixin.service.VerificaCodeService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @ClassName VerificaCodeServiceFeign
 * @Description: TODO
 * @Author liupantao
 * @Date 2019/12/4
 * @Version V1.0
 **/
@FeignClient("app-mayikt-weixin") //spring-application-name
public interface VerificaCodeServiceFeign extends VerificaCodeService {
}
