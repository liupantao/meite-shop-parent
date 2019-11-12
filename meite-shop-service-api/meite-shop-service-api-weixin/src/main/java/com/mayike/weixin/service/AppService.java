package com.mayike.weixin.service;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * @author  liupantao
 */
public interface AppService {


   /**
    * @Description: &#x83b7;&#x53d6;app
    * @param name
    * @return String
    * TODO: 2019/11/12
    */
   @RequestMapping(value="/user/name", method= RequestMethod.GET)
   public String getAppInfo();



}
