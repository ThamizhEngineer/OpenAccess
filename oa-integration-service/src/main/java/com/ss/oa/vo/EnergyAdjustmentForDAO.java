package com.ss.oa.vo;

public class EnergyAdjustmentForDAO {
	private String energyLederId, serviceNumber, buyerName, billingMonth, billingYear,orgCode,orgName;
	private String sequence, sellerServiceNumber,sellerServiceName,commissionDate,isRec,adjustmentPriority,allowLowerSlotAdmt;
	private String inj_c1,inj_c2, inj_c3, inj_c4, inj_c5, inj_units;
	private String tl_c1,tl_c2, tl_c3, tl_c4, tl_c5, tl_units;
	private String dl_c1,dl_c2, dl_c3, dl_c4, dl_c5, dl_units;
	private String l_c1,l_c2, l_c3, l_c4, l_c5, l_units;
	private String dr_c1,dr_c2, dr_c3, dr_c4, dr_c5, dr_units;
	private String C001_CHARGE_CODE, C001_CHARGE_DESC, C001_TOTAL_CHARGES;
	private String C002_CHARGE_CODE, C002_CHARGE_DESC, C002_TOTAL_CHARGES;
	private String C003_CHARGE_CODE, C003_CHARGE_DESC, C003_TOTAL_CHARGES;
	private String C004_CHARGE_CODE, C004_CHARGE_DESC, C004_TOTAL_CHARGES;
	private String C005_CHARGE_CODE, C005_CHARGE_DESC, C005_TOTAL_CHARGES;
	private String C006_CHARGE_CODE, C006_CHARGE_DESC, C006_TOTAL_CHARGES;
	private String C007_CHARGE_CODE, C007_CHARGE_DESC, C007_TOTAL_CHARGES;
	private String C008_CHARGE_CODE, C008_CHARGE_DESC, C008_TOTAL_CHARGES;

	public EnergyAdjustmentForDAO()
	{
		super();
	}

