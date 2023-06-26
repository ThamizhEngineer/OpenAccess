package com.ss.oa.integration.mdmsintegration.vo;

import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@Scope("prototype")
public class MdmsIntegration {
	
  @JsonProperty("id")  private String id;
  @JsonProperty("meterNumber") private String meterNumber;
  @JsonProperty("mF") private String mF;
  @JsonProperty("serviceNumber") private String serviceNumber;
  @JsonProperty("systemDate") private String systemDate;
  @JsonProperty("initialReadingDate") private String initialReadingDate;
  @JsonProperty("finalReadingDate") private String finalReadingDate;
  @JsonProperty("impInitS1") private String impInitS1;
  @JsonProperty("impInitS2") private String impInitS2;
  @JsonProperty("impInitS3") private String impInitS3;
  @JsonProperty("impInitS4") private String impInitS4;
  @JsonProperty("impInitS5") private String impInitS5;
  @JsonProperty("impFinalS1") private String impFinalS1;
  @JsonProperty("impFinalS2") private String impFinalS2;
  @JsonProperty("impFinalS3") private String impFinalS3;
  @JsonProperty("impFinalS4") private String impFinalS4;
  @JsonProperty("impFinalS5") private String impFinalS5;
  @JsonProperty("expInitS1") private String expInitS1;
  @JsonProperty("expInitS2") private String expInitS2;
  @JsonProperty("expInitS3") private String expInitS3;
  @JsonProperty("expInitS4") private String expInitS4;
  @JsonProperty("expInitS5") private String expInitS5;
  @JsonProperty("expFinalS1") private String expFinalS1;
  @JsonProperty("expFinalS2") private String expFinalS2;
  @JsonProperty("expFinalS3") private String expFinalS3;
  @JsonProperty("expFinalS4") private String expFinalS4;
  @JsonProperty("expFinalS5") private String expFinalS5;
  @JsonProperty("impKvahInit") private String impKvahInit;
  @JsonProperty("expKvahInit") private String expKvahInit;
  @JsonProperty("impKvahFinal") private String impKvahFinal;
  @JsonProperty("expKvahFinal") private String expKvahFinal;
  @JsonProperty("q1KvarhInit") private String q1KvarhInit;
  @JsonProperty("q2KvarhInit") private String q2KvarhInit;
  @JsonProperty("q3KvarhInit") private String q3KvarhInit;
  @JsonProperty("q4KvarhInit") private String q4KvarhInit;
  @JsonProperty("q1KvarhFinal") private String q1KvarhFinal;
  @JsonProperty("q2KvarhFinal") private String q2KvarhFinal;
  @JsonProperty("q3KvarhFinal") private String q3KvarhFinal;
  @JsonProperty("q4KvarhFinal") private String q4KvarhFinal;
  @JsonProperty("impKvarhInit") private String impKvarhInit;
  @JsonProperty("expKvarhInit") private String expKvarhInit;
  @JsonProperty("impKvarhFinal") private String impKvarhFinal;
  @JsonProperty("expKvarhFinal") private String expKvarhFinal;
  @JsonProperty("month") private String month;
  @JsonProperty("year") private String year;
  @JsonProperty("downloadStatus") private String downloadStatus;
	  
		public MdmsIntegration(){
			super();
		}

