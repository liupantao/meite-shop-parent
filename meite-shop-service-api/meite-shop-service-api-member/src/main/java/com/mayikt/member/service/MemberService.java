package com.mayikt.member.service;


import com.mayike.weixin.entity.AppEntity;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 */
public interface MemberService {

    public AppEntity MemberInvokeWeixin();
}
