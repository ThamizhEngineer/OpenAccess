package com.ss.oashared.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="AUTH_USER_TYPE_ACCESS")
public class AuthUserTypeAccess {
 @Id
  @Column(name="ID")
  private String id ;
  @Column(name="USER_TYPE_CODE")
  private String userTypeCode;
  @Column(name="TRANSACTION_TYPE_CODE")
  private String transactionTypeCode;
  @Column(name="FUEL_GROUP_NAME")
  private String fuelGroupName;
public AuthUserTypeAccess() {
	super();
	// TODO Auto-generated constructor stub
}
public AuthUserTypeAccess(String id, String userTypeCode, String transactionTypeCode, String fuelGroupName) {
	super();
	this.id = id;
	this.userTypeCode = userTypeCode;
	this.transactionTypeCode = transactionTypeCode;
	this.fuelGroupName = fuelGroupName;
}
@Override
public String toString() {
	return "AuthUserTypeAccess [id=" + id + ", userTypeCode=" + userTypeCode + ", transactionTypeCode="
			+ transactionTypeCode + ", fuelGroupName=" + fuelGroupName + "]";
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getUserTypeCode() {
	return userTypeCode;
}
public void setUserTypeCode(String userTypeCode) {
	this.userTypeCode = userTypeCode;
}
public String getTransactionTypeCode() {
	return transactionTypeCode;
}
public void setTransactionTypeCode(String transactionTypeCode) {
	this.transactionTypeCode = transactionTypeCode;
}
public String getFuelGroupName() {
	return fuelGroupName;
}
public void setFuelGroupName(String fuelGroupName) {
	this.fuelGroupName = fuelGroupName;
}
  
  
  
}
