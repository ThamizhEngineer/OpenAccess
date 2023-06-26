package com.ss.oashared.model;



import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
@Table(name="DOC_INFO")
@Component
public class DocInfo{
	@Id
	@Column(name="ID")
	private String id;
	private String docName;
	private String filterCriteria;
	private String docPath;
	private String fileName;
	@Column(name="FILE_NAME_TO_USER")
	private String fileNameToUser;
	private String fileExtension;
	private String tableId;
	private String batchKey;
	private String isEnabled;
	private String createdBy;
	@CreationTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	
	public DocInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DocInfo(String id, String docName, String filterCriteria, String docPath, String fileName,
			String fileNameToUser, String fileExtension, String tableId, String batchKey, String isEnabled,
			String createdBy, LocalDateTime createdDt) {
		super();
		this.id = id;
		this.docName = docName;
		this.filterCriteria = filterCriteria;
		this.docPath = docPath;
		this.fileName = fileName;
		this.fileNameToUser = fileNameToUser;
		this.fileExtension = fileExtension;
		this.tableId = tableId;
		this.batchKey = batchKey;
		this.isEnabled = isEnabled;
		this.createdBy = createdBy;
		this.createdDt = createdDt;
	}

	@Override
	public String toString() {
		return "DocInfo [id=" + id + ", docName=" + docName + ", filterCriteria=" + filterCriteria + ", docPath="
				+ docPath + ", fileName=" + fileName + ", fileNameToUser=" + fileNameToUser + ", fileExtension="
				+ fileExtension + ", tableId=" + tableId + ", batchKey=" + batchKey + ", isEnabled=" + isEnabled
				+ ", createdBy=" + createdBy + ", createdDt=" + createdDt + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	public String getFilterCriteria() {
		return filterCriteria;
	}

	public void setFilterCriteria(String filterCriteria) {
		this.filterCriteria = filterCriteria;
	}

	public String getDocPath() {
		return docPath;
	}

	public void setDocPath(String docPath) {
		this.docPath = docPath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileNameToUser() {
		return fileNameToUser;
	}

	public void setFileNameToUser(String fileNameToUser) {
		this.fileNameToUser = fileNameToUser;
	}

	public String getFileExtension() {
		return fileExtension;
	}

	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}

	public String getTableId() {
		return tableId;
	}

	public void setTableId(String tableId) {
		this.tableId = tableId;
	}

	public String getBatchKey() {
		return batchKey;
	}

	public void setBatchKey(String batchKey) {
		this.batchKey = batchKey;
	}

	public String getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(String isEnabled) {
		this.isEnabled = isEnabled;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getCreatedDt() {
		return createdDt;
	}

	public void setCreatedDt(LocalDateTime createdDt) {
		this.createdDt = createdDt;
	}

		
	
}
