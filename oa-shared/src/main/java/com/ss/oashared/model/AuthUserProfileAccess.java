package com.ss.oashared.model;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
@Entity
@Table(name="AUTH_FEATURE")
public class AuthUserProfileAccess {
	 @Id
	    private String id;
	    @Column(name="functionality_code")
	    private String functionality;
	    @Column(name="feature_Code")
	    private String feature;
	    private String description;
	    private String systemKeyCode;
	    @Column(name="USER_TYPE_CODE")
	    private String userTypeCode;
	    private String orgTypeCode;
	    @ManyToMany(mappedBy = "accessList")
	    private Set<AuthUser> authUsers = new HashSet<>();
	    
	    public AuthUserProfileAccess() {
	        super();

	    }

	    public String getId() {
	        return id;
	    }

	    public void setId(String id) {
	        this.id = id;
	    }

	    public String getFunctionality() {
	        return functionality;
	    }

	    public void setFunctionality(String functionality) {
	        this.functionality = functionality;
	    }

	    public String getFeature() {
	        return feature;
	    }

	    public void setFeature(String feature) {
	        this.feature = feature;
	    }

	    public String getDescription() {
	        return description;
	    }

	    public void setDescription(String description) {
	        this.description = description;
	    }

	    public String getSystemKeyCode() {
	        return systemKeyCode;
	    }

	    public void setSystemKeyCode(String systemKeyCode) {
	        this.systemKeyCode = systemKeyCode;
	    }

	    public String getUserTypeCode() {
	        return userTypeCode;
	    }

	    public void setUserTypeCode(String userTypeCode) {
	        this.userTypeCode = userTypeCode;
	    }

	    public String getOrgTypeCode() {
	        return orgTypeCode;
	    }

	    public void setOrgTypeCode(String orgTypeCode) {
	        this.orgTypeCode = orgTypeCode;
	    }

	    public Set<AuthUser> getAuthUsers() {
	        return authUsers;
	    }

	    public void setAuthUsers(Set<AuthUser> authUsers) {
	        this.authUsers = authUsers;
	    }

	    @Override
	    public String toString() {
	        return "AuthUserProfileAccess [id=" + id + ", functionality=" + functionality + ", feature=" + feature
	                + ", description=" + description + ", systemKeyCode=" + systemKeyCode + ", userTypeCode=" + userTypeCode
	                + ", orgTypeCode=" + orgTypeCode + ", authUsers=" + authUsers + "]";
	    }
}