	public EnergyAdjustmentForDAO(String energyLederId, String serviceNumber, String buyerName, String billingMonth,
			String billingYear, String orgCode, String orgName, String sequence, String sellerServiceNumber,
			String sellerServiceName, String commissionDate, String isRec, String adjustmentPriority,
			String allowLowerSlotAdmt, String inj_c1, String inj_c2, String inj_c3, String inj_c4, String inj_c5,
			String inj_units, String tl_c1, String tl_c2, String tl_c3, String tl_c4, String tl_c5, String tl_units,
			String dl_c1, String dl_c2, String dl_c3, String dl_c4, String dl_c5, String dl_units, String l_c1,
			String l_c2, String l_c3, String l_c4, String l_c5, String l_units, String dr_c1, String dr_c2,
			String dr_c3, String dr_c4, String dr_c5, String dr_units, String c001_CHARGE_CODE, String c001_CHARGE_DESC,
			String c001_TOTAL_CHARGES, String c002_CHARGE_CODE, String c002_CHARGE_DESC, String c002_TOTAL_CHARGES,
			String c003_CHARGE_CODE, String c003_CHARGE_DESC, String c003_TOTAL_CHARGES, String c004_CHARGE_CODE,
			String c004_CHARGE_DESC, String c004_TOTAL_CHARGES, String c005_CHARGE_CODE, String c005_CHARGE_DESC,
			String c005_TOTAL_CHARGES, String c006_CHARGE_CODE, String c006_CHARGE_DESC, String c006_TOTAL_CHARGES,
			String c007_CHARGE_CODE, String c007_CHARGE_DESC, String c007_TOTAL_CHARGES, String c008_CHARGE_CODE,
			String c008_CHARGE_DESC, String c008_TOTAL_CHARGES) {
		super();
		this.energyLederId = energyLederId;
		this.serviceNumber = serviceNumber;
		this.buyerName = buyerName;
		this.billingMonth = billingMonth;
		this.billingYear = billingYear;
		this.orgCode = orgCode;
		this.orgName = orgName;
		this.sequence = sequence;
		this.sellerServiceNumber = sellerServiceNumber;
		this.sellerServiceName = sellerServiceName;
		this.commissionDate = commissionDate;
		this.isRec = isRec;
		this.adjustmentPriority = adjustmentPriority;
		this.allowLowerSlotAdmt = allowLowerSlotAdmt;
		this.inj_c1 = inj_c1;
		this.inj_c2 = inj_c2;
		this.inj_c3 = inj_c3;
		this.inj_c4 = inj_c4;
		this.inj_c5 = inj_c5;
		this.inj_units = inj_units;
		this.tl_c1 = tl_c1;
		this.tl_c2 = tl_c2;
		this.tl_c3 = tl_c3;
		this.tl_c4 = tl_c4;
		this.tl_c5 = tl_c5;
		this.tl_units = tl_units;
		this.dl_c1 = dl_c1;
		this.dl_c2 = dl_c2;
		this.dl_c3 = dl_c3;
		this.dl_c4 = dl_c4;
		this.dl_c5 = dl_c5;
		this.dl_units = dl_units;
		this.l_c1 = l_c1;
		this.l_c2 = l_c2;
		this.l_c3 = l_c3;
		this.l_c4 = l_c4;
		this.l_c5 = l_c5;
		this.l_units = l_units;
		this.dr_c1 = dr_c1;
		this.dr_c2 = dr_c2;
		this.dr_c3 = dr_c3;
		this.dr_c4 = dr_c4;
		this.dr_c5 = dr_c5;
		this.dr_units = dr_units;
		C001_CHARGE_CODE = c001_CHARGE_CODE;
		C001_CHARGE_DESC = c001_CHARGE_DESC;
		C001_TOTAL_CHARGES = c001_TOTAL_CHARGES;
		C002_CHARGE_CODE = c002_CHARGE_CODE;
		C002_CHARGE_DESC = c002_CHARGE_DESC;
		C002_TOTAL_CHARGES = c002_TOTAL_CHARGES;
		C003_CHARGE_CODE = c003_CHARGE_CODE;
		C003_CHARGE_DESC = c003_CHARGE_DESC;
		C003_TOTAL_CHARGES = c003_TOTAL_CHARGES;
		C004_CHARGE_CODE = c004_CHARGE_CODE;
		C004_CHARGE_DESC = c004_CHARGE_DESC;
		C004_TOTAL_CHARGES = c004_TOTAL_CHARGES;
		C005_CHARGE_CODE = c005_CHARGE_CODE;
		C005_CHARGE_DESC = c005_CHARGE_DESC;
		C005_TOTAL_CHARGES = c005_TOTAL_CHARGES;
		C006_CHARGE_CODE = c006_CHARGE_CODE;
		C006_CHARGE_DESC = c006_CHARGE_DESC;
		C006_TOTAL_CHARGES = c006_TOTAL_CHARGES;
		C007_CHARGE_CODE = c007_CHARGE_CODE;
		C007_CHARGE_DESC = c007_CHARGE_DESC;
		C007_TOTAL_CHARGES = c007_TOTAL_CHARGES;
		C008_CHARGE_CODE = c008_CHARGE_CODE;
		C008_CHARGE_DESC = c008_CHARGE_DESC;
		C008_TOTAL_CHARGES = c008_TOTAL_CHARGES;
	}

