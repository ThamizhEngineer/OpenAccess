package com.ss.oashared.model;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;

@Table(name = "T_GEN_STMT_SLOT_MC")
@Entity
public class MeterChangeMc {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer Id;
	@Column(name = "SERVICE_NO")
	private String ServiceNo;
	@Column(name = "READING_MONTH")
	private String ReadingMonth;
	@Column(name = "READING_YEAR")
	private String ReadingYear;
	@Column(name = "IMP_INIT_C1")
	private String ImpInitC1;
	@Column(name = "IMP_INIT_C2")
	private String ImpInitC2;
	@Column(name = "IMP_INIT_C3")
	private String ImpInitC3;
	@Column(name = "IMP_INIT_C4")
	private String ImpInitC4;
	@Column(name = "IMP_INIT_C5")
	private String ImpInitC5;
	@Column(name = "IMP_FIN_C1")
	private String ImpFinC1;
	@Column(name = "IMP_FIN_C2")
	private String ImpFinC2;
	@Column(name = "IMP_FIN_C3")
	private String ImpFinC3;
	@Column(name = "IMP_FIN_C4")
	private String ImpFinC4;
	@Column(name = "IMP_FIN_C5")
	private String ImpFinC5;
	@Column(name = "EXP_INIT_C1")
	private String ExpInitC1;
	@Column(name = "EXP_INIT_C2")
	private String ExpInitC2;
	@Column(name = "EXP_INIT_C3")
	private String ExpInitC3;
	@Column(name = "EXP_INIT_C4")
	private String ExpInitC4;
	@Column(name = "EXP_INIT_C5")
	private String ExpInitC5;
	@Column(name = "EXP_FIN_C1")
	private String ExpFinC1;
	@Column(name = "EXP_FIN_C2")
	private String ExpFinC2;
	@Column(name = "EXP_FIN_C3")
	private String ExpFinC3;
	@Column(name = "EXP_FIN_C4")
	private String ExpFinC4;
	@Column(name = "EXP_FIN_C5")
	private String ExpFinC5;
	@Column(name = "NET_IMP_C1")
	private String NetImpC1;
	@Column(name = "NET_IMP_C2")
	private String NetImpC2;
	@Column(name = "NET_IMP_C3")
	private String NetImpC3;
	@Column(name = "NET_IMP_C4")
	private String NetImpC4;
	@Column(name = "NET_IMP_C5")
	private String NetImpC5;
	@Column(name = "NET_EXP_C1")
	private String NetExpC1;
	@Column(name = "NET_EXP_C2")
	private String NetExpC2;
	@Column(name = "NET_EXP_C3")
	private String NetExpC3;
	@Column(name = "NET_EXP_C4")
	private String NetExpC4;
	@Column(name = "NET_EXP_C5")
	private String NetExpC5;
	@Column(name = "REMARKS")
	private String Remarks;
	@Column(name = "READING_TYPE")
	private String ReadingType;
	@Column(name = "DIFF_IMP_C1")
	private String DiffImpC1;
	@Column(name = "DIFF_IMP_C2")
	private String DiffImpC2;
	@Column(name = "DIFF_IMP_C3")
	private String DiffImpC3;
	@Column(name = "DIFF_IMP_C4")
	private String DiffImpC4;
	@Column(name = "DIFF_IMP_C5")
	private String DiffImpC5;
	@Column(name = "DIFF_EXP_C1")
	private String DiffExpC1;
	@Column(name = "DIFF_EXP_C2")
	private String DiffExpC2;
	@Column(name = "DIFF_EXP_C3")
	private String DiffExpC3;
	@Column(name = "DIFF_EXP_C4")
	private String DiffExpC4;
	@Column(name = "DIFF_EXP_C5")
	private String DiffExpC5;

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getServiceNo() {
		return ServiceNo;
	}

	public void setServiceNo(String serviceNo) {
		ServiceNo = serviceNo;
	}

	public String getReadingMonth() {
		return ReadingMonth;
	}

	public void setReadingMonth(String readingMonth) {
		ReadingMonth = readingMonth;
	}

