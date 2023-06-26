package com.ss.oa.transaction.agreement;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;

import com.ss.oa.transaction.vo.Agreement;
import com.ss.oa.transaction.vo.AgreementLine;

@Scope("prototype")
public interface AgreementDao {
	
	public abstract List<Agreement> getAllAgreements(Map<String,String> criteria);
	public abstract Agreement getAgreementById(String id);
	public abstract String addAgreement(Agreement agreement);
	public abstract String updateAgreement(String id,Agreement agreement);
	public abstract String addAgreementLine(AgreementLine agreementLine);
	public abstract String updateAgreementLine(AgreementLine agreementLine);
}