	@Override
	public String toString() {
		return "EnergyAdjustmentForDAO [energyLederId=" + energyLederId + ", serviceNumber=" + serviceNumber
				+ ", buyerName=" + buyerName + ", billingMonth=" + billingMonth + ", billingYear=" + billingYear
				+ ", orgCode=" + orgCode + ", orgName=" + orgName + ", sequence=" + sequence + ", sellerServiceNumber="
				+ sellerServiceNumber + ", sellerServiceName=" + sellerServiceName + ", commissionDate="
				+ commissionDate + ", isRec=" + isRec + ", adjustmentPriority=" + adjustmentPriority
				+ ", allowLowerSlotAdmt=" + allowLowerSlotAdmt + ", inj_c1=" + inj_c1 + ", inj_c2=" + inj_c2
				+ ", inj_c3=" + inj_c3 + ", inj_c4=" + inj_c4 + ", inj_c5=" + inj_c5 + ", inj_units=" + inj_units
				+ ", tl_c1=" + tl_c1 + ", tl_c2=" + tl_c2 + ", tl_c3=" + tl_c3 + ", tl_c4=" + tl_c4 + ", tl_c5=" + tl_c5
				+ ", tl_units=" + tl_units + ", dl_c1=" + dl_c1 + ", dl_c2=" + dl_c2 + ", dl_c3=" + dl_c3 + ", dl_c4="
				+ dl_c4 + ", dl_c5=" + dl_c5 + ", dl_units=" + dl_units + ", l_c1=" + l_c1 + ", l_c2=" + l_c2
				+ ", l_c3=" + l_c3 + ", l_c4=" + l_c4 + ", l_c5=" + l_c5 + ", l_units=" + l_units + ", dr_c1=" + dr_c1
				+ ", dr_c2=" + dr_c2 + ", dr_c3=" + dr_c3 + ", dr_c4=" + dr_c4 + ", dr_c5=" + dr_c5 + ", dr_units="
				+ dr_units + ", C001_CHARGE_CODE=" + C001_CHARGE_CODE + ", C001_CHARGE_DESC=" + C001_CHARGE_DESC
				+ ", C001_TOTAL_CHARGES=" + C001_TOTAL_CHARGES + ", C002_CHARGE_CODE=" + C002_CHARGE_CODE
				+ ", C002_CHARGE_DESC=" + C002_CHARGE_DESC + ", C002_TOTAL_CHARGES=" + C002_TOTAL_CHARGES
				+ ", C003_CHARGE_CODE=" + C003_CHARGE_CODE + ", C003_CHARGE_DESC=" + C003_CHARGE_DESC
				+ ", C003_TOTAL_CHARGES=" + C003_TOTAL_CHARGES + ", C004_CHARGE_CODE=" + C004_CHARGE_CODE
				+ ", C004_CHARGE_DESC=" + C004_CHARGE_DESC + ", C004_TOTAL_CHARGES=" + C004_TOTAL_CHARGES
				+ ", C005_CHARGE_CODE=" + C005_CHARGE_CODE + ", C005_CHARGE_DESC=" + C005_CHARGE_DESC
				+ ", C005_TOTAL_CHARGES=" + C005_TOTAL_CHARGES + ", C006_CHARGE_CODE=" + C006_CHARGE_CODE
				+ ", C006_CHARGE_DESC=" + C006_CHARGE_DESC + ", C006_TOTAL_CHARGES=" + C006_TOTAL_CHARGES
				+ ", C007_CHARGE_CODE=" + C007_CHARGE_CODE + ", C007_CHARGE_DESC=" + C007_CHARGE_DESC
				+ ", C007_TOTAL_CHARGES=" + C007_TOTAL_CHARGES + ", C008_CHARGE_CODE=" + C008_CHARGE_CODE
				+ ", C008_CHARGE_DESC=" + C008_CHARGE_DESC + ", C008_TOTAL_CHARGES=" + C008_TOTAL_CHARGES + "]";
	}

	public String getEnergyLederId() {
		return energyLederId;
	}

	public void setEnergyLederId(String energyLederId) {
		this.energyLederId = energyLederId;
	}

	public String getServiceNumber() {
		return serviceNumber;
	}

	public void setServiceNumber(String serviceNumber) {
		this.serviceNumber = serviceNumber;
	}

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	public String getBillingMonth() {
		return billingMonth;
	}

	public void setBillingMonth(String billingMonth) {
		this.billingMonth = billingMonth;
	}

	public String getBillingYear() {
		return billingYear;
	}

