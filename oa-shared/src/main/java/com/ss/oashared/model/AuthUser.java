package com.ss.oashared.model;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Formula;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="AUTH_USER")
@Component
public class AuthUser implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_AUTH_USER_SEQ")
    @SequenceGenerator(name = "id_AUTH_USER_SEQ", sequenceName ="AUTH_USER_SEQ")
	 private String id;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    @Column(name="USER_TYPE_CODE")
    private String userTypeCode;
    private String isSuperUser;
    private String systemKeyCode;
    private String systemRefKey;
    private String edcCode;
    private String companyId;
    private String contactNumber;
    private String emailId;
    private String orgId;
    private String companyServiceId;
    private String token;
    private String duration;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime tokenValidityDt;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime lastLoggedDt;
    @Column(columnDefinition="CHAR")
	private String masterConfirmed;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime confirmedDate;
	@ManyToMany(cascade = CascadeType.ALL)
	   @JoinTable(name = "AUTH_USER_TYPE_MAP", 
	            joinColumns = {@JoinColumn(name = "AUTH_USER_TYPE_CODE",referencedColumnName = "user_type_code") }, 
	            inverseJoinColumns = { @JoinColumn(name = "AUTH_FEATURE_ID" , referencedColumnName = "id")})
	    private Set<AuthUserProfileAccess> accessList = new HashSet<>();
	    
	    
	    @Formula("(SELECT AT.USER_TYPE_NAME FROM AUTH_USER_TYPE AT WHERE AT.ID=USER_TYPE_CODE )")
	private String userTypeName ;
	private String loginStopMessage ;
	private String isLoggedIn;
	@Transient
	private String uniqueId;
	public AuthUser(String isLoggedIn) {
		super();
		this.isLoggedIn = isLoggedIn;
	}
	public AuthUser() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AuthUser(String id, String firstName, String lastName, String userName, String password, String userTypeCode,
			String isSuperUser, String systemKeyCode, String systemRefKey, String edcCode, String companyId,
			String contactNumber, String emailId, String orgId, String companyServiceId, String token, String duration,
			LocalDateTime tokenValidityDt, LocalDateTime lastLoggedDt, String masterConfirmed,
			LocalDateTime confirmedDate, Set<AuthUserProfileAccess> accessList, String userTypeName,String loginStopMessage) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.userTypeCode = userTypeCode;
		this.isSuperUser = isSuperUser;
		this.systemKeyCode = systemKeyCode;
		this.systemRefKey = systemRefKey;
		this.edcCode = edcCode;
		this.companyId = companyId;
		this.contactNumber = contactNumber;
		this.emailId = emailId;
		this.orgId = orgId;
		this.companyServiceId = companyServiceId;
		this.token = token;
		this.duration = duration;
		this.tokenValidityDt = tokenValidityDt;
		this.lastLoggedDt = lastLoggedDt;
		this.masterConfirmed = masterConfirmed;
		this.confirmedDate = confirmedDate;
		this.accessList = accessList;
		this.userTypeName = userTypeName;
		this.loginStopMessage = loginStopMessage;
	}
	@Override
	public String toString() {
		return "AuthUser [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", userName=" + userName
				+ ", password=" + password + ", userTypeCode=" + userTypeCode + ", isSuperUser=" + isSuperUser
				+ ", systemKeyCode=" + systemKeyCode + ", systemRefKey=" + systemRefKey + ", edcCode=" + edcCode
				+ ", companyId=" + companyId + ", contactNumber=" + contactNumber + ", emailId=" + emailId + ", orgId="
				+ orgId + ", companyServiceId=" + companyServiceId + ", token=" + token + ", duration=" + duration
				+ ", tokenValidityDt=" + tokenValidityDt + ", lastLoggedDt=" + lastLoggedDt + ", masterConfirmed="
				+ masterConfirmed + ", confirmedDate=" + confirmedDate + ", accessList=" + accessList
				+ ", userTypeName=" + userTypeName + ", loginStopMessage=" + loginStopMessage + ", isLoggedIn="
				+ isLoggedIn + "]";
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserTypeCode() {
		return userTypeCode;
	}
	public void setUserTypeCode(String userTypeCode) {
		this.userTypeCode = userTypeCode;
	}
	public String getIsSuperUser() {
		return isSuperUser;
	}
	public void setIsSuperUser(String isSuperUser) {
		this.isSuperUser = isSuperUser;
	}
	public String getSystemKeyCode() {
		return systemKeyCode;
	}
	public void setSystemKeyCode(String systemKeyCode) {
		this.systemKeyCode = systemKeyCode;
	}
	public String getSystemRefKey() {
		return systemRefKey;
	}
	public void setSystemRefKey(String systemRefKey) {
		this.systemRefKey = systemRefKey;
	}
	public String getEdcCode() {
		return edcCode;
	}
	public void setEdcCode(String edcCode) {
		this.edcCode = edcCode;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getCompanyServiceId() {
		return companyServiceId;
	}
	public void setCompanyServiceId(String companyServiceId) {
		this.companyServiceId = companyServiceId;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public LocalDateTime getTokenValidityDt() {
		return tokenValidityDt;
	}
	public void setTokenValidityDt(LocalDateTime tokenValidityDt) {
		this.tokenValidityDt = tokenValidityDt;
	}
	public LocalDateTime getLastLoggedDt() {
		return lastLoggedDt;
	}
	public void setLastLoggedDt(LocalDateTime lastLoggedDt) {
		this.lastLoggedDt = lastLoggedDt;
	}
	public String getMasterConfirmed() {
		return masterConfirmed;
	}
	public void setMasterConfirmed(String masterConfirmed) {
		this.masterConfirmed = masterConfirmed;
	}
	public LocalDateTime getConfirmedDate() {
		return confirmedDate;
	}
	public void setConfirmedDate(LocalDateTime confirmedDate) {
		this.confirmedDate = confirmedDate;
	}
	public Set<AuthUserProfileAccess> getAccessList() {
		return accessList;
	}
	public void setAccessList(Set<AuthUserProfileAccess> accessList) {
		this.accessList = accessList;
	}
	public String getUserTypeName() {
		return userTypeName;
	}
	public void setUserTypeName(String userTypeName) {
		this.userTypeName = userTypeName;
	}
	public String getLoginStopMessage() {
		return loginStopMessage;
	}
	public void setLoginStopMessage(String loginStopMessage) {
		this.loginStopMessage = loginStopMessage;
	}

	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getIsLoggedIn() {
		return isLoggedIn;
	}
	public void setIsLoggedIn(String isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}
	public String getUniqueId() {
		return uniqueId;
	}
	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}

}
