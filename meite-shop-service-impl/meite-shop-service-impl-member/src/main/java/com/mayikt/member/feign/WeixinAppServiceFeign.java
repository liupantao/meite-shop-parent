package com.mayikt.member.feign;

import com.mayike.weixin.service.AppService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("app-mayikt-weixin")
public interface WeixinAppServiceFeign extends AppService {
}
