package com.ss.oa.transaction.vo;
public class Transformer {
	private String id;
	private String gcId;
	private String name;
	private String mvaRating;
	private String primaryVoltageCode;
	private String secondaryVoltageCode;
	private String primaryVoltageName;
	private String secondaryVoltageName;
	private String coolingType;
	private String windingConfig;
	private String breakerRating;
	private String tapSetting;
	private String tapStep;
	private String tapStepOffLoad;
	private String tapStepOnLoad;
	private String tapRatio;
	private String tapNumberMax;
	private String tapNumberMin;
	private String tapVoltageMax;
	private String tapVoltageMin;
	private String phaseDisplacement;
	private String impedencePercentage;
	private String leakReact;
	private String resistance;
	private String react;
	private String remarks;
	private String createdBy;
	private String createdDate;
	private String modifiedBy;
	private String modifiedDate;
	private String enabled;
	
	public Transformer() {
		super();
	}

	public Transformer(String id, String gcId, String name, String mvaRating, String primaryVoltageCode,
			String secondaryVoltageCode, String primaryVoltageName, String secondaryVoltageName, String coolingType,
			String windingConfig, String breakerRating, String tapSetting, String tapStep, String tapStepOffLoad,
			String tapStepOnLoad, String tapRatio, String tapNumberMax, String tapNumberMin, String tapVoltageMax,
			String tapVoltageMin, String phaseDisplacement, String impedencePercentage, String leakReact,
			String resistance, String react, String remarks, String createdBy, String createdDate, String modifiedBy,
			String modifiedDate, String enabled) {
		super();
		this.id = id;
		this.gcId = gcId;
		this.name = name;
		this.mvaRating = mvaRating;
		this.primaryVoltageCode = primaryVoltageCode;
		this.secondaryVoltageCode = secondaryVoltageCode;
		this.primaryVoltageName = primaryVoltageName;
		this.secondaryVoltageName = secondaryVoltageName;
		this.coolingType = coolingType;
		this.windingConfig = windingConfig;
		this.breakerRating = breakerRating;
		this.tapSetting = tapSetting;
		this.tapStep = tapStep;
		this.tapStepOffLoad = tapStepOffLoad;
		this.tapStepOnLoad = tapStepOnLoad;
		this.tapRatio = tapRatio;
		this.tapNumberMax = tapNumberMax;
		this.tapNumberMin = tapNumberMin;
		this.tapVoltageMax = tapVoltageMax;
		this.tapVoltageMin = tapVoltageMin;
		this.phaseDisplacement = phaseDisplacement;
		this.impedencePercentage = impedencePercentage;
		this.leakReact = leakReact;
		this.resistance = resistance;
		this.react = react;
		this.remarks = remarks;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "Transformer [id=" + id + ", gcId=" + gcId + ", name=" + name + ", mvaRating=" + mvaRating
				+ ", primaryVoltageCode=" + primaryVoltageCode + ", secondaryVoltageCode=" + secondaryVoltageCode
				+ ", primaryVoltageName=" + primaryVoltageName + ", secondaryVoltageName=" + secondaryVoltageName
				+ ", coolingType=" + coolingType + ", windingConfig=" + windingConfig + ", breakerRating="
				+ breakerRating + ", tapSetting=" + tapSetting + ", tapStep=" + tapStep + ", tapStepOffLoad="
				+ tapStepOffLoad + ", tapStepOnLoad=" + tapStepOnLoad + ", tapRatio=" + tapRatio + ", tapNumberMax="
				+ tapNumberMax + ", tapNumberMin=" + tapNumberMin + ", tapVoltageMax=" + tapVoltageMax
				+ ", tapVoltageMin=" + tapVoltageMin + ", phaseDisplacement=" + phaseDisplacement
				+ ", impedencePercentage=" + impedencePercentage + ", leakReact=" + leakReact + ", resistance="
				+ resistance + ", react=" + react + ", remarks=" + remarks + ", createdBy=" + createdBy
				+ ", createdDate=" + createdDate + ", modifiedBy=" + modifiedBy + ", modifiedDate=" + modifiedDate
				+ ", enabled=" + enabled + "]";
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMvaRating() {
		return mvaRating;
	}

	public void setMvaRating(String mvaRating) {
		this.mvaRating = mvaRating;
	}

	public String getPrimaryVoltageCode() {
		return primaryVoltageCode;
	}

	public void setPrimaryVoltageCode(String primaryVoltageCode) {
		this.primaryVoltageCode = primaryVoltageCode;
	}

	public String getSecondaryVoltageCode() {
		return secondaryVoltageCode;
	}

	public void setSecondaryVoltageCode(String secondaryVoltageCode) {
		this.secondaryVoltageCode = secondaryVoltageCode;
	}

	public String getPrimaryVoltageName() {
		return primaryVoltageName;
	}

	public void setPrimaryVoltageName(String primaryVoltageName) {
		this.primaryVoltageName = primaryVoltageName;
	}

	public String getSecondaryVoltageName() {
		return secondaryVoltageName;
	}

	public void setSecondaryVoltageName(String secondaryVoltageName) {
		this.secondaryVoltageName = secondaryVoltageName;
	}

	public String getCoolingType() {
		return coolingType;
	}

	public void setCoolingType(String coolingType) {
		this.coolingType = coolingType;
	}

	public String getWindingConfig() {
		return windingConfig;
	}

	public void setWindingConfig(String windingConfig) {
		this.windingConfig = windingConfig;
	}

	public String getBreakerRating() {
		return breakerRating;
	}

	public void setBreakerRating(String breakerRating) {
		this.breakerRating = breakerRating;
	}

	public String getTapSetting() {
		return tapSetting;
	}

	public void setTapSetting(String tapSetting) {
		this.tapSetting = tapSetting;
	}

	public String getTapStep() {
		return tapStep;
	}

	public void setTapStep(String tapStep) {
		this.tapStep = tapStep;
	}

	public String getTapStepOffLoad() {
		return tapStepOffLoad;
	}

	public void setTapStepOffLoad(String tapStepOffLoad) {
		this.tapStepOffLoad = tapStepOffLoad;
	}

	public String getTapStepOnLoad() {
		return tapStepOnLoad;
	}

	public void setTapStepOnLoad(String tapStepOnLoad) {
		this.tapStepOnLoad = tapStepOnLoad;
	}

	public String getTapRatio() {
		return tapRatio;
	}

	public void setTapRatio(String tapRatio) {
		this.tapRatio = tapRatio;
	}

	public String getTapNumberMax() {
		return tapNumberMax;
	}

	public void setTapNumberMax(String tapNumberMax) {
		this.tapNumberMax = tapNumberMax;
	}

	public String getTapNumberMin() {
		return tapNumberMin;
	}

	public void setTapNumberMin(String tapNumberMin) {
		this.tapNumberMin = tapNumberMin;
	}

	public String getTapVoltageMax() {
		return tapVoltageMax;
	}

	public void setTapVoltageMax(String tapVoltageMax) {
		this.tapVoltageMax = tapVoltageMax;
	}

	public String getTapVoltageMin() {
		return tapVoltageMin;
	}

	public void setTapVoltageMin(String tapVoltageMin) {
		this.tapVoltageMin = tapVoltageMin;
	}

	public String getPhaseDisplacement() {
		return phaseDisplacement;
	}

	public void setPhaseDisplacement(String phaseDisplacement) {
		this.phaseDisplacement = phaseDisplacement;
	}

	public String getImpedencePercentage() {
		return impedencePercentage;
	}

	public void setImpedencePercentage(String impedencePercentage) {
		this.impedencePercentage = impedencePercentage;
	}

	public String getLeakReact() {
		return leakReact;
	}

	public void setLeakReact(String leakReact) {
		this.leakReact = leakReact;
	}

	public String getResistance() {
		return resistance;
	}

	public void setResistance(String resistance) {
		this.resistance = resistance;
	}

	public String getReact() {
		return react;
	}

	public void setReact(String react) {
		this.react = react;
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
