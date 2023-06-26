package com.ss.oa.report.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dashboard {
	private Map<String,Object> dataSets;
	

	public Dashboard() {
		super();
		dataSets = new HashMap<>();
	}

	public Map<String, Object> getDataSets() {
		return dataSets;
	}

	public void setDataSets(Map<String, Object> dataSets) {
		this.dataSets = dataSets;
	}

	@Override
	public String toString() {
		return "Dashboard [dataSets=" + dataSets + "]";
	}
	

}
