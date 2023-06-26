package com.ss.oa.transaction.cs;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;

import com.ss.oa.transaction.vo.Cs;
import com.ss.oa.transaction.vo.CsCaptiveUserContribution;
import com.ss.oa.transaction.vo.CsEquityShareVotingRight;
import com.ss.oa.transaction.vo.CsLoan;
import com.ss.oa.transaction.vo.CsQuantumAllocation;

@Scope("prototype")
public interface CsDao {
	
	public abstract List<Cs> getAllCs(Map<String,String> criteria);
	public abstract Cs getCsById(String id);
	public abstract String addCs(Cs cs);
	public abstract String updateCs(String id,Cs cs);
	public abstract String addCsCaptiveUserContribution(CsCaptiveUserContribution csCaptiveUserContribution);
	public abstract String updateCsCaptiveUserContribution(CsCaptiveUserContribution csCaptiveUserContribution);
	public abstract String addCsEquityShareVotingRight(CsEquityShareVotingRight csEquityShareVotingRight);
	public abstract String updateCsEquityShareVotingRight(CsEquityShareVotingRight csEquityShareVotingRight);
	public abstract String addCsQuantumAllocation(CsQuantumAllocation csQuantumAllocation);
	public abstract String updateCsQuantumAllocation(CsQuantumAllocation csQuantumAllocation);
	public abstract String addCsLoan(CsLoan csLoan);
	public abstract String updateCsLoan(CsLoan csLoan);
}
