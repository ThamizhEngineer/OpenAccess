package com.ss.oa.transaction.vo;

public class LogService {

	private String id;
	private String processType,processName;
	private String activityName;
	private String message;
	private String result;
	private String createdBy,createdDt;
	private String att1,att2,att3;
	private String createdDate;
	private String enabled;
	public LogService() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "LogService [id=" + id + ", processType=" + processType + ", processName=" + processName
				+ ", activityName=" + activityName + ", message=" + message + ", result=" + result + ", createdBy="
				+ createdBy + ", createdDt=" + createdDt + ", att1=" + att1 + ", att2=" + att2 + ", att3=" + att3
				+ ", createdDate=" + createdDate + ", enabled=" + enabled + "]";
	}
	public LogService(String id, String processType, String processName, String activityName, String message,
			String result, String createdBy, String createdDt, String att1, String att2, String att3,
			String createdDate, String enabled) {
		super();
		this.id = id;
		this.processType = processType;
		this.processName = processName;
		this.activityName = activityName;
		this.message = message;
		this.result = result;
		this.createdBy = createdBy;
		this.createdDt = createdDt;
		this.att1 = att1;
		this.att2 = att2;
		this.att3 = att3;
		this.createdDate = createdDate;
		this.enabled = enabled;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProcessType() {
		return processType;
	}
	public void setProcessType(String processType) {
		this.processType = processType;
	}
	public String getProcessName() {
		return processName;
	}
	public void setProcessName(String processName) {
		this.processName = processName;
	}
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getCreatedDt() {
		return createdDt;
	}
	public void setCreatedDt(String createdDt) {
		this.createdDt = createdDt;
	}
	public String getAtt1() {
		return att1;
	}
	public void setAtt1(String att1) {
		this.att1 = att1;
	}
	public String getAtt2() {
		return att2;
	}
	public void setAtt2(String att2) {
		this.att2 = att2;
	}
	public String getAtt3() {
		return att3;
	}
	public void setAtt3(String att3) {
		this.att3 = att3;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	
}