	public String getReadingYear() {
		return ReadingYear;
	}

	public void setReadingYear(String readingYear) {
		ReadingYear = readingYear;
	}

	public String getImpInitC1() {
		return ImpInitC1;
	}

	public void setImpInitC1(String impInitC1) {
		ImpInitC1 = impInitC1;
	}

	public String getImpInitC2() {
		return ImpInitC2;
	}

	public void setImpInitC2(String impInitC2) {
		ImpInitC2 = impInitC2;
	}

	public String getImpInitC3() {
		return ImpInitC3;
	}

	public void setImpInitC3(String impInitC3) {
		ImpInitC3 = impInitC3;
	}

	public String getImpInitC4() {
		return ImpInitC4;
	}

	public void setImpInitC4(String impInitC4) {
		ImpInitC4 = impInitC4;
	}

	public String getImpInitC5() {
		return ImpInitC5;
	}

	public void setImpInitC5(String impInitC5) {
		ImpInitC5 = impInitC5;
	}

	public String getImpFinC1() {
		return ImpFinC1;
	}

	public void setImpFinC1(String impFinC1) {
		ImpFinC1 = impFinC1;
	}

	public String getImpFinC2() {
		return ImpFinC2;
	}

	public void setImpFinC2(String impFinC2) {
		ImpFinC2 = impFinC2;
	}

	public String getImpFinC3() {
		return ImpFinC3;
	}

	public void setImpFinC3(String impFinC3) {
		ImpFinC3 = impFinC3;
	}

	public String getImpFinC4() {
		return ImpFinC4;
	}

	public void setImpFinC4(String impFinC4) {
		ImpFinC4 = impFinC4;
	}

	public String getImpFinC5() {
		return ImpFinC5;
	}

	public void setImpFinC5(String impFinC5) {
		ImpFinC5 = impFinC5;
	}

	public String getExpInitC1() {
		return ExpInitC1;
	}

	public void setExpInitC1(String expInitC1) {
		ExpInitC1 = expInitC1;
	}

	public String getExpInitC2() {
		return ExpInitC2;
	}

	public void setExpInitC2(String expInitC2) {
		ExpInitC2 = expInitC2;
	}

	public String getExpInitC3() {
		return ExpInitC3;
	}

	public void setExpInitC3(String expInitC3) {
		ExpInitC3 = expInitC3;
	}

	public String getExpInitC4() {
		return ExpInitC4;
	}

	public void setExpInitC4(String expInitC4) {
		ExpInitC4 = expInitC4;
	}

	public String getExpInitC5() {
		return ExpInitC5;
	}

	public void setExpInitC5(String expInitC5) {
		ExpInitC5 = expInitC5;
	}

	public String getExpFinC1() {
		return ExpFinC1;
	}

	public void setExpFinC1(String expFinC1) {
		ExpFinC1 = expFinC1;
	}

	public String getExpFinC2() {
		return ExpFinC2;
	}

	public void setExpFinC2(String expFinC2) {
		ExpFinC2 = expFinC2;
	}

	public String getExpFinC3() {
		return ExpFinC3;
	}

	public void setExpFinC3(String expFinC3) {
		ExpFinC3 = expFinC3;
	}

	public String getExpFinC4() {
		return ExpFinC4;
	}

	public void setExpFinC4(String expFinC4) {
		ExpFinC4 = expFinC4;
	}

	public String getExpFinC5() {
		return ExpFinC5;
	}

	public void setExpFinC5(String expFinC5) {
		ExpFinC5 = expFinC5;
	}

	public String getNetImpC1() {
		return NetImpC1;
	}

	public void setNetImpC1(String netImpC1) {
		NetImpC1 = netImpC1;
	}

	public String getNetImpC2() {
		return NetImpC2;
	}

	public void setNetImpC2(String netImpC2) {
		NetImpC2 = netImpC2;
	}

	public String getNetImpC3() {
		return NetImpC3;
	}

	public void setNetImpC3(String netImpC3) {
		NetImpC3 = netImpC3;
	}

	public String getNetImpC4() {
		return NetImpC4;
	}

