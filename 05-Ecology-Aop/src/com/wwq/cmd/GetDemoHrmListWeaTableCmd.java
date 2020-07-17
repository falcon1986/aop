package com.wwq.cmd;

import java.util.HashMap;
import java.util.Map;

import com.cloudstore.eccom.pc.table.WeaTable;
import com.cloudstore.eccom.pc.table.WeaTableColumn;
import com.cloudstore.eccom.result.WeaResultMsg;
import com.engine.common.biz.AbstractCommonCommand;
import com.engine.common.entity.BizLogContext;
import com.engine.core.interceptor.CommandContext;

import weaver.general.PageIdConst;
import weaver.hrm.User;


public class GetDemoHrmListWeaTableCmd extends AbstractCommonCommand<Map<String, Object>> {

	public GetDemoHrmListWeaTableCmd(Map<String, Object> params, User user) {
		this.params = params;
		this.user = user;
	}
	
	@Override
	public Map<String, Object> execute(CommandContext commandContext) {
		Map<String, Object> apiDatas = new HashMap<String, Object>();
		
		try {
            WeaResultMsg result = new WeaResultMsg(false);

            String pageID = "a512eb1e-787d-4b87-bb1e-ee8749ea4e85";
            String pageUid = pageID + "_" + user.getUID();
            String pageSize = PageIdConst.getPageSize(pageID, user.getUID());
            String sqlwhere = " 1=1 ";

            //新建�?个weatable
            WeaTable table = new WeaTable();
            table.setPageUID(pageUid);
            table.setPageID(pageID);
            table.setPagesize(pageSize);

            String fileds = " A.ID ,A.lastname ";
            table.setBackfields(fileds);

            table.setSqlform(" hrmresource A ");
            table.setSqlwhere(sqlwhere);
            table.setSqlorderby("A.id");
            table.setSqlsortway("desc");
            table.setSqlprimarykey("id");
            table.setSqlisdistinct("false");

            table.getColumns().add(new WeaTableColumn("7%", "姓名", "lastname", "lastname"));

            result.putAll(table.makeDataResult());
            
            result.success();
            apiDatas = result.getResultMap();

        } catch (Exception e) {
        	e.printStackTrace();
        }
        return apiDatas;
	}

	@Override
	public BizLogContext getLogContext() {
		return null;
	}
}