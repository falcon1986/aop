package com.wwq.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.alibaba.fastjson.JSON;
import com.engine.common.util.ParamUtil;
import com.engine.common.util.ServiceUtil;
import com.wwq.service.DemoHrmService;
import com.wwq.service.impl.DemoHrmServiceImpl;

import weaver.hrm.HrmUserVarify;
import weaver.hrm.User;

public class DemoHrmAction {

	private DemoHrmService getService(User user){
		return (DemoHrmService)ServiceUtil.getService(DemoHrmServiceImpl.class, user);
	}
	
	private User getUser(HttpServletRequest request, HttpServletResponse response) {
		return HrmUserVarify.getUser(request, response);
	}

	@GET
	@Path("/getListWeaTableDatas")
	@Produces(MediaType.TEXT_PLAIN)
	public String getListWeaTableDatas(@Context HttpServletRequest request, @Context HttpServletResponse response) {
		//参数转换
		Map<String, Object> params = ParamUtil.request2Map(request);
		
		//执行
		try{
			Map<String, Object> res = getService(getUser(request, response)).getListWeaTableData(params);
			return JSON.toJSONString(res);
		} catch(Exception e) {
			return JSON.toJSONString(e);
		}
	}
	
	@GET
	@Path("/getListSearchCondition")
	@Produces(MediaType.TEXT_PLAIN)
	public String getListSearchCondition(@Context HttpServletRequest request, @Context HttpServletResponse response) {
		//参数转换
		Map<String, Object> params = ParamUtil.request2Map(request);
		
		//执行
		try{
			Map<String, Object> res = getService(getUser(request, response)).getListSearchCondition(params);
			return JSON.toJSONString(res);
		} catch(Exception e) {
			return JSON.toJSONString(e);
		}
	}
}
