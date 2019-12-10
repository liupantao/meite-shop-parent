package com.mayikt.weixin.mp.handler;

import com.mayikt.core.base.BaseResponse;
import com.mayikt.core.constants.Constants;
import com.mayikt.core.utils.RedisUtils;
import com.mayikt.core.utils.RegexUtils;
import com.mayikt.member.output.dto.UserOutDTO;
import com.mayikt.weixin.feign.MemberServiceFeign;
import me.chanjar.weixin.common.api.WxConsts.XmlMsgType;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.mayikt.weixin.mp.builder.TextBuilder;

import java.util.Map;

/**
 * @author Binary Wang(https://github.com/binarywang)
 */
@Component
public class MsgHandler extends AbstractHandler {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());


	/**
	 * 发送验证码消息
	 */
	@Value("${mayikt.weixin.registration.code.message}")
	private String registrationCodeMessage;

	/**
	 * 默认回复消息
	 */
	@Value("${mayikt.weixin.default.registration.code.message}")
	private String defaultRegistrationCodeMessage;


	@Autowired
	private RedisUtils redisUtils;

	@Autowired
	private MemberServiceFeign memberServiceFeign;

	@Override
	public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
									Map<String, Object> context, WxMpService weixinService,
									WxSessionManager sessionManager) {

		if (!wxMessage.getMsgType().equals(XmlMsgType.EVENT)) {
			//TODO 可以选择将消息保存到本地
		}

		//当用户输入关键词如“你好”，“客服”等，并且有客服在线时，把消息转发给在线客服
		try {
			if (StringUtils.startsWithAny(wxMessage.getContent(), "你好", "客服")
					&& weixinService.getKefuService().kfOnlineList()
					.getKfOnlineList().size() > 0) {
				return WxMpXmlOutMessage.TRANSFER_CUSTOMER_SERVICE()
						.fromUser(wxMessage.getToUser())
						.toUser(wxMessage.getFromUser()).build();
			}

			//自定义逻辑
			// 1. 获取微信客户端发送的消息
			String fromContent = wxMessage.getContent();
			// 2.使用正则表达式验证消息是否为手机号码格式
			if (RegexUtils.checkMobile(fromContent)) {
				// 1.根据手机号码调用会员服务接口查询用户信息是否存在  //feign客户端调用接口 默认post方式
				BaseResponse<UserOutDTO> reusltUserInfo = memberServiceFeign.existMobile(fromContent);
				if (reusltUserInfo.getCode().equals(Constants.HTTP_RES_CODE_200)) {
					return new TextBuilder().build("该手机号码" + fromContent + "已经存在!", wxMessage, weixinService);
				}
				if (!reusltUserInfo.getCode().equals(Constants.HTTP_RES_CODE_EXISTMOBILE_203)) {
					return new TextBuilder().build(reusltUserInfo.getMsg(), wxMessage, weixinService);
				}
				// 3.如果是手机号码格式的话,随机生产4位数字注册码
				int registCode = registCode();
				String content = registrationCodeMessage.format(registrationCodeMessage, registCode);
				// 将注册码存入在redis中 key为手机号码
				redisUtils.setString(Constants.WEIXINCODE_KEY + fromContent, registCode + "", Constants.WEIXINCODE_TIMEOUT);
				return new TextBuilder().build(content, wxMessage, weixinService);
			}
			// 否则情况下返回默认消息 调用第三方机器人接口
			return new TextBuilder().build(defaultRegistrationCodeMessage, wxMessage, weixinService);




		} catch (WxErrorException e) {
			e.printStackTrace();
		}


		String content=defaultRegistrationCodeMessage;
		return new TextBuilder().build(content, wxMessage, weixinService);

	}


	// 获取注册码
	private int registCode() {
		int registCode = (int) (Math.random() * 9000 + 1000);
		return registCode;
	}

	public static void main(String[] args) {
		double random = Math.random();
		System.out.println(random);
	}


}

