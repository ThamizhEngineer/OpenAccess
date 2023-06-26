package com.ss.oa.report.vo;

public class FlowType {

	private String flowTypeName;
	private String FlowTypeCount;
	
	public FlowType(){
		super();
	}

	public FlowType(String flowTypeName, String flowTypeCount) {
		super();
		this.flowTypeName = flowTypeName;
		FlowTypeCount = flowTypeCount;
	}

	@Override
	public String toString() {
		return "flowType [flowTypeName=" + flowTypeName + ", FlowTypeCount=" + FlowTypeCount + "]";
	}

	public String getFlowTypeName() {
		return flowTypeName;
	}

	public void setFlowTypeName(String flowTypeName) {
		this.flowTypeName = flowTypeName;
	}

	public String getFlowTypeCount() {
		return FlowTypeCount;
	}

	public void setFlowTypeCount(String flowTypeCount) {
		FlowTypeCount = flowTypeCount;
	}
	
}
