package com.customization.wwq;

import java.io.ByteArrayInputStream;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.alibaba.fastjson.JSON;
import com.cloudstore.dev.api.util.Util_TableMap;
import com.engine.core.cfg.annotation.CommandDynamicProxy;
import com.engine.core.interceptor.AbstractCommandProxy;
import com.engine.core.interceptor.Command;
import com.wwq.cmd.GetDemoHrmListWeaTableCmd;

import weaver.upgradetool.wscheck.Util;

@CommandDynamicProxy(target = GetDemoHrmListWeaTableCmd.class, desc = "测试拦截")
public class GetDemoHrmListWeaTableCmdProxy extends AbstractCommandProxy<Map<String, Object>> {
	
	Log log = LogFactory.getLog(this.getClass());

	@Override
	public Map<String, Object> execute(Command<Map<String, Object>> targetCommand) {
		log.info("========Aop-cmd-proxy=========");
		GetDemoHrmListWeaTableCmd cmd = (GetDemoHrmListWeaTableCmd)targetCommand;
		log.info("当前拦截的类：" + cmd.getClass().getName());
		log.info("当前传入的参数：" + JSON.toJSONString(cmd.getParams()));
		
		Map<String, Object> result = this.nextExecute(targetCommand);
		log.info("返回值：" + JSON.toJSONString(result));
		
		String tableId = Util.null2String(result.get("datas"));
		String weaverTableString = Util_TableMap.getVal(tableId);
		log.info("缓存内容：" + weaverTableString);
		
		try(ByteArrayInputStream btyeTableString = new ByteArrayInputStream(weaverTableString.getBytes("UTF-8"))) {
			SAXReader reader = new SAXReader();
			Document doc = reader.read(btyeTableString);
			Element table = doc.getRootElement();
			
			Element sql = table.element("sql");
			Attribute backfields = sql.attribute("backfields");
			backfields.setValue(backfields.getValue() + ",A.loginId");
			
			Attribute sqlwhere = sql.attribute("sqlwhere");
			String whereSql = "";
			if(cmd.getParams() != null) {
				String loginId = Util.null2String(cmd.getParams().get("loginId"));
				if(!"".equals(loginId)) {
					whereSql += " and A.loginId like '%" + loginId + "%'";
				}
			}
			sqlwhere.setValue(sqlwhere.getValue() + whereSql);
			
			Element head = table.element("head");
			Element col = head.addElement("col");
			col.addAttribute("hide", "false");
			col.addAttribute("width", "7%");
			col.addAttribute("align", "left");
			col.addAttribute("dataalign", "left");
			col.addAttribute("text", "登录名");
			col.addAttribute("column", "loginId");
			col.addAttribute("orderkey", "loginId");
			col.addAttribute("display", "true");
			col.addAttribute("showType", "0");
			col.addAttribute("isInputCol", "false");
			col.addAttribute("isPrimarykey", "false");
			Util_TableMap.setVal(tableId, doc.asXML());
			log.info("新缓存内容：" + doc.asXML());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

}
