package com.ss.oa.transaction.vo;

public class ApplicationStatus {
	  private String id  ;
	  private String gcId;
	  private String gcStatusTypeCode  ;
	  private String gcStatusTypeName  ;
	  private String gcStatusUpdateDate  ;
	  private String gcStatusUpdateBy  ;
	  private String gcStatusRemarks  ;
	  private String createdBy;
	  private String createdDate;
	  private String modifiedBy;
	  private String modifiedDate;
	  private String enabled;
	  
		public ApplicationStatus() {
			super();
		}

		public ApplicationStatus(String id, String gcId, String gcStatusTypeCode, String gcStatusTypeName,
				String gcStatusUpdateDate, String gcStatusUpdateBy, String gcStatusRemarks, String createdBy,
				String createdDate, String modifiedBy, String modifiedDate, String enabled) {
			super();
			this.id = id;
			this.gcId = gcId;
			this.gcStatusTypeCode = gcStatusTypeCode;
			this.gcStatusTypeName = gcStatusTypeName;
			this.gcStatusUpdateDate = gcStatusUpdateDate;
			this.gcStatusUpdateBy = gcStatusUpdateBy;
			this.gcStatusRemarks = gcStatusRemarks;
			this.createdBy = createdBy;
			this.createdDate = createdDate;
			this.modifiedBy = modifiedBy;
			this.modifiedDate = modifiedDate;
			this.enabled = enabled;
		}

		@Override
		public String toString() {
			return "ApplicationStatus [id=" + id + ", gcId=" + gcId + ", gcStatusTypeCode=" + gcStatusTypeCode
					+ ", gcStatusTypeName=" + gcStatusTypeName + ", gcStatusUpdateDate=" + gcStatusUpdateDate
					+ ", gcStatusUpdateBy=" + gcStatusUpdateBy + ", gcStatusRemarks=" + gcStatusRemarks + ", createdBy="
					+ createdBy + ", createdDate=" + createdDate + ", modifiedBy=" + modifiedBy + ", modifiedDate="
					+ modifiedDate + ", enabled=" + enabled + "]";
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getGcId() {
			return gcId;
		}

		public void setGcId(String gcId) {
			this.gcId = gcId;
		}

		public String getGcStatusTypeCode() {
			return gcStatusTypeCode;
		}

		public void setGcStatusTypeCode(String gcStatusTypeCode) {
			this.gcStatusTypeCode = gcStatusTypeCode;
		}

		public String getGcStatusTypeName() {
			return gcStatusTypeName;
		}

		public void setGcStatusTypeName(String gcStatusTypeName) {
			this.gcStatusTypeName = gcStatusTypeName;
		}

		public String getGcStatusUpdateDate() {
			return gcStatusUpdateDate;
		}

		public void setGcStatusUpdateDate(String gcStatusUpdateDate) {
			this.gcStatusUpdateDate = gcStatusUpdateDate;
		}

		public String getGcStatusUpdateBy() {
			return gcStatusUpdateBy;
		}

		public void setGcStatusUpdateBy(String gcStatusUpdateBy) {
			this.gcStatusUpdateBy = gcStatusUpdateBy;
		}

		public String getGcStatusRemarks() {
			return gcStatusRemarks;
		}

		public void setGcStatusRemarks(String gcStatusRemarks) {
			this.gcStatusRemarks = gcStatusRemarks;
		}

		public String getCreatedBy() {
			return createdBy;
		}

		public void setCreatedBy(String createdBy) {
			this.createdBy = createdBy;
		}

		public String getCreatedDate() {
			return createdDate;
		}

		public void setCreatedDate(String createdDate) {
			this.createdDate = createdDate;
		}

		public String getModifiedBy() {
			return modifiedBy;
		}

		public void setModifiedBy(String modifiedBy) {
			this.modifiedBy = modifiedBy;
		}

		public String getModifiedDate() {
			return modifiedDate;
		}

		public void setModifiedDate(String modifiedDate) {
			this.modifiedDate = modifiedDate;
		}

		public String getEnabled() {
			return enabled;
		}

		public void setEnabled(String enabled) {
			this.enabled = enabled;
		}

		
}
