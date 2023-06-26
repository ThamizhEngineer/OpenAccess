package com.ss.oa.transaction.sldcNoc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ss.oa.common.OpenAccessException;
import com.ss.oa.transaction.vo.ExtSamastApplnApproval;
import com.ss.oa.transaction.vo.SldcNoc;
import com.ss.oa.transaction.vo.SldcNocLine;
import com.ss.oashared.common.CommonUtils;
import com.ss.oashared.model.VCompanyService;

import oracle.jdbc.OracleTypes;

@RestController
@RequestMapping("/transaction/sdlc-noc")
public class SldcNocService {
	
	@Resource
	private DataSource dataSource;
	 
	Connection conn = null;
	CallableStatement stmt = null;
	
	@Autowired
	SldcNocRepo sldcNocRepo;
	
	@Autowired
	CommonUtils commonUtils;
	
	@Autowired
	SldcLineRepo sldcRepo;
	
	@Autowired
	VCompanyServicerepo nameRepo;
	
	@Autowired
	ExtSamastApplnApprovalRepo applnRepo;

	
	@GetMapping
	public Iterable<SldcNoc> getAllSldcNoc() throws OpenAccessException{
		return sldcNocRepo.findAll();
	}
	
	@GetMapping("/search")
	public List<SldcNoc> searchSldcNoc(@RequestParam(value = "compServNo",required = false) String compServNo,
			@RequestParam(value = "edcId",required = false) String edcId,
			@RequestParam(value = "cntrctDemand",required = false) String cntrctDemand,
			@RequestParam(value = "nocCode",required = false) String nocCode,
			@RequestParam(value = "appliedNo",required = false) String appliedNo,
			@RequestParam(value = "appliedDt",required = false) String appliedDt,
			@RequestParam(value = "status",required = false) String status,
			@RequestParam(value = "nocPurpose",required = false) String nocPurpose){
		
		return sldcNocRepo.searchSldcNoc(compServNo, edcId, cntrctDemand, nocCode, appliedNo, appliedDt, status,nocPurpose);
	}

	@GetMapping("/purpose/{purpose}")
	public List<SldcNoc> getAllSldcNocByPurpose(@PathVariable(value="purpose") String purpose) throws OpenAccessException{
		return sldcNocRepo.findByNocPurpose(purpose);
	}
	
	@GetMapping("/{id}")
	public Optional<SldcNoc> getSldcNocById(@PathVariable(value="id") String id) throws OpenAccessException{
		return sldcNocRepo.findById(id);
	}
//	@GetMapping("/{ids}")
//	public Optional<SldcNocLine> gettSldcNocId(@PathVariable(value="tSldcNocId") String tSldcNocId) throws OpenAccessException{
//		return sldcNocRepo.findByNocId(tSldcNocId);
//	}
	@GetMapping("/{id}/_internal")
	public Optional<SldcNoc> getSldcNocByIdForInternal(@PathVariable(value="id") String id) throws OpenAccessException{
		return sldcNocRepo.findById(id);
	}
	
	@PostMapping()
	public SldcNoc createSldcNocById(@RequestBody SldcNoc sldcNoc) throws OpenAccessException{
//		sldcNoc.setId(commonUtils.generateId());
		
//		if(!sldcNoc.getSldcNocLine().isEmpty()) {
//			for(SldcNocLine sldcNocLine:sldcNoc.getSldcNocLine()) {
//				sldcNocLine.setId(commonUtils.generateId());
//				sldcNocLine.settSldcNocId(sldcNoc.getId());
//			}
//		}
//				
		return sldcNocRepo.save(sldcNoc);
	}
	
