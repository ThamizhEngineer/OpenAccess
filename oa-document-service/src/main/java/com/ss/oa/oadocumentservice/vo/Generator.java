package com.ss.oa.oadocumentservice.vo;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Generator{

	String id ;
	String powerplantId;
	String name ;
	String makeCode ; 
	String makeName ;
	String serialNumber; 
	String rotorDia;
	String hubHeight;
	String capacity;
	String refId;
	String voltageCode;
	String voltageName;
	String remarks;
	String createdBy;
	@JsonFormat(pattern = "yyyy-MM-dd")
	LocalDate createdDate;
	String modifiedBy;
	@JsonFormat(pattern = "yyyy-MM-dd")
	LocalDate modifiedDate;
	String noOfUnits;
	String importRemarks;
	String make;
	String mwRating;
	String mvRating;
	String kvRating;
	String dampingFactor;
	String amateurResistance;
	String directAssistanceReactance;
	String negativeSequenceReactance;
	String zeroSequenceReactance;
	String directAxisTransientReactance;
	String quadratureAxisTransientReactance;
	String directAxisSubTransientReactance;
	String inertia;
	String windingConnection;
	String massNumber;
	String stiffnessCoefficient;
	String status;
	String gcId;
	String finalCod;
	String finalCopd;
	String isCaptive;

	public Generator() {
		super();
	}

	public Generator(String id, String powerplantId, String name, String makeCode, String makeName, String serialNumber,
			String rotorDia, String hubHeight, String capacity, String refId, String voltageCode, String voltageName,
			String remarks, String createdBy, LocalDate createdDate, String modifiedBy, LocalDate modifiedDate,
			String noOfUnits, String importRemarks, String make, String mwRating, String mvRating, String kvRating,
			String dampingFactor, String amateurResistance, String directAssistanceReactance,
			String negativeSequenceReactance, String zeroSequenceReactance, String directAxisTransientReactance,
			String quadratureAxisTransientReactance, String directAxisSubTransientReactance, String inertia,
			String windingConnection, String massNumber, String stiffnessCoefficient, String status, String gcId,
			String finalCod, String finalCopd, String isCaptive) {
		super();
		this.id = id;
		this.powerplantId = powerplantId;
		this.name = name;
		this.makeCode = makeCode;
		this.makeName = makeName;
		this.serialNumber = serialNumber;
		this.rotorDia = rotorDia;
		this.hubHeight = hubHeight;
		this.capacity = capacity;
		this.refId = refId;
		this.voltageCode = voltageCode;
		this.voltageName = voltageName;
		this.remarks = remarks;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
		this.noOfUnits = noOfUnits;
		this.importRemarks = importRemarks;
		this.make = make;
		this.mwRating = mwRating;
		this.mvRating = mvRating;
		this.kvRating = kvRating;
		this.dampingFactor = dampingFactor;
		this.amateurResistance = amateurResistance;
		this.directAssistanceReactance = directAssistanceReactance;
		this.negativeSequenceReactance = negativeSequenceReactance;
		this.zeroSequenceReactance = zeroSequenceReactance;
		this.directAxisTransientReactance = directAxisTransientReactance;
		this.quadratureAxisTransientReactance = quadratureAxisTransientReactance;
		this.directAxisSubTransientReactance = directAxisSubTransientReactance;
		this.inertia = inertia;
		this.windingConnection = windingConnection;
		this.massNumber = massNumber;
		this.stiffnessCoefficient = stiffnessCoefficient;
		this.status = status;
		this.gcId = gcId;
		this.finalCod = finalCod;
		this.finalCopd = finalCopd;
		this.isCaptive = isCaptive;
	}

	@Override
	public String toString() {
		return "Generator [id=" + id + ", powerplantId=" + powerplantId + ", name=" + name + ", makeCode=" + makeCode
				+ ", makeName=" + makeName + ", serialNumber=" + serialNumber + ", rotorDia=" + rotorDia
				+ ", hubHeight=" + hubHeight + ", capacity=" + capacity + ", refId=" + refId + ", voltageCode="
				+ voltageCode + ", voltageName=" + voltageName + ", remarks=" + remarks + ", createdBy=" + createdBy
				+ ", createdDate=" + createdDate + ", modifiedBy=" + modifiedBy + ", modifiedDate=" + modifiedDate
				+ ", noOfUnits=" + noOfUnits + ", importRemarks=" + importRemarks + ", make=" + make + ", mwRating="
				+ mwRating + ", mvRating=" + mvRating + ", kvRating=" + kvRating + ", dampingFactor=" + dampingFactor
				+ ", amateurResistance=" + amateurResistance + ", directAssistanceReactance="
				+ directAssistanceReactance + ", negativeSequenceReactance=" + negativeSequenceReactance
				+ ", zeroSequenceReactance=" + zeroSequenceReactance + ", directAxisTransientReactance="
				+ directAxisTransientReactance + ", quadratureAxisTransientReactance="
				+ quadratureAxisTransientReactance + ", directAxisSubTransientReactance="
				+ directAxisSubTransientReactance + ", inertia=" + inertia + ", windingConnection=" + windingConnection
				+ ", massNumber=" + massNumber + ", stiffnessCoefficient=" + stiffnessCoefficient + ", status=" + status
				+ ", gcId=" + gcId + ", finalCod=" + finalCod + ", finalCopd=" + finalCopd + ", isCaptive=" + isCaptive
				+ "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPowerplantId() {
		return powerplantId;
	}

	public void setPowerplantId(String powerplantId) {
		this.powerplantId = powerplantId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMakeCode() {
		return makeCode;
	}

	public void setMakeCode(String makeCode) {
		this.makeCode = makeCode;
	}

	public String getMakeName() {
		return makeName;
	}

	public void setMakeName(String makeName) {
		this.makeName = makeName;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getRotorDia() {
		return rotorDia;
	}

	public void setRotorDia(String rotorDia) {
		this.rotorDia = rotorDia;
	}

	public String getHubHeight() {
		return hubHeight;
	}

	public void setHubHeight(String hubHeight) {
		this.hubHeight = hubHeight;
	}

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	public String getRefId() {
		return refId;
	}

	public void setRefId(String refId) {
		this.refId = refId;
	}

	public String getVoltageCode() {
		return voltageCode;
	}

	public void setVoltageCode(String voltageCode) {
		this.voltageCode = voltageCode;
	}

	public String getVoltageName() {
		return voltageName;
	}

	public void setVoltageName(String voltageName) {
		this.voltageName = voltageName;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public LocalDate getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(LocalDate modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getNoOfUnits() {
		return noOfUnits;
	}

	public void setNoOfUnits(String noOfUnits) {
		this.noOfUnits = noOfUnits;
	}

	public String getImportRemarks() {
		return importRemarks;
	}

	public void setImportRemarks(String importRemarks) {
		this.importRemarks = importRemarks;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getMwRating() {
		return mwRating;
	}

	public void setMwRating(String mwRating) {
		this.mwRating = mwRating;
	}

	public String getMvRating() {
		return mvRating;
	}

	public void setMvRating(String mvRating) {
		this.mvRating = mvRating;
	}

	public String getKvRating() {
		return kvRating;
	}

	public void setKvRating(String kvRating) {
		this.kvRating = kvRating;
	}

	public String getDampingFactor() {
		return dampingFactor;
	}

	public void setDampingFactor(String dampingFactor) {
		this.dampingFactor = dampingFactor;
	}

	public String getAmateurResistance() {
		return amateurResistance;
	}

	public void setAmateurResistance(String amateurResistance) {
		this.amateurResistance = amateurResistance;
	}

	public String getDirectAssistanceReactance() {
		return directAssistanceReactance;
	}

	public void setDirectAssistanceReactance(String directAssistanceReactance) {
		this.directAssistanceReactance = directAssistanceReactance;
	}

	public String getNegativeSequenceReactance() {
		return negativeSequenceReactance;
	}

	public void setNegativeSequenceReactance(String negativeSequenceReactance) {
		this.negativeSequenceReactance = negativeSequenceReactance;
	}

	public String getZeroSequenceReactance() {
		return zeroSequenceReactance;
	}

	public void setZeroSequenceReactance(String zeroSequenceReactance) {
		this.zeroSequenceReactance = zeroSequenceReactance;
	}

	public String getDirectAxisTransientReactance() {
		return directAxisTransientReactance;
	}

	public void setDirectAxisTransientReactance(String directAxisTransientReactance) {
		this.directAxisTransientReactance = directAxisTransientReactance;
	}

	public String getQuadratureAxisTransientReactance() {
		return quadratureAxisTransientReactance;
	}

	public void setQuadratureAxisTransientReactance(String quadratureAxisTransientReactance) {
		this.quadratureAxisTransientReactance = quadratureAxisTransientReactance;
	}

	public String getDirectAxisSubTransientReactance() {
		return directAxisSubTransientReactance;
	}

	public void setDirectAxisSubTransientReactance(String directAxisSubTransientReactance) {
		this.directAxisSubTransientReactance = directAxisSubTransientReactance;
	}

	public String getInertia() {
		return inertia;
	}

	public void setInertia(String inertia) {
		this.inertia = inertia;
	}

	public String getWindingConnection() {
		return windingConnection;
	}

	public void setWindingConnection(String windingConnection) {
		this.windingConnection = windingConnection;
	}

	public String getMassNumber() {
		return massNumber;
	}

	public void setMassNumber(String massNumber) {
		this.massNumber = massNumber;
	}

	public String getStiffnessCoefficient() {
		return stiffnessCoefficient;
	}

	public void setStiffnessCoefficient(String stiffnessCoefficient) {
		this.stiffnessCoefficient = stiffnessCoefficient;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getGcId() {
		return gcId;
	}

	public void setGcId(String gcId) {
		this.gcId = gcId;
	}

	public String getFinalCod() {
		return finalCod;
	}

	public void setFinalCod(String finalCod) {
		this.finalCod = finalCod;
	}

	public String getFinalCopd() {
		return finalCopd;
	}

	public void setFinalCopd(String finalCopd) {
		this.finalCopd = finalCopd;
	}

	public String getIsCaptive() {
		return isCaptive;
	}

	public void setIsCaptive(String isCaptive) {
		this.isCaptive = isCaptive;
	}

}
