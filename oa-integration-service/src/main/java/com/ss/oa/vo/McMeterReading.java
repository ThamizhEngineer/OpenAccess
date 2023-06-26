package com.ss.oa.vo;

public class McMeterReading {

	private String id;
	private String oldMeterNumber;
	private String newMeterNumber;
	private String status;
	private String meterChangeDate;
	private String initialReadingDate;
	private String finalReadingDate;
	private String month;
	private String year;
	private String oldImpKwhToD1;
	private String oldImpKwhToD2;
	private String oldImpKwhToD3;
	private String oldImpKwhToD4;
	private String oldImpKwhToD5;
	private String oldExpKwhToD1;
	private String oldExpKwhToD2;
	private String oldExpKwhToD3;
	private String oldExpKwhToD4;
	private String oldExpKwhToD5;
	private String oldImpKvah;
	private String oldExpKvah;
	private String oldQ1Kvarh;
	private String oldQ2Kvarh;
	private String oldQ3Kvarh;
	private String oldQ4Kvarh;
	private String oldMf;
	private String newImpKwhToD1;
	private String newImpKwhToD2;
	private String newImpKwhToD3;
	private String newImpKwhToD4;
	private String newImpKwhToD5;
	private String newExpKwhToD1;
	private String newExpKwhToD2;
	private String newExpKwhToD3;
	private String newExpKwhToD4;
	private String newExpKwhToD5;
	private String newImpKvah;
	private String newExpKvah;
	private String newQ1Kvarh;
	private String newQ2Kvarh;
	private String newQ3Kvarh;
	private String newQ4Kvarh;
	private String newMf;
	private String modifyDate;

	  
	public McMeterReading(){
		super();
	}


	public McMeterReading(String id, String oldMeterNumber, String newMeterNumber, String status,
			String meterChangeDate, String initialReadingDate, String finalReadingDate, String month, String year,
			String oldImpKwhToD1, String oldImpKwhToD2, String oldImpKwhToD3, String oldImpKwhToD4,
			String oldImpKwhToD5, String oldExpKwhToD1, String oldExpKwhToD2, String oldExpKwhToD3,
			String oldExpKwhToD4, String oldExpKwhToD5, String oldImpKvah, String oldExpKvah, String oldQ1Kvarh,
			String oldQ2Kvarh, String oldQ3Kvarh, String oldQ4Kvarh, String oldMf, String newImpKwhToD1,
			String newImpKwhToD2, String newImpKwhToD3, String newImpKwhToD4, String newImpKwhToD5,
			String newExpKwhToD1, String newExpKwhToD2, String newExpKwhToD3, String newExpKwhToD4,
			String newExpKwhToD5, String newImpKvah, String newExpKvah, String newQ1Kvarh, String newQ2Kvarh,
			String newQ3Kvarh, String newQ4Kvarh, String newMf, String modifyDate) {
		super();
		this.id = id;
		this.oldMeterNumber = oldMeterNumber;
		this.newMeterNumber = newMeterNumber;
		this.status = status;
		this.meterChangeDate = meterChangeDate;
		this.initialReadingDate = initialReadingDate;
		this.finalReadingDate = finalReadingDate;
		this.month = month;
		this.year = year;
		this.oldImpKwhToD1 = oldImpKwhToD1;
		this.oldImpKwhToD2 = oldImpKwhToD2;
		this.oldImpKwhToD3 = oldImpKwhToD3;
		this.oldImpKwhToD4 = oldImpKwhToD4;
		this.oldImpKwhToD5 = oldImpKwhToD5;
		this.oldExpKwhToD1 = oldExpKwhToD1;
		this.oldExpKwhToD2 = oldExpKwhToD2;
		this.oldExpKwhToD3 = oldExpKwhToD3;
		this.oldExpKwhToD4 = oldExpKwhToD4;
		this.oldExpKwhToD5 = oldExpKwhToD5;
		this.oldImpKvah = oldImpKvah;
		this.oldExpKvah = oldExpKvah;
		this.oldQ1Kvarh = oldQ1Kvarh;
		this.oldQ2Kvarh = oldQ2Kvarh;
		this.oldQ3Kvarh = oldQ3Kvarh;
		this.oldQ4Kvarh = oldQ4Kvarh;
		this.oldMf = oldMf;
		this.newImpKwhToD1 = newImpKwhToD1;
		this.newImpKwhToD2 = newImpKwhToD2;
		this.newImpKwhToD3 = newImpKwhToD3;
		this.newImpKwhToD4 = newImpKwhToD4;
		this.newImpKwhToD5 = newImpKwhToD5;
		this.newExpKwhToD1 = newExpKwhToD1;
		this.newExpKwhToD2 = newExpKwhToD2;
		this.newExpKwhToD3 = newExpKwhToD3;
		this.newExpKwhToD4 = newExpKwhToD4;
		this.newExpKwhToD5 = newExpKwhToD5;
		this.newImpKvah = newImpKvah;
		this.newExpKvah = newExpKvah;
		this.newQ1Kvarh = newQ1Kvarh;
		this.newQ2Kvarh = newQ2Kvarh;
		this.newQ3Kvarh = newQ3Kvarh;
		this.newQ4Kvarh = newQ4Kvarh;
		this.newMf = newMf;
		this.modifyDate = modifyDate;
	}


