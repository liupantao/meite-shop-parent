package com.mayikt.weixin.serviceimp;

import com.mayike.weixin.entity.AppEntity;
import org.springframework.stereotype.Service;

import com.mayike.weixin.service.AppService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/***
 * @author liupantao
 */
@RestController
public class AppServiceImpl implements AppService {

	@Override
    //@RequestMapping(value="/user/name", method= RequestMethod.GET)
	public String getAppInfo() {
		// TODO Auto-generated method stub
		AppEntity appEntity = new AppEntity("123", "APP-01");
		String s = appEntity.toString();
		return s;
	}

}