		public MdmsIntegration(String id, String meterNumber, String mF, String serviceNumber, String systemDate,
				String initialReadingDate, String finalReadingDate, String impInitS1, String impInitS2,
				String impInitS3, String impInitS4, String impInitS5, String impFinalS1, String impFinalS2,
				String impFinalS3, String impFinalS4, String impFinalS5, String expInitS1, String expInitS2,
				String expInitS3, String expInitS4, String expInitS5, String expFinalS1, String expFinalS2,
				String expFinalS3, String expFinalS4, String expFinalS5, String impKvahInit, String expKvahInit,
				String impKvahFinal, String expKvahFinal, String q1KvarhInit, String q2KvarhInit, String q3KvarhInit,
				String q4KvarhInit, String q1KvarhFinal, String q2KvarhFinal, String q3KvarhFinal, String q4KvarhFinal,
				String impKvarhInit, String expKvarhInit, String impKvarhFinal, String expKvarhFinal, String month,
				String year, String downloadStatus) {
			super();
			this.id = id;
			this.meterNumber = meterNumber;
			this.mF = mF;
			this.serviceNumber = serviceNumber;
			this.systemDate = systemDate;
			this.initialReadingDate = initialReadingDate;
			this.finalReadingDate = finalReadingDate;
			this.impInitS1 = impInitS1;
			this.impInitS2 = impInitS2;
			this.impInitS3 = impInitS3;
			this.impInitS4 = impInitS4;
			this.impInitS5 = impInitS5;
			this.impFinalS1 = impFinalS1;
			this.impFinalS2 = impFinalS2;
			this.impFinalS3 = impFinalS3;
			this.impFinalS4 = impFinalS4;
			this.impFinalS5 = impFinalS5;
			this.expInitS1 = expInitS1;
			this.expInitS2 = expInitS2;
			this.expInitS3 = expInitS3;
			this.expInitS4 = expInitS4;
			this.expInitS5 = expInitS5;
			this.expFinalS1 = expFinalS1;
			this.expFinalS2 = expFinalS2;
			this.expFinalS3 = expFinalS3;
			this.expFinalS4 = expFinalS4;
			this.expFinalS5 = expFinalS5;
			this.impKvahInit = impKvahInit;
			this.expKvahInit = expKvahInit;
			this.impKvahFinal = impKvahFinal;
			this.expKvahFinal = expKvahFinal;
			this.q1KvarhInit = q1KvarhInit;
			this.q2KvarhInit = q2KvarhInit;
			this.q3KvarhInit = q3KvarhInit;
			this.q4KvarhInit = q4KvarhInit;
			this.q1KvarhFinal = q1KvarhFinal;
			this.q2KvarhFinal = q2KvarhFinal;
			this.q3KvarhFinal = q3KvarhFinal;
			this.q4KvarhFinal = q4KvarhFinal;
			this.impKvarhInit = impKvarhInit;
			this.expKvarhInit = expKvarhInit;
			this.impKvarhFinal = impKvarhFinal;
			this.expKvarhFinal = expKvarhFinal;
			this.month = month;
			this.year = year;
			this.downloadStatus = downloadStatus;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getMeterNumber() {
			return meterNumber;
		}

		public void setMeterNumber(String meterNumber) {
			this.meterNumber = meterNumber;
		}

		public String getmF() {
			return mF;
		}

		public void setmF(String mF) {
			this.mF = mF;
		}

		public String getServiceNumber() {
			return serviceNumber;
		}

		public void setServiceNumber(String serviceNumber) {
			this.serviceNumber = serviceNumber;
		}

		public String getSystemDate() {
			return systemDate;
		}

		public void setSystemDate(String systemDate) {
			this.systemDate = systemDate;
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

		public String getImpInitS1() {
			return impInitS1;
		}

		public void setImpInitS1(String impInitS1) {
			this.impInitS1 = impInitS1;
		}

		public String getImpInitS2() {
			return impInitS2;
		}

		public void setImpInitS2(String impInitS2) {
			this.impInitS2 = impInitS2;
		}

		public String getImpInitS3() {
			return impInitS3;
		}

		public void setImpInitS3(String impInitS3) {
			this.impInitS3 = impInitS3;
		}

		public String getImpInitS4() {
			return impInitS4;
		}

		public void setImpInitS4(String impInitS4) {
			this.impInitS4 = impInitS4;
		}

		public String getImpInitS5() {
			return impInitS5;
		}

		public void setImpInitS5(String impInitS5) {
			this.impInitS5 = impInitS5;
		}

		public String getImpFinalS1() {
			return impFinalS1;
		}

		public void setImpFinalS1(String impFinalS1) {
			this.impFinalS1 = impFinalS1;
		}

		public String getImpFinalS2() {
			return impFinalS2;
		}

		public void setImpFinalS2(String impFinalS2) {
			this.impFinalS2 = impFinalS2;
		}

		public String getImpFinalS3() {
			return impFinalS3;
		}

		public void setImpFinalS3(String impFinalS3) {
			this.impFinalS3 = impFinalS3;
		}

		public String getImpFinalS4() {
			return impFinalS4;
		}

		public void setImpFinalS4(String impFinalS4) {
			this.impFinalS4 = impFinalS4;
		}

		public String getImpFinalS5() {
			return impFinalS5;
		}

		public void setImpFinalS5(String impFinalS5) {
			this.impFinalS5 = impFinalS5;
		}

		public String getExpInitS1() {
			return expInitS1;
		}

		public void setExpInitS1(String expInitS1) {
			this.expInitS1 = expInitS1;
		}

		public String getExpInitS2() {
			return expInitS2;
		}

		public void setExpInitS2(String expInitS2) {
			this.expInitS2 = expInitS2;
		}

		public String getExpInitS3() {
			return expInitS3;
		}

		public void setExpInitS3(String expInitS3) {
			this.expInitS3 = expInitS3;
		}

		public String getExpInitS4() {
			return expInitS4;
		}

		public void setExpInitS4(String expInitS4) {
			this.expInitS4 = expInitS4;
		}

		public String getExpInitS5() {
			return expInitS5;
		}

		public void setExpInitS5(String expInitS5) {
			this.expInitS5 = expInitS5;
		}

		public String getExpFinalS1() {
			return expFinalS1;
		}

		public void setExpFinalS1(String expFinalS1) {
			this.expFinalS1 = expFinalS1;
		}

		public String getExpFinalS2() {
			return expFinalS2;
		}

		public void setExpFinalS2(String expFinalS2) {
			this.expFinalS2 = expFinalS2;
		}

		public String getExpFinalS3() {
			return expFinalS3;
		}

		public void setExpFinalS3(String expFinalS3) {
			this.expFinalS3 = expFinalS3;
		}

		public String getExpFinalS4() {
			return expFinalS4;
		}

		public void setExpFinalS4(String expFinalS4) {
			this.expFinalS4 = expFinalS4;
		}

		public String getExpFinalS5() {
			return expFinalS5;
		}

		public void setExpFinalS5(String expFinalS5) {
			this.expFinalS5 = expFinalS5;
		}

		public String getImpKvahInit() {
			return impKvahInit;
		}

		public void setImpKvahInit(String impKvahInit) {
			this.impKvahInit = impKvahInit;
		}

		public String getExpKvahInit() {
			return expKvahInit;
		}

		public void setExpKvahInit(String expKvahInit) {
			this.expKvahInit = expKvahInit;
		}

		public String getImpKvahFinal() {
			return impKvahFinal;
		}

		public void setImpKvahFinal(String impKvahFinal) {
			this.impKvahFinal = impKvahFinal;
		}

		public String getExpKvahFinal() {
			return expKvahFinal;
		}

		public void setExpKvahFinal(String expKvahFinal) {
			this.expKvahFinal = expKvahFinal;
		}

		public String getQ1KvarhInit() {
			return q1KvarhInit;
		}

		public void setQ1KvarhInit(String q1KvarhInit) {
			this.q1KvarhInit = q1KvarhInit;
		}

		public String getQ2KvarhInit() {
			return q2KvarhInit;
		}

		public void setQ2KvarhInit(String q2KvarhInit) {
			this.q2KvarhInit = q2KvarhInit;
		}

		public String getQ3KvarhInit() {
			return q3KvarhInit;
		}

		public void setQ3KvarhInit(String q3KvarhInit) {
			this.q3KvarhInit = q3KvarhInit;
		}

		public String getQ4KvarhInit() {
			return q4KvarhInit;
		}

		public void setQ4KvarhInit(String q4KvarhInit) {
			this.q4KvarhInit = q4KvarhInit;
		}

		public String getQ1KvarhFinal() {
			return q1KvarhFinal;
		}

		public void setQ1KvarhFinal(String q1KvarhFinal) {
			this.q1KvarhFinal = q1KvarhFinal;
		}

		public String getQ2KvarhFinal() {
			return q2KvarhFinal;
		}

		public void setQ2KvarhFinal(String q2KvarhFinal) {
			this.q2KvarhFinal = q2KvarhFinal;
		}

		public String getQ3KvarhFinal() {
			return q3KvarhFinal;
		}

		public void setQ3KvarhFinal(String q3KvarhFinal) {
			this.q3KvarhFinal = q3KvarhFinal;
		}

		public String getQ4KvarhFinal() {
			return q4KvarhFinal;
		}

		public void setQ4KvarhFinal(String q4KvarhFinal) {
			this.q4KvarhFinal = q4KvarhFinal;
		}

		public String getImpKvarhInit() {
			return impKvarhInit;
		}

		public void setImpKvarhInit(String impKvarhInit) {
			this.impKvarhInit = impKvarhInit;
		}

		public String getExpKvarhInit() {
			return expKvarhInit;
		}

		public void setExpKvarhInit(String expKvarhInit) {
			this.expKvarhInit = expKvarhInit;
		}

		public String getImpKvarhFinal() {
			return impKvarhFinal;
		}

		public void setImpKvarhFinal(String impKvarhFinal) {
			this.impKvarhFinal = impKvarhFinal;
		}

		public String getExpKvarhFinal() {
			return expKvarhFinal;
		}

		public void setExpKvarhFinal(String expKvarhFinal) {
			this.expKvarhFinal = expKvarhFinal;
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

		public String getDownloadStatus() {
			return downloadStatus;
		}

		public void setDownloadStatus(String downloadStatus) {
			this.downloadStatus = downloadStatus;
		}

		@Override
		public String toString() {
			return "MdmsIntegration [id=" + id + ", meterNumber=" + meterNumber + ", mF=" + mF + ", serviceNumber="
					+ serviceNumber + ", systemDate=" + systemDate + ", initialReadingDate=" + initialReadingDate
					+ ", finalReadingDate=" + finalReadingDate + ", impInitS1=" + impInitS1 + ", impInitS2=" + impInitS2
					+ ", impInitS3=" + impInitS3 + ", impInitS4=" + impInitS4 + ", impInitS5=" + impInitS5
					+ ", impFinalS1=" + impFinalS1 + ", impFinalS2=" + impFinalS2 + ", impFinalS3=" + impFinalS3
					+ ", impFinalS4=" + impFinalS4 + ", impFinalS5=" + impFinalS5 + ", expInitS1=" + expInitS1
					+ ", expInitS2=" + expInitS2 + ", expInitS3=" + expInitS3 + ", expInitS4=" + expInitS4
					+ ", expInitS5=" + expInitS5 + ", expFinalS1=" + expFinalS1 + ", expFinalS2=" + expFinalS2
					+ ", expFinalS3=" + expFinalS3 + ", expFinalS4=" + expFinalS4 + ", expFinalS5=" + expFinalS5
					+ ", impKvahInit=" + impKvahInit + ", expKvahInit=" + expKvahInit + ", impKvahFinal=" + impKvahFinal
					+ ", expKvahFinal=" + expKvahFinal + ", q1KvarhInit=" + q1KvarhInit + ", q2KvarhInit=" + q2KvarhInit
					+ ", q3KvarhInit=" + q3KvarhInit + ", q4KvarhInit=" + q4KvarhInit + ", q1KvarhFinal=" + q1KvarhFinal
					+ ", q2KvarhFinal=" + q2KvarhFinal + ", q3KvarhFinal=" + q3KvarhFinal + ", q4KvarhFinal="
					+ q4KvarhFinal + ", impKvarhInit=" + impKvarhInit + ", expKvarhInit=" + expKvarhInit
					+ ", impKvarhFinal=" + impKvarhFinal + ", expKvarhFinal=" + expKvarhFinal + ", month=" + month
					+ ", year=" + year + ", downloadStatus=" + downloadStatus + "]";
		}


	
		 

}