	@PatchMapping("/{id}")
	public SldcNoc updateSldcNocById(@PathVariable(value="id") String id,@RequestBody SldcNoc sldcNoc) throws OpenAccessException{
		
		for(SldcNocLine sldcNocLine:sldcNoc.getSldcNocLine()) {
			System.out.println(sldcNocLine.getId()==null);
			if(sldcNocLine.getId()==null) {
				sldcNocLine.setId(commonUtils.generateId());
				sldcNocLine.settSldcNocId(sldcNoc.getId());
				sldcNocLine.setModifiedDt(LocalDateTime.now());
			}
		}		
		sldcNoc.setModifiedDt(LocalDateTime.now());
		if(sldcNoc.getStatus().equalsIgnoreCase("EDC-APPROVED")) {
			sldcNoc.setEdcApprovalDate(LocalDate.now());
		}
		sldcNoc = sldcNocRepo.save(sldcNoc);
		updateExtSamastAppln(sldcNoc);
		return sldcNoc;
	}

	private void updateExtSamastAppln(SldcNoc sldcNoc) {
		if (sldcNoc.getStatus().equalsIgnoreCase("DIR-APPROVED")
				|| sldcNoc.getStatus().equalsIgnoreCase("DIR-REJECTED")) {
			String res = "";
			String ssLossFunction = "{call FEEDER_LOSS.START_IMPORT(?,?,?)}";
			try {
				conn = dataSource.getConnection();
				stmt = conn.prepareCall(ssLossFunction);
				stmt.setString(1, sldcNoc.getId());
				stmt.registerOutParameter(2, OracleTypes.VARCHAR);
				stmt.registerOutParameter(3, OracleTypes.VARCHAR);
				stmt.execute();
				res = stmt.getString(2) +"--"+stmt.getString(3);
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {

				try {
					if ((stmt != null) && (!stmt.isClosed())) {
						stmt.close();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					if ((conn != null) && (!conn.isClosed())) {
						conn.close();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		System.out.println("sldc-noc final updation status -test - "+res);
		}
	}

	
	@GetMapping("/getSldc")
	public List<SldcNoc> getSldcByMonthAndYear(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
		return sldcNocRepo.findByAppliedDtBetween(date.withDayOfMonth(1), date.withDayOfMonth(date.getMonth().length(date.isLeapYear())));
	}
	
	@GetMapping("/report")
	public List<SldcNocLine> searchApprovalNoc(@RequestParam(value = "quantum",required = false) String quantum,
			@RequestParam(value = "tSldcNocId",required = false) String tSldcNocId,
			@RequestParam(value = "commitType",required = false) String commitType,
			@RequestParam(value = "flowType",required = false) String flowType,
			@RequestParam(value = "isExisting",required = false) String isExisting
			){
		return sldcNocRepo.searchApprovalNoc(quantum, tSldcNocId, commitType, flowType,isExisting);
	}
	@GetMapping("/compname/{mCompServNumber}")
	public VCompanyService getcompName(@PathVariable(value="mCompServNumber") String mCompServNumber)throws OpenAccessException{
		return nameRepo.findByMCompServNumber(mCompServNumber);
	}
	@GetMapping("/getHt/{eobHtConsumerNumber}")
	public List<ExtSamastApplnApproval> getName(@PathVariable(value="eobHtConsumerNumber") String eobHtConsumerNumber)throws OpenAccessException{
		return applnRepo.findByEobHtConsumerNumber(eobHtConsumerNumber);
	}
	@GetMapping("/getGc/{eosGcApprovalNumber}")
	public List<ExtSamastApplnApproval> getNameEos(@PathVariable(value="eosGcApprovalNumber") String eosGcApprovalNumber)throws OpenAccessException{
		return applnRepo.findByEosGcApprovalNumber(eosGcApprovalNumber);
	}
	@GetMapping("/revised")
	public List<ExtSamastApplnApproval> getRevised(@RequestParam(value="eosGcApprovalNumber") String eosGcApprovalNumber,@RequestParam(value="appType") String appType,
			@RequestParam(value="approvalNo",required = false) String approvalNo){
		return applnRepo.findByRevisedData(eosGcApprovalNumber,appType,"_R1");
	}
	
}
