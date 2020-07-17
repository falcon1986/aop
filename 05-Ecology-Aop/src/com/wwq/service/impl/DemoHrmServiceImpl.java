package com.wwq.service.impl;

import java.util.Map;

import com.engine.core.impl.Service;
import com.wwq.cmd.GetDemoHrmListSearchConditionCmd;
import com.wwq.cmd.GetDemoHrmListWeaTableCmd;
import com.wwq.service.DemoHrmService;

public class DemoHrmServiceImpl extends Service implements DemoHrmService {

	@Override
	public Map<String, Object> getListWeaTableData(Map<String, Object> params) throws Exception {
		return this.commandExecutor.execute(new GetDemoHrmListWeaTableCmd(params, user));
	}

	@Override
	public Map<String, Object> getListSearchCondition(Map<String, Object> params) throws Exception {
		return this.commandExecutor.execute(new GetDemoHrmListSearchConditionCmd(params, user));
	}

}
