package com.ss.oa.transaction.vo;

public class DocCheckListItem {

	
	  private String id   ;
	  private String gcId  ;
	  private String documentCode   ;
	  private String docSubmitted   ;
	  private String docUrl   ;
	  private String documentDesc;
	  private String checkListCode;
	  private String checkListDesc;
	  private String completedDate;
	  private String isComplete;
	  private String createdBy;
	  private String createdDate;
	  private String modifiedBy;
	  private String modifiedDate;
	  private String enabled;
	  private String remarks;
	  
		public DocCheckListItem() {
			super();
		}

		public DocCheckListItem(String id, String gcId, String documentCode, String docSubmitted, String docUrl,
				String documentDesc, String checkListCode, String checkListDesc, String completedDate,
				String isComplete, String createdBy, String createdDate, String modifiedBy, String modifiedDate,
				String enabled, String remarks) {
			super();
			this.id = id;
			this.gcId = gcId;
			this.documentCode = documentCode;
			this.docSubmitted = docSubmitted;
			this.docUrl = docUrl;
			this.documentDesc = documentDesc;
			this.checkListCode = checkListCode;
			this.checkListDesc = checkListDesc;
			this.completedDate = completedDate;
			this.isComplete = isComplete;
			this.createdBy = createdBy;
			this.createdDate = createdDate;
			this.modifiedBy = modifiedBy;
			this.modifiedDate = modifiedDate;
			this.enabled = enabled;
			this.remarks = remarks;
		}

		@Override
		public String toString() {
			return "DocCheckListItem [id=" + id + ", gcId=" + gcId + ", documentCode=" + documentCode
					+ ", docSubmitted=" + docSubmitted + ", docUrl=" + docUrl + ", documentDesc=" + documentDesc
					+ ", checkListCode=" + checkListCode + ", checkListDesc=" + checkListDesc + ", completedDate="
					+ completedDate + ", isComplete=" + isComplete + ", createdBy=" + createdBy + ", createdDate="
					+ createdDate + ", modifiedBy=" + modifiedBy + ", modifiedDate=" + modifiedDate + ", enabled="
					+ enabled + ", remarks=" + remarks + "]";
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

		public String getDocumentCode() {
			return documentCode;
		}

		public void setDocumentCode(String documentCode) {
			this.documentCode = documentCode;
		}

		public String getDocSubmitted() {
			return docSubmitted;
		}

		public void setDocSubmitted(String docSubmitted) {
			this.docSubmitted = docSubmitted;
		}

		public String getDocUrl() {
			return docUrl;
		}

		public void setDocUrl(String docUrl) {
			this.docUrl = docUrl;
		}

		public String getDocumentDesc() {
			return documentDesc;
		}

		public void setDocumentDesc(String documentDesc) {
			this.documentDesc = documentDesc;
		}

		public String getCheckListCode() {
			return checkListCode;
		}

		public void setCheckListCode(String checkListCode) {
			this.checkListCode = checkListCode;
		}

		public String getCheckListDesc() {
			return checkListDesc;
		}

		public void setCheckListDesc(String checkListDesc) {
			this.checkListDesc = checkListDesc;
		}

		public String getCompletedDate() {
			return completedDate;
		}

		public void setCompletedDate(String completedDate) {
			this.completedDate = completedDate;
		}

		public String getIsComplete() {
			return isComplete;
		}

		public void setIsComplete(String isComplete) {
			this.isComplete = isComplete;
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

		public String getRemarks() {
			return remarks;
		}

		public void setRemarks(String remarks) {
			this.remarks = remarks;
		}
		
}
