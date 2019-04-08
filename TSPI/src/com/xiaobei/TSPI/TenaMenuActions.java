package com.xiaobei.TSPI;

import java.util.HashMap;
import java.util.Map;

import com.tena.mq.MessageUtil;

import actions.OperationAction;

public class TenaMenuActions extends OperationAction{

	public TenaMenuActions(Object part) {
		super(part);
		// TODO Auto-generated constructor stub
	}
	public void openTspi() {
		System.out.println("打开Tspi");
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", "TSPI");
		map.put("ID", getmId());
		map.put("modeljson", "{\"menuAction\":\"" + "turnUpTSPI\"}");
		MessageUtil.sendToMM(map);
	}
	public void closeTspi() {
		System.out.println("关闭TSPI");
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", "TSPI");
		map.put("ID", getmId());
		map.put("modeljson", "{\"menuAction\":\"" + "turnDownTSPI\"}");
		MessageUtil.sendToMM(map);
	}
	public void logOut() {
		System.out.println("开始记录");
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", "TSPI");
		map.put("ID", getmId());
		map.put("modeljson", "{\"menuAction\":\"" + "turnUpRecord\"}");
		MessageUtil.sendToMM(map);
	}
	public void logIn() {
		System.out.println("停止记录");
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", "TSPI");
		map.put("ID", getmId());
		map.put("modeljson", "{\"menuAction\":\"" + "turnDownRecord\"}");
		MessageUtil.sendToMM(map);
	}
}
