package com.wwq.service;

import java.util.Map;

public interface DemoHrmService {

	Map<String, Object> getListWeaTableData(Map<String, Object> params) throws Exception;
	

	Map<String, Object> getListSearchCondition(Map<String, Object> params) throws Exception;
}
