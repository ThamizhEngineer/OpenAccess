package com.ss.oa.transaction.cs;

import java.util.List;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ss.oa.transaction.vo.Cs;
import com.ss.oa.transaction.vo.CsCaptiveUserContribution;
import com.ss.oa.transaction.vo.CsEquityShareVotingRight;
import com.ss.oa.transaction.vo.CsLoan;
import com.ss.oa.transaction.vo.CsQuantumAllocation;

@Scope("prototype")
@Component
public class CsService {
	
	@Autowired
	CsDao dao;
	
	String csId="";

	public List<Cs> getAllCs(Map<String, String> criteria) {
	
		return dao.getAllCs(criteria);
	}


	public Cs getCsById(String id) {
	
		return dao.getCsById(id);
	}

	public String addCs(Cs cs) {
		cs.setStatusCode("CREATED");
		csId = dao.addCs(cs);
		System.out.println(cs);
		if(cs.getCsCaptiveUserContributions()!=null) {
			for(CsCaptiveUserContribution csCaptiveUserContribution : cs.getCsCaptiveUserContributions()) {
				csCaptiveUserContribution.setCsId(csId);
				dao.addCsCaptiveUserContribution(csCaptiveUserContribution);
			}
		}
		if(cs.getCsEquityShareVotingRights()!=null) {
			for(CsEquityShareVotingRight csEquityShareVotingRight : cs.getCsEquityShareVotingRights()) {
				csEquityShareVotingRight.setCsId(csId);
				dao.addCsEquityShareVotingRight(csEquityShareVotingRight);
			}
		}
		if(cs.getCsQuantumAllocations()!=null) {
			for(CsQuantumAllocation csQuantumAllocation : cs.getCsQuantumAllocations()) {
				csQuantumAllocation.setCsId(csId);
				dao.addCsQuantumAllocation(csQuantumAllocation);
			}
		}
		if(cs.getCsLoans()!=null) {
			for(CsLoan csLoan : cs.getCsLoans()) {
				csLoan.setCsId(csId);
				dao.addCsLoan(csLoan);
			}
		}
		return csId;
	}


	public String updateCs(String id, Cs cs) {
		csId=dao.updateCs(id, cs);
		if(cs.getCsCaptiveUserContributions()!=null) {
			for(CsCaptiveUserContribution csCaptiveUserContribution : cs.getCsCaptiveUserContributions()) {
				if(csCaptiveUserContribution.getId()!=null) {
					dao.updateCsCaptiveUserContribution(csCaptiveUserContribution);
				}else {
					csCaptiveUserContribution.setCsId(csId);
					dao.addCsCaptiveUserContribution(csCaptiveUserContribution);
				}
				
			}
		}
		if(cs.getCsEquityShareVotingRights()!=null) {
			for(CsEquityShareVotingRight csEquityShareVotingRight : cs.getCsEquityShareVotingRights()) {				
				if(csEquityShareVotingRight.getId()!=null) {
					dao.updateCsEquityShareVotingRight(csEquityShareVotingRight);
				}else {
					csEquityShareVotingRight.setCsId(csId);
					dao.addCsEquityShareVotingRight(csEquityShareVotingRight);
				}
			}
		}
		if(cs.getCsQuantumAllocations()!=null) {
			for(CsQuantumAllocation csQuantumAllocation : cs.getCsQuantumAllocations()) {
				if(csQuantumAllocation.getId()!=null) {
					dao.updateCsQuantumAllocation(csQuantumAllocation);
				}else {
					csQuantumAllocation.setCsId(csId);
					dao.addCsQuantumAllocation(csQuantumAllocation);
				}
			}
		}
		if(cs.getCsLoans()!=null) {
			for(CsLoan csLoan : cs.getCsLoans()) {
				if(csLoan.getId()!=null) {
					dao.updateCsLoan(csLoan);
				}else {
					csLoan.setCsId(csId);
					dao.addCsLoan(csLoan);
				}
			}
		}
		return csId;
	}
	

	public String completeCs(String id, Cs cs) {
		cs.setStatusCode("COMPLETED");
		csId=dao.updateCs(id, cs);
		if(cs.getCsCaptiveUserContributions()!=null) {
			for(CsCaptiveUserContribution csCaptiveUserContribution : cs.getCsCaptiveUserContributions()) {
				if(csCaptiveUserContribution.getId()!=null) {
					dao.updateCsCaptiveUserContribution(csCaptiveUserContribution);
				}else {
					csCaptiveUserContribution.setCsId(csId);
					dao.addCsCaptiveUserContribution(csCaptiveUserContribution);
				}
				
			}
		}
		if(cs.getCsEquityShareVotingRights()!=null) {
			for(CsEquityShareVotingRight csEquityShareVotingRight : cs.getCsEquityShareVotingRights()) {				
				if(csEquityShareVotingRight.getId()!=null) {
					dao.updateCsEquityShareVotingRight(csEquityShareVotingRight);
				}else {
					csEquityShareVotingRight.setCsId(csId);
					dao.addCsEquityShareVotingRight(csEquityShareVotingRight);
				}
			}
		}
		if(cs.getCsQuantumAllocations()!=null) {
			for(CsQuantumAllocation csQuantumAllocation : cs.getCsQuantumAllocations()) {
				if(csQuantumAllocation.getId()!=null) {
					dao.updateCsQuantumAllocation(csQuantumAllocation);
				}else {
					csQuantumAllocation.setCsId(csId);
					dao.addCsQuantumAllocation(csQuantumAllocation);
				}
			}
		}
		if(cs.getCsLoans()!=null) {
			for(CsLoan csLoan : cs.getCsLoans()) {
				if(csLoan.getId()!=null) {
					dao.updateCsLoan(csLoan);
				}else {
					csLoan.setCsId(csId);
					dao.addCsLoan(csLoan);
				}
			}
		}
		return csId;
	}
	
	
}
