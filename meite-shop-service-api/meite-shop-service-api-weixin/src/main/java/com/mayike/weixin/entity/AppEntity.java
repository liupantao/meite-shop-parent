package com.mayike.weixin.entity;

import lombok.Data;

/**
 * @author liupantao
 *
 */
@Data
public class AppEntity {
    
	public String appId;
	
	public String appName;


	public AppEntity(String appId, String appName) {
		this.appId = appId;
		this.appName = appName;
	}
}