	public void setNetImpC4(String netImpC4) {
		NetImpC4 = netImpC4;
	}

	public String getNetImpC5() {
		return NetImpC5;
	}

	public void setNetImpC5(String netImpC5) {
		NetImpC5 = netImpC5;
	}

	public String getNetExpC1() {
		return NetExpC1;
	}

	public void setNetExpC1(String netExpC1) {
		NetExpC1 = netExpC1;
	}

	public String getNetExpC2() {
		return NetExpC2;
	}

	public void setNetExpC2(String netExpC2) {
		NetExpC2 = netExpC2;
	}

	public String getNetExpC3() {
		return NetExpC3;
	}

	public void setNetExpC3(String netExpC3) {
		NetExpC3 = netExpC3;
	}

	public String getNetExpC4() {
		return NetExpC4;
	}

	public void setNetExpC4(String netExpC4) {
		NetExpC4 = netExpC4;
	}

	public String getNetExpC5() {
		return NetExpC5;
	}

	public void setNetExpC5(String netExpC5) {
		NetExpC5 = netExpC5;
	}

	public String getRemarks() {
		return Remarks;
	}

	public void setRemarks(String remarks) {
		Remarks = remarks;
	}

	public String getReadingType() {
		return ReadingType;
	}

	public void setReadingType(String readingType) {
		ReadingType = readingType;
	}

	public String getDiffImpC1() {
		return DiffImpC1;
	}

	public void setDiffImpC1(String diffImpC1) {
		DiffImpC1 = diffImpC1;
	}

	public String getDiffImpC2() {
		return DiffImpC2;
	}

	public void setDiffImpC2(String diffImpC2) {
		DiffImpC2 = diffImpC2;
	}

	public String getDiffImpC3() {
		return DiffImpC3;
	}

	public void setDiffImpC3(String diffImpC3) {
		DiffImpC3 = diffImpC3;
	}

	public String getDiffImpC4() {
		return DiffImpC4;
	}

	public void setDiffImpC4(String diffImpC4) {
		DiffImpC4 = diffImpC4;
	}

	public String getDiffImpC5() {
		return DiffImpC5;
	}

	public void setDiffImpC5(String diffImpC5) {
		DiffImpC5 = diffImpC5;
	}

	public String getDiffExpC1() {
		return DiffExpC1;
	}

	public void setDiffExpC1(String diffExpC1) {
		DiffExpC1 = diffExpC1;
	}

	public String getDiffExpC2() {
		return DiffExpC2;
	}

	public void setDiffExpC2(String diffExpC2) {
		DiffExpC2 = diffExpC2;
	}

	public String getDiffExpC3() {
		return DiffExpC3;
	}

	public void setDiffExpC3(String diffExpC3) {
		DiffExpC3 = diffExpC3;
	}

	public String getDiffExpC4() {
		return DiffExpC4;
	}

	public void setDiffExpC4(String diffExpC4) {
		DiffExpC4 = diffExpC4;
	}

	public String getDiffExpC5() {
		return DiffExpC5;
	}

	public void setDiffExpC5(String diffExpC5) {
		DiffExpC5 = diffExpC5;
	}

