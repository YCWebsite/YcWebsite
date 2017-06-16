package com.yc.web.utils;

import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

public class Message {

	public void sendMessage(String s_tel,String s_name) throws Exception{
		TaobaoClient client = new DefaultTaobaoClient("http://gw.api.taobao.com/router/rest", "23704526","a1313f8e56d992b8e6828d83bc9eead0");
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		req.setSmsType("normal");
		req.setSmsFreeSignName("衡阳");
		req.setSmsParamString("{\"name\":\""+s_name+"\"}");
		req.setRecNum(s_tel);
		req.setSmsTemplateCode("SMS_56710094");
		AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
		System.out.println(s_tel+" "+s_name);
		System.out.println(rsp.getBody());
	}
	
}
