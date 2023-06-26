package com.ss.oa.transaction.gridConnectivity;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;

import com.ss.oa.master.vo.Generator;
import com.ss.oa.transaction.vo.ApplicationStatus;
import com.ss.oa.transaction.vo.CaptiveUserContribution;
import com.ss.oa.transaction.vo.DocCheckListItem;
import com.ss.oa.transaction.vo.EquityShareVotingRights;
import com.ss.oa.transaction.vo.GridConnectivity;
import com.ss.oa.transaction.vo.Loan;
import com.ss.oa.transaction.vo.QuantumAllocation;
import com.ss.oa.transaction.vo.Transformer;

@Scope("prototype")
public interface GridConnectivityDao {
	
	public abstract List<GridConnectivity> getAllGcs(Map<String, String> criteria);

	public abstract GridConnectivity getGcById(String id);

	public abstract String addGc(GridConnectivity gc);

	public abstract String updateGc(String id, GridConnectivity gc);

	public abstract String addGenUnits(Generator genUnits);

	public abstract String updateGenUnits(Generator genUnits);

	public abstract String addTransformers(Transformer transformers);

	public abstract String addQuantumAllocation(QuantumAllocation quantumAllocation);

	public abstract String addApplicationStatus(ApplicationStatus applicationStatus);

	public abstract String addLoan(Loan idLoans);

	public abstract String addCaptiveUserContribution(CaptiveUserContribution captiveUserContribution);

	public abstract String addEquityShareVotingRights(EquityShareVotingRights equityShareVotingRights);

	public abstract String addDocCheckListItem(DocCheckListItem checkList);

	public abstract String updateTransformers(Transformer transformers);

	public abstract String updateQuantumAllocation( QuantumAllocation quantumAllocation);

	public abstract String updateApplicationStatus( ApplicationStatus applicationStatus);

	public abstract String updateLoan( Loan idLoans);

	public abstract String updateCaptiveUserContribution(CaptiveUserContribution captiveUserContribution);

	public abstract String updateEquityShareVotingRights(EquityShareVotingRights equityShareVotingRights);

	public abstract String updateDocCheckListItem(DocCheckListItem checkList);

}