	@Override
	public String toString() {
		return "MeterChangeMc [Id=" + Id + ", ServiceNo=" + ServiceNo + ", ReadingMonth=" + ReadingMonth
				+ ", ReadingYear=" + ReadingYear + ", ImpInitC1=" + ImpInitC1 + ", ImpInitC2=" + ImpInitC2
				+ ", ImpInitC3=" + ImpInitC3 + ", ImpInitC4=" + ImpInitC4 + ", ImpInitC5=" + ImpInitC5 + ", ImpFinC1="
				+ ImpFinC1 + ", ImpFinC2=" + ImpFinC2 + ", ImpFinC3=" + ImpFinC3 + ", ImpFinC4=" + ImpFinC4
				+ ", ImpFinC5=" + ImpFinC5 + ", ExpInitC1=" + ExpInitC1 + ", ExpInitC2=" + ExpInitC2 + ", ExpInitC3="
				+ ExpInitC3 + ", ExpInitC4=" + ExpInitC4 + ", ExpInitC5=" + ExpInitC5 + ", ExpFinC1=" + ExpFinC1
				+ ", ExpFinC2=" + ExpFinC2 + ", ExpFinC3=" + ExpFinC3 + ", ExpFinC4=" + ExpFinC4 + ", ExpFinC5="
				+ ExpFinC5 + ", NetImpC1=" + NetImpC1 + ", NetImpC2=" + NetImpC2 + ", NetImpC3=" + NetImpC3
				+ ", NetImpC4=" + NetImpC4 + ", NetImpC5=" + NetImpC5 + ", NetExpC1=" + NetExpC1 + ", NetExpC2="
				+ NetExpC2 + ", NetExpC3=" + NetExpC3 + ", NetExpC4=" + NetExpC4 + ", NetExpC5=" + NetExpC5
				+ ", Remarks=" + Remarks + ", ReadingType=" + ReadingType + ", DiffImpC1=" + DiffImpC1 + ", DiffImpC2="
				+ DiffImpC2 + ", DiffImpC3=" + DiffImpC3 + ", DiffImpC4=" + DiffImpC4 + ", DiffImpC5=" + DiffImpC5
				+ ", DiffExpC1=" + DiffExpC1 + ", DiffExpC2=" + DiffExpC2 + ", DiffExpC3=" + DiffExpC3 + ", DiffExpC4="
				+ DiffExpC4 + ", DiffExpC5=" + DiffExpC5 + ", getId()=" + getId() + ", getServiceNo()=" + getServiceNo()
				+ ", getReadingMonth()=" + getReadingMonth() + ", getReadingYear()=" + getReadingYear()
				+ ", getImpInitC1()=" + getImpInitC1() + ", getImpInitC2()=" + getImpInitC2() + ", getImpInitC3()="
				+ getImpInitC3() + ", getImpInitC4()=" + getImpInitC4() + ", getImpInitC5()=" + getImpInitC5()
				+ ", getImpFinC1()=" + getImpFinC1() + ", getImpFinC2()=" + getImpFinC2() + ", getImpFinC3()="
				+ getImpFinC3() + ", getImpFinC4()=" + getImpFinC4() + ", getImpFinC5()=" + getImpFinC5()
				+ ", getExpInitC1()=" + getExpInitC1() + ", getExpInitC2()=" + getExpInitC2() + ", getExpInitC3()="
				+ getExpInitC3() + ", getExpInitC4()=" + getExpInitC4() + ", getExpInitC5()=" + getExpInitC5()
				+ ", getExpFinC1()=" + getExpFinC1() + ", getExpFinC2()=" + getExpFinC2() + ", getExpFinC3()="
				+ getExpFinC3() + ", getExpFinC4()=" + getExpFinC4() + ", getExpFinC5()=" + getExpFinC5()
				+ ", getNetImpC1()=" + getNetImpC1() + ", getNetImpC2()=" + getNetImpC2() + ", getNetImpC3()="
				+ getNetImpC3() + ", getNetImpC4()=" + getNetImpC4() + ", getNetImpC5()=" + getNetImpC5()
				+ ", getNetExpC1()=" + getNetExpC1() + ", getNetExpC2()=" + getNetExpC2() + ", getNetExpC3()="
				+ getNetExpC3() + ", getNetExpC4()=" + getNetExpC4() + ", getNetExpC5()=" + getNetExpC5()
				+ ", getRemarks()=" + getRemarks() + ", getReadingType()=" + getReadingType() + ", getDiffImpC1()="
				+ getDiffImpC1() + ", getDiffImpC2()=" + getDiffImpC2() + ", getDiffImpC3()=" + getDiffImpC3()
				+ ", getDiffImpC4()=" + getDiffImpC4() + ", getDiffImpC5()=" + getDiffImpC5() + ", getDiffExpC1()="
				+ getDiffExpC1() + ", getDiffExpC2()=" + getDiffExpC2() + ", getDiffExpC3()=" + getDiffExpC3()
				+ ", getDiffExpC4()=" + getDiffExpC4() + ", getDiffExpC5()=" + getDiffExpC5() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

}
