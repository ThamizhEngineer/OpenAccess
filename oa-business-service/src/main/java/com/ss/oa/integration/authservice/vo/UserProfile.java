package com.ss.oa.integration.authservice.vo;

import java.util.List;

import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@Scope("prototype")
public class UserProfile {
	@JsonProperty("_id") private String id;
	@JsonProperty("firstName") private String firstName;
	@JsonProperty("lastName") private String lastName;
	@JsonProperty("userType") private String userType;
	@JsonProperty("superUser") private String superUser;
	@JsonProperty("userName") private String username;
	@JsonProperty("password") private String password;
	@JsonProperty("systemKey") private String systemKey;
	@JsonProperty("systemRefKey") private String systemRefKey;
	@JsonProperty("contactNumber") private String contactNumber;
	@JsonProperty("emailId") private String emailId;	
	@JsonProperty("edcCode")  private String edcCode;
	@JsonProperty("companyId") private String companyId;
	@JsonProperty("userAccessList") private List<ProfileAccess> profileAccessList;
	
	public UserProfile() {
		super();
	}

	public UserProfile(String id, String firstName, String lastName, String userType, String superUser, String username,
			String password, String systemKey, String systemRefKey, String contactNumber, String emailId,
			String edcCode, String companyId, List<ProfileAccess> profileAccessList) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userType = userType;
		this.superUser = superUser;
		this.username = username;
		this.password = password;
		this.systemKey = systemKey;
		this.systemRefKey = systemRefKey;
		this.contactNumber = contactNumber;
		this.emailId = emailId;
		this.edcCode = edcCode;
		this.companyId = companyId;
		this.profileAccessList = profileAccessList;
	}

	@Override
	public String toString() {
		return "UserProfile [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", userType="
				+ userType + ", superUser=" + superUser + ", username=" + username + ", password=" + password
				+ ", systemKey=" + systemKey + ", systemRefKey=" + systemRefKey + ", contactNumber=" + contactNumber
				+ ", emailId=" + emailId + ", edcCode=" + edcCode + ", companyId=" + companyId + ", profileAccessList="
				+ profileAccessList + "]";
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

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getSuperUser() {
		return superUser;
	}

	public void setSuperUser(String superUser) {
		this.superUser = superUser;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSystemKey() {
		return systemKey;
	}

	public void setSystemKey(String systemKey) {
		this.systemKey = systemKey;
	}

	public String getSystemRefKey() {
		return systemRefKey;
	}

	public void setSystemRefKey(String systemRefKey) {
		this.systemRefKey = systemRefKey;
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

	public List<ProfileAccess> getProfileAccessList() {
		return profileAccessList;
	}

	public void setProfileAccessList(List<ProfileAccess> profileAccessList) {
		this.profileAccessList = profileAccessList;
	}


	 
}
