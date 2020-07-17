package com.customization.wwq;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSON;
import com.weaverboot.frame.ioc.anno.classAnno.WeaIocReplaceComponent;
import com.weaverboot.frame.ioc.anno.methodAnno.WeaReplaceAfter;
import com.weaverboot.frame.ioc.anno.methodAnno.WeaReplaceBefore;
import com.weaverboot.frame.ioc.beans.bean.definition.handler.replace.weaReplaceParam.impl.WeaAfterReplaceParam;
import com.weaverboot.frame.ioc.beans.bean.definition.handler.replace.weaReplaceParam.impl.WeaBeforeReplaceParam;

@WeaIocReplaceComponent
public class DemoHrmActionIntercept {
	
	Log log = LogFactory.getLog(this.getClass());

	@WeaReplaceBefore(value = "/api/wwq/demo/hrm/getListSearchCondition", order = "1")
	public void getListSearchConditionBefore(WeaBeforeReplaceParam weaBeforeReplaceParam) {
		log.info("========Aop-rest-before-proxy-start=========");
		log.info("拦截的rest地址：" + weaBeforeReplaceParam.getApiUrl());
		log.info("拦截的rest请求参数：" + JSON.toJSONString(weaBeforeReplaceParam.getParamMap()));
		
		log.info("========Aop-rest-before-proxy-end=========");
	}
	
	@WeaReplaceAfter(value = "/api/wwq/demo/hrm/getListSearchCondition", order = "1")
	public String getListSearchConditionAfter(WeaAfterReplaceParam weaAfterReplaceParam) {
		log.info("========Aop-rest-after-proxy-start=========");
		log.info("拦截的rest地址：" + weaAfterReplaceParam.getApiUrl());
		log.info("拦截的rest请求参数：" + JSON.toJSONString(weaAfterReplaceParam.getParamMap()));
		log.info("拦截的rest返回报文：" + JSON.toJSONString(weaAfterReplaceParam.getData()));
		
		log.info("========Aop-rest-after-proxy-end=========");
		
		return weaAfterReplaceParam.getData();
	}
}