	@Override
	public String toString() {
		return "McMeterReading [id=" + id + ", oldMeterNumber=" + oldMeterNumber + ", newMeterNumber=" + newMeterNumber
				+ ", status=" + status + ", meterChangeDate=" + meterChangeDate + ", initialReadingDate="
				+ initialReadingDate + ", finalReadingDate=" + finalReadingDate + ", month=" + month + ", year=" + year
				+ ", oldImpKwhToD1=" + oldImpKwhToD1 + ", oldImpKwhToD2=" + oldImpKwhToD2 + ", oldImpKwhToD3="
				+ oldImpKwhToD3 + ", oldImpKwhToD4=" + oldImpKwhToD4 + ", oldImpKwhToD5=" + oldImpKwhToD5
				+ ", oldExpKwhToD1=" + oldExpKwhToD1 + ", oldExpKwhToD2=" + oldExpKwhToD2 + ", oldExpKwhToD3="
				+ oldExpKwhToD3 + ", oldExpKwhToD4=" + oldExpKwhToD4 + ", oldExpKwhToD5=" + oldExpKwhToD5
				+ ", oldImpKvah=" + oldImpKvah + ", oldExpKvah=" + oldExpKvah + ", oldQ1Kvarh=" + oldQ1Kvarh
				+ ", oldQ2Kvarh=" + oldQ2Kvarh + ", oldQ3Kvarh=" + oldQ3Kvarh + ", oldQ4Kvarh=" + oldQ4Kvarh
				+ ", oldMf=" + oldMf + ", newImpKwhToD1=" + newImpKwhToD1 + ", newImpKwhToD2=" + newImpKwhToD2
				+ ", newImpKwhToD3=" + newImpKwhToD3 + ", newImpKwhToD4=" + newImpKwhToD4 + ", newImpKwhToD5="
				+ newImpKwhToD5 + ", newExpKwhToD1=" + newExpKwhToD1 + ", newExpKwhToD2=" + newExpKwhToD2
				+ ", newExpKwhToD3=" + newExpKwhToD3 + ", newExpKwhToD4=" + newExpKwhToD4 + ", newExpKwhToD5="
				+ newExpKwhToD5 + ", newImpKvah=" + newImpKvah + ", newExpKvah=" + newExpKvah + ", newQ1Kvarh="
				+ newQ1Kvarh + ", newQ2Kvarh=" + newQ2Kvarh + ", newQ3Kvarh=" + newQ3Kvarh + ", newQ4Kvarh="
				+ newQ4Kvarh + ", newMf=" + newMf + ", modifyDate=" + modifyDate + "]";
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getOldMeterNumber() {
		return oldMeterNumber;
	}


	public void setOldMeterNumber(String oldMeterNumber) {
		this.oldMeterNumber = oldMeterNumber;
	}


	public String getNewMeterNumber() {
		return newMeterNumber;
	}


	public void setNewMeterNumber(String newMeterNumber) {
		this.newMeterNumber = newMeterNumber;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getMeterChangeDate() {
		return meterChangeDate;
	}


	public void setMeterChangeDate(String meterChangeDate) {
		this.meterChangeDate = meterChangeDate;
	}


	public String getInitialReadingDate() {
		return initialReadingDate;
	}


	public void setInitialReadingDate(String initialReadingDate) {
		this.initialReadingDate = initialReadingDate;
	}


	public String getFinalReadingDate() {
		return finalReadingDate;
	}


	public void setFinalReadingDate(String finalReadingDate) {
		this.finalReadingDate = finalReadingDate;
	}


	public String getMonth() {
		return month;
	}


	public void setMonth(String month) {
		this.month = month;
	}


	public String getYear() {
		return year;
	}


	public void setYear(String year) {
		this.year = year;
	}


	public String getOldImpKwhToD1() {
		return oldImpKwhToD1;
	}


	public void setOldImpKwhToD1(String oldImpKwhToD1) {
		this.oldImpKwhToD1 = oldImpKwhToD1;
	}


	public String getOldImpKwhToD2() {
		return oldImpKwhToD2;
	}


	public void setOldImpKwhToD2(String oldImpKwhToD2) {
		this.oldImpKwhToD2 = oldImpKwhToD2;
	}


	public String getOldImpKwhToD3() {
		return oldImpKwhToD3;
	}


	public void setOldImpKwhToD3(String oldImpKwhToD3) {
		this.oldImpKwhToD3 = oldImpKwhToD3;
	}


	public String getOldImpKwhToD4() {
		return oldImpKwhToD4;
	}


	public void setOldImpKwhToD4(String oldImpKwhToD4) {
		this.oldImpKwhToD4 = oldImpKwhToD4;
	}


	public String getOldImpKwhToD5() {
		return oldImpKwhToD5;
	}


	public void setOldImpKwhToD5(String oldImpKwhToD5) {
		this.oldImpKwhToD5 = oldImpKwhToD5;
	}


	public String getOldExpKwhToD1() {
		return oldExpKwhToD1;
	}


	public void setOldExpKwhToD1(String oldExpKwhToD1) {
		this.oldExpKwhToD1 = oldExpKwhToD1;
	}


	public String getOldExpKwhToD2() {
		return oldExpKwhToD2;
	}


	public void setOldExpKwhToD2(String oldExpKwhToD2) {
		this.oldExpKwhToD2 = oldExpKwhToD2;
	}


	public String getOldExpKwhToD3() {
		return oldExpKwhToD3;
	}


	public void setOldExpKwhToD3(String oldExpKwhToD3) {
		this.oldExpKwhToD3 = oldExpKwhToD3;
	}


	public String getOldExpKwhToD4() {
		return oldExpKwhToD4;
	}


	public void setOldExpKwhToD4(String oldExpKwhToD4) {
		this.oldExpKwhToD4 = oldExpKwhToD4;
	}


	public String getOldExpKwhToD5() {
		return oldExpKwhToD5;
	}


	public void setOldExpKwhToD5(String oldExpKwhToD5) {
		this.oldExpKwhToD5 = oldExpKwhToD5;
	}


	public String getOldImpKvah() {
		return oldImpKvah;
	}


	public void setOldImpKvah(String oldImpKvah) {
		this.oldImpKvah = oldImpKvah;
	}


	public String getOldExpKvah() {
		return oldExpKvah;
	}


	public void setOldExpKvah(String oldExpKvah) {
		this.oldExpKvah = oldExpKvah;
	}


	public String getOldQ1Kvarh() {
		return oldQ1Kvarh;
	}


	public void setOldQ1Kvarh(String oldQ1Kvarh) {
		this.oldQ1Kvarh = oldQ1Kvarh;
	}


	public String getOldQ2Kvarh() {
		return oldQ2Kvarh;
	}


	public void setOldQ2Kvarh(String oldQ2Kvarh) {
		this.oldQ2Kvarh = oldQ2Kvarh;
	}


	public String getOldQ3Kvarh() {
		return oldQ3Kvarh;
	}


	public void setOldQ3Kvarh(String oldQ3Kvarh) {
		this.oldQ3Kvarh = oldQ3Kvarh;
	}


	public String getOldQ4Kvarh() {
		return oldQ4Kvarh;
	}


	public void setOldQ4Kvarh(String oldQ4Kvarh) {
		this.oldQ4Kvarh = oldQ4Kvarh;
	}


	public String getOldMf() {
		return oldMf;
	}


	public void setOldMf(String oldMf) {
		this.oldMf = oldMf;
	}


	public String getNewImpKwhToD1() {
		return newImpKwhToD1;
	}


	public void setNewImpKwhToD1(String newImpKwhToD1) {
		this.newImpKwhToD1 = newImpKwhToD1;
	}


	public String getNewImpKwhToD2() {
		return newImpKwhToD2;
	}


	public void setNewImpKwhToD2(String newImpKwhToD2) {
		this.newImpKwhToD2 = newImpKwhToD2;
	}


	public String getNewImpKwhToD3() {
		return newImpKwhToD3;
	}


	public void setNewImpKwhToD3(String newImpKwhToD3) {
		this.newImpKwhToD3 = newImpKwhToD3;
	}


	public String getNewImpKwhToD4() {
		return newImpKwhToD4;
	}


	public void setNewImpKwhToD4(String newImpKwhToD4) {
		this.newImpKwhToD4 = newImpKwhToD4;
	}


	public String getNewImpKwhToD5() {
		return newImpKwhToD5;
	}


	public void setNewImpKwhToD5(String newImpKwhToD5) {
		this.newImpKwhToD5 = newImpKwhToD5;
	}


	public String getNewExpKwhToD1() {
		return newExpKwhToD1;
	}


	public void setNewExpKwhToD1(String newExpKwhToD1) {
		this.newExpKwhToD1 = newExpKwhToD1;
	}


	public String getNewExpKwhToD2() {
		return newExpKwhToD2;
	}


	public void setNewExpKwhToD2(String newExpKwhToD2) {
		this.newExpKwhToD2 = newExpKwhToD2;
	}


	public String getNewExpKwhToD3() {
		return newExpKwhToD3;
	}


	public void setNewExpKwhToD3(String newExpKwhToD3) {
		this.newExpKwhToD3 = newExpKwhToD3;
	}


	public String getNewExpKwhToD4() {
		return newExpKwhToD4;
	}


	public void setNewExpKwhToD4(String newExpKwhToD4) {
		this.newExpKwhToD4 = newExpKwhToD4;
	}


	public String getNewExpKwhToD5() {
		return newExpKwhToD5;
	}


	public void setNewExpKwhToD5(String newExpKwhToD5) {
		this.newExpKwhToD5 = newExpKwhToD5;
	}


	public String getNewImpKvah() {
		return newImpKvah;
	}


	public void setNewImpKvah(String newImpKvah) {
		this.newImpKvah = newImpKvah;
	}


	public String getNewExpKvah() {
		return newExpKvah;
	}


	public void setNewExpKvah(String newExpKvah) {
		this.newExpKvah = newExpKvah;
	}


	public String getNewQ1Kvarh() {
		return newQ1Kvarh;
	}


	public void setNewQ1Kvarh(String newQ1Kvarh) {
		this.newQ1Kvarh = newQ1Kvarh;
	}


	public String getNewQ2Kvarh() {
		return newQ2Kvarh;
	}


	public void setNewQ2Kvarh(String newQ2Kvarh) {
		this.newQ2Kvarh = newQ2Kvarh;
	}


	public String getNewQ3Kvarh() {
		return newQ3Kvarh;
	}


	public void setNewQ3Kvarh(String newQ3Kvarh) {
		this.newQ3Kvarh = newQ3Kvarh;
	}


	public String getNewQ4Kvarh() {
		return newQ4Kvarh;
	}


	public void setNewQ4Kvarh(String newQ4Kvarh) {
		this.newQ4Kvarh = newQ4Kvarh;
	}


	public String getNewMf() {
		return newMf;
	}


	public void setNewMf(String newMf) {
		this.newMf = newMf;
	}


	public String getModifyDate() {
		return modifyDate;
	}


	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}

}
