package com.mayike.weixin.service;

import com.mayike.weixin.entity.AppEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * @author  liupantao
 */
@Api("微信服务接口")
public interface AppService {


   /**
    * @Description: &#x83b7;&#x53d6;app
    * @param name
    * @return String
    * TODO: 2019/11/12
    */
   @ApiOperation("微信接口")
   @GetMapping("/getAppInfo")
   public AppEntity getAppInfo(@RequestParam String name);



}
