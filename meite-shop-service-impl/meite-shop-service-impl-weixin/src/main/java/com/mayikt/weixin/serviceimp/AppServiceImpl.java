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
	public AppEntity getAppInfo(String name) {
		System.out.println("----------微信信息接口---");
		AppEntity appEntity = new AppEntity("123", name);
		return appEntity;
	}

}
