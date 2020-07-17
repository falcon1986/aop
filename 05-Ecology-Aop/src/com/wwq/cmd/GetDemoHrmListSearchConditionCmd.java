package com.wwq.cmd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.api.browser.bean.SearchConditionGroup;
import com.api.browser.bean.SearchConditionItem;
import com.api.browser.util.ConditionFactory;
import com.api.browser.util.ConditionType;
import com.engine.common.biz.AbstractCommonCommand;
import com.engine.common.entity.BizLogContext;
import com.engine.core.interceptor.CommandContext;

import weaver.hrm.User;

public class GetDemoHrmListSearchConditionCmd extends AbstractCommonCommand<Map<String, Object>> {

	public GetDemoHrmListSearchConditionCmd(Map<String, Object> params, User user) {
		this.params = params;
		this.user = user;
	}

	@Override
	public BizLogContext getLogContext() {
		return null;
	}

	@Override
	public Map<String, Object> execute(CommandContext var1) {
		ConditionFactory conditionFactory = new ConditionFactory(user);
		//组信息
		List<SearchConditionGroup> baseGroup = new ArrayList<SearchConditionGroup>();
		//组项目集合
		List<SearchConditionItem> conditionItems = new ArrayList<SearchConditionItem>();
		
		//名称
		SearchConditionItem name = conditionFactory.createCondition(ConditionType.INPUT ,15068 ,"name");
		name.setLabel("名称");
        conditionItems.add(name);
        
        baseGroup.add(new SearchConditionGroup("基本信息" ,true ,conditionItems));
        
        Map<String, Object> apiResult = new HashMap<String, Object>();
        apiResult.put("condition", baseGroup);
        
		return apiResult;
	}
}