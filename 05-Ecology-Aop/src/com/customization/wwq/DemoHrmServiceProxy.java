package com.customization.wwq;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSON;
import com.api.browser.bean.SearchConditionGroup;
import com.api.browser.bean.SearchConditionItem;
import com.api.browser.util.ConditionFactory;
import com.api.browser.util.ConditionType;
import com.engine.core.cfg.annotation.ServiceDynamicProxy;
import com.engine.core.cfg.annotation.ServiceMethodDynamicProxy;
import com.engine.core.impl.aop.AbstractServiceProxy;
import com.wwq.service.DemoHrmService;
import com.wwq.service.impl.DemoHrmServiceImpl;

@ServiceDynamicProxy(target = DemoHrmServiceImpl.class, desc = "服务层拦截")
public class DemoHrmServiceProxy extends AbstractServiceProxy implements DemoHrmService {
	
	Log log = LogFactory.getLog(this.getClass());

	@SuppressWarnings("unchecked")
	@Override
	@ServiceMethodDynamicProxy(desc = "方法拦截1")
	public Map<String, Object> getListWeaTableData(Map<String, Object> params) throws Exception {
		log.info("========Aop-service-proxy=========");
		log.info("当前传入的参数：" + JSON.toJSONString(params));
		
		Map<String, Object> result = (Map<String, Object>)this.executeMethod(params);
		
		log.info("返回值：" + JSON.toJSONString(result));
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	@ServiceMethodDynamicProxy(desc = "方法拦截2")
	public Map<String, Object> getListSearchCondition(Map<String, Object> params) throws Exception {
		log.info("========Aop-service-proxy=========");
		log.info("当前传入的参数：" + JSON.toJSONString(params));
		Map<String, Object> result = (Map<String, Object>)this.executeMethod(params);
		log.info("返回值：" + JSON.toJSONString(result));
		
		List<SearchConditionGroup> baseGroup = (List<SearchConditionGroup>) result.get("condition");
		log.info("baseGroup：" + JSON.toJSONString(baseGroup));
		if(baseGroup != null && baseGroup.size() > 0) {
			try {
				List<SearchConditionItem> conditionItems = baseGroup.get(0).getItems();
				
				//性别
				DemoHrmServiceImpl service = (DemoHrmServiceImpl)this.getTarget();
				ConditionFactory conditionFactory = new ConditionFactory(service.getUser());
				SearchConditionItem loginId = conditionFactory.createCondition(ConditionType.INPUT ,15068 ,"loginId");
				loginId.setLabel("登录名");
		        conditionItems.add(loginId);
			} catch(Exception e) {
				log.info("error", e);
			}
	        
	        log.info("返回值修改后：" + JSON.toJSONString(result));
		}
		
		return result;
	}


}