	public void setBillingYear(String billingYear) {
		this.billingYear = billingYear;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getSequence() {
		return sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}

	public String getSellerServiceNumber() {
		return sellerServiceNumber;
	}

	public void setSellerServiceNumber(String sellerServiceNumber) {
		this.sellerServiceNumber = sellerServiceNumber;
	}

	public String getSellerServiceName() {
		return sellerServiceName;
	}

	public void setSellerServiceName(String sellerServiceName) {
		this.sellerServiceName = sellerServiceName;
	}

	public String getCommissionDate() {
		return commissionDate;
	}

	public void setCommissionDate(String commissionDate) {
		this.commissionDate = commissionDate;
	}

	public String getIsRec() {
		return isRec;
	}

	public void setIsRec(String isRec) {
		this.isRec = isRec;
	}

	public String getAdjustmentPriority() {
		return adjustmentPriority;
	}

	public void setAdjustmentPriority(String adjustmentPriority) {
		this.adjustmentPriority = adjustmentPriority;
	}

	public String getAllowLowerSlotAdmt() {
		return allowLowerSlotAdmt;
	}

	public void setAllowLowerSlotAdmt(String allowLowerSlotAdmt) {
		this.allowLowerSlotAdmt = allowLowerSlotAdmt;
	}

	public String getInj_c1() {
		return inj_c1;
	}

	public void setInj_c1(String inj_c1) {
		this.inj_c1 = inj_c1;
	}

	public String getInj_c2() {
		return inj_c2;
	}

	public void setInj_c2(String inj_c2) {
		this.inj_c2 = inj_c2;
	}

	public String getInj_c3() {
		return inj_c3;
	}

	public void setInj_c3(String inj_c3) {
		this.inj_c3 = inj_c3;
	}

	public String getInj_c4() {
		return inj_c4;
	}

	public void setInj_c4(String inj_c4) {
		this.inj_c4 = inj_c4;
	}

	public String getInj_c5() {
		return inj_c5;
	}

	public void setInj_c5(String inj_c5) {
		this.inj_c5 = inj_c5;
	}

	public String getInj_units() {
		return inj_units;
	}

	public void setInj_units(String inj_units) {
		this.inj_units = inj_units;
	}

	public String getTl_c1() {
		return tl_c1;
	}

	public void setTl_c1(String tl_c1) {
		this.tl_c1 = tl_c1;
	}

	public String getTl_c2() {
		return tl_c2;
	}

	public void setTl_c2(String tl_c2) {
		this.tl_c2 = tl_c2;
	}

	public String getTl_c3() {
		return tl_c3;
	}

	public void setTl_c3(String tl_c3) {
		this.tl_c3 = tl_c3;
	}

	public String getTl_c4() {
		return tl_c4;
	}

	public void setTl_c4(String tl_c4) {
		this.tl_c4 = tl_c4;
	}

	public String getTl_c5() {
		return tl_c5;
	}

	public void setTl_c5(String tl_c5) {
		this.tl_c5 = tl_c5;
	}

	public String getTl_units() {
		return tl_units;
	}

	public void setTl_units(String tl_units) {
		this.tl_units = tl_units;
	}

	public String getDl_c1() {
		return dl_c1;
	}

	public void setDl_c1(String dl_c1) {
		this.dl_c1 = dl_c1;
	}

	public String getDl_c2() {
		return dl_c2;
	}

	public void setDl_c2(String dl_c2) {
		this.dl_c2 = dl_c2;
	}

	public String getDl_c3() {
		return dl_c3;
	}

	public void setDl_c3(String dl_c3) {
		this.dl_c3 = dl_c3;
	}

	public String getDl_c4() {
		return dl_c4;
	}

	public void setDl_c4(String dl_c4) {
		this.dl_c4 = dl_c4;
	}

	public String getDl_c5() {
		return dl_c5;
	}

	public void setDl_c5(String dl_c5) {
		this.dl_c5 = dl_c5;
	}

	public String getDl_units() {
		return dl_units;
	}

	public void setDl_units(String dl_units) {
		this.dl_units = dl_units;
	}

	public String getL_c1() {
		return l_c1;
	}

	public void setL_c1(String l_c1) {
		this.l_c1 = l_c1;
	}

	public String getL_c2() {
		return l_c2;
	}

	public void setL_c2(String l_c2) {
		this.l_c2 = l_c2;
	}

	public String getL_c3() {
		return l_c3;
	}

	public void setL_c3(String l_c3) {
		this.l_c3 = l_c3;
	}

	public String getL_c4() {
		return l_c4;
	}

	public void setL_c4(String l_c4) {
		this.l_c4 = l_c4;
	}

	public String getL_c5() {
		return l_c5;
	}

	public void setL_c5(String l_c5) {
		this.l_c5 = l_c5;
	}

	public String getL_units() {
		return l_units;
	}

	public void setL_units(String l_units) {
		this.l_units = l_units;
	}

	public String getDr_c1() {
		return dr_c1;
	}

	public void setDr_c1(String dr_c1) {
		this.dr_c1 = dr_c1;
	}

	public String getDr_c2() {
		return dr_c2;
	}

	public void setDr_c2(String dr_c2) {
		this.dr_c2 = dr_c2;
	}

	public String getDr_c3() {
		return dr_c3;
	}

	public void setDr_c3(String dr_c3) {
		this.dr_c3 = dr_c3;
	}

	public String getDr_c4() {
		return dr_c4;
	}

	public void setDr_c4(String dr_c4) {
		this.dr_c4 = dr_c4;
	}

	public String getDr_c5() {
		return dr_c5;
	}

	public void setDr_c5(String dr_c5) {
		this.dr_c5 = dr_c5;
	}

	public String getDr_units() {
		return dr_units;
	}

	public void setDr_units(String dr_units) {
		this.dr_units = dr_units;
	}

	public String getC001_CHARGE_CODE() {
		return C001_CHARGE_CODE;
	}

	public void setC001_CHARGE_CODE(String c001_CHARGE_CODE) {
		C001_CHARGE_CODE = c001_CHARGE_CODE;
	}

	public String getC001_CHARGE_DESC() {
		return C001_CHARGE_DESC;
	}

	public void setC001_CHARGE_DESC(String c001_CHARGE_DESC) {
		C001_CHARGE_DESC = c001_CHARGE_DESC;
	}

	public String getC001_TOTAL_CHARGES() {
		return C001_TOTAL_CHARGES;
	}

	public void setC001_TOTAL_CHARGES(String c001_TOTAL_CHARGES) {
		C001_TOTAL_CHARGES = c001_TOTAL_CHARGES;
	}

	public String getC002_CHARGE_CODE() {
		return C002_CHARGE_CODE;
	}

	public void setC002_CHARGE_CODE(String c002_CHARGE_CODE) {
		C002_CHARGE_CODE = c002_CHARGE_CODE;
	}

	public String getC002_CHARGE_DESC() {
		return C002_CHARGE_DESC;
	}

	public void setC002_CHARGE_DESC(String c002_CHARGE_DESC) {
		C002_CHARGE_DESC = c002_CHARGE_DESC;
	}

	public String getC002_TOTAL_CHARGES() {
		return C002_TOTAL_CHARGES;
	}

	public void setC002_TOTAL_CHARGES(String c002_TOTAL_CHARGES) {
		C002_TOTAL_CHARGES = c002_TOTAL_CHARGES;
	}

	public String getC003_CHARGE_CODE() {
		return C003_CHARGE_CODE;
	}

	public void setC003_CHARGE_CODE(String c003_CHARGE_CODE) {
		C003_CHARGE_CODE = c003_CHARGE_CODE;
	}

	public String getC003_CHARGE_DESC() {
		return C003_CHARGE_DESC;
	}

	public void setC003_CHARGE_DESC(String c003_CHARGE_DESC) {
		C003_CHARGE_DESC = c003_CHARGE_DESC;
	}

	public String getC003_TOTAL_CHARGES() {
		return C003_TOTAL_CHARGES;
	}

	public void setC003_TOTAL_CHARGES(String c003_TOTAL_CHARGES) {
		C003_TOTAL_CHARGES = c003_TOTAL_CHARGES;
	}

	public String getC004_CHARGE_CODE() {
		return C004_CHARGE_CODE;
	}

	public void setC004_CHARGE_CODE(String c004_CHARGE_CODE) {
		C004_CHARGE_CODE = c004_CHARGE_CODE;
	}

	public String getC004_CHARGE_DESC() {
		return C004_CHARGE_DESC;
	}

	public void setC004_CHARGE_DESC(String c004_CHARGE_DESC) {
		C004_CHARGE_DESC = c004_CHARGE_DESC;
	}

	public String getC004_TOTAL_CHARGES() {
		return C004_TOTAL_CHARGES;
	}

	public void setC004_TOTAL_CHARGES(String c004_TOTAL_CHARGES) {
		C004_TOTAL_CHARGES = c004_TOTAL_CHARGES;
	}

	public String getC005_CHARGE_CODE() {
		return C005_CHARGE_CODE;
	}

	public void setC005_CHARGE_CODE(String c005_CHARGE_CODE) {
		C005_CHARGE_CODE = c005_CHARGE_CODE;
	}

	public String getC005_CHARGE_DESC() {
		return C005_CHARGE_DESC;
	}

	public void setC005_CHARGE_DESC(String c005_CHARGE_DESC) {
		C005_CHARGE_DESC = c005_CHARGE_DESC;
	}

	public String getC005_TOTAL_CHARGES() {
		return C005_TOTAL_CHARGES;
	}

	public void setC005_TOTAL_CHARGES(String c005_TOTAL_CHARGES) {
		C005_TOTAL_CHARGES = c005_TOTAL_CHARGES;
	}

	public String getC006_CHARGE_CODE() {
		return C006_CHARGE_CODE;
	}

	public void setC006_CHARGE_CODE(String c006_CHARGE_CODE) {
		C006_CHARGE_CODE = c006_CHARGE_CODE;
	}

	public String getC006_CHARGE_DESC() {
		return C006_CHARGE_DESC;
	}

	public void setC006_CHARGE_DESC(String c006_CHARGE_DESC) {
		C006_CHARGE_DESC = c006_CHARGE_DESC;
	}

	public String getC006_TOTAL_CHARGES() {
		return C006_TOTAL_CHARGES;
	}

	public void setC006_TOTAL_CHARGES(String c006_TOTAL_CHARGES) {
		C006_TOTAL_CHARGES = c006_TOTAL_CHARGES;
	}

	public String getC007_CHARGE_CODE() {
		return C007_CHARGE_CODE;
	}

	public void setC007_CHARGE_CODE(String c007_CHARGE_CODE) {
		C007_CHARGE_CODE = c007_CHARGE_CODE;
	}

	public String getC007_CHARGE_DESC() {
		return C007_CHARGE_DESC;
	}

	public void setC007_CHARGE_DESC(String c007_CHARGE_DESC) {
		C007_CHARGE_DESC = c007_CHARGE_DESC;
	}

	public String getC007_TOTAL_CHARGES() {
		return C007_TOTAL_CHARGES;
	}

	public void setC007_TOTAL_CHARGES(String c007_TOTAL_CHARGES) {
		C007_TOTAL_CHARGES = c007_TOTAL_CHARGES;
	}

	public String getC008_CHARGE_CODE() {
		return C008_CHARGE_CODE;
	}

	public void setC008_CHARGE_CODE(String c008_CHARGE_CODE) {
		C008_CHARGE_CODE = c008_CHARGE_CODE;
	}

	public String getC008_CHARGE_DESC() {
		return C008_CHARGE_DESC;
	}

	public void setC008_CHARGE_DESC(String c008_CHARGE_DESC) {
		C008_CHARGE_DESC = c008_CHARGE_DESC;
	}

	public String getC008_TOTAL_CHARGES() {
		return C008_TOTAL_CHARGES;
	}

	public void setC008_TOTAL_CHARGES(String c008_TOTAL_CHARGES) {
		C008_TOTAL_CHARGES = c008_TOTAL_CHARGES;
	}

	
}