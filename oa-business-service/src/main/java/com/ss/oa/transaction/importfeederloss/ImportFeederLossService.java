package com.ss.oa.transaction.importfeederloss;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ss.oa.common.OpenAccessException;
import com.ss.oa.transaction.feederloss.FeederLossRepo;
import com.ss.oa.transaction.vo.FeederLoss;
import com.ss.oa.transaction.vo.ImportFeederLoss;
import com.ss.oa.transaction.vo.SubstationLoss;
import com.ss.oashared.common.CommonUtils;

import oracle.jdbc.OracleTypes;

@Scope("prototype")
@RestController
@RequestMapping("/transaction/import-feeder-loss")
public class ImportFeederLossService {
	
	@Resource
	private DataSource dataSource;
	@Autowired
	private CommonUtils commonUtils;
	
	@Autowired
	ImportFeederLossRepo importFeederLossRepo;
	
	@Autowired
	FeederLossRepo feederLossRepo;
	
	Connection conn = null;
	CallableStatement stmt = null;
	Connection conn1 = null;
	CallableStatement stmt1 = null;

	@CrossOrigin(origins = "*")
	@GetMapping("/findAll")
    public Iterable<ImportFeederLoss> getAllImportFeederLoss(){
			return importFeederLossRepo.findAll();   
		}
	
	@CrossOrigin(origins="*")
	@GetMapping("/{id}")
	public ImportFeederLoss getimportFeederLossById(@PathVariable(value="id")String id)throws OpenAccessException{
		return importFeederLossRepo.findById(id).get();	
	}	
	
	@CrossOrigin(origins="*")
	@PostMapping("/")
	public String addimportFeederLoss(@RequestBody List<ImportFeederLoss> importFeederLoss)  throws OpenAccessException {
		System.out.println(importFeederLoss);
		 Date date= new Date();
		 long time = date.getTime();
		 Timestamp ts = new Timestamp(time);
		 String batchKey = null;
		 String res = null;
		 
		 //finding duplicate records
		 List<FeederLoss>feederLosses = (List<FeederLoss>) feederLossRepo.findAll();
//		 		List<SubstationLoss>existingSSLoss=new ArrayList<SubstationLoss>();
		 List<FeederLoss>exestingFeederLoss = new ArrayList<FeederLoss>();
		 for(FeederLoss feederLossExesting : feederLosses) {
//			 System.out.println(feederLossExesting);
				if(importFeederLoss!=null && !(importFeederLoss.isEmpty())) {
					 for (ImportFeederLoss importFeederIncoming : importFeederLoss) {
						 if(importFeederIncoming.getOrgId().equals(feederLossExesting.getOrgId())
								 &&importFeederIncoming.getMonth().equals(feederLossExesting.getMonth())
								 &&importFeederIncoming.getYear().equals(feederLossExesting.getYear())
								 &&importFeederIncoming.getSubstationId().equals(feederLossExesting.getSubstationId())
								 &&importFeederIncoming.getFeederId().equals(feederLossExesting.getFeederId())) {
							 exestingFeederLoss.add(feederLossExesting);
							 if(!exestingFeederLoss.isEmpty()) {
									throw new OpenAccessException("Feeder Loss Percent Has Been Entered For this Month.For Further clarification Please contact TANGEDCO IT Team");
								}
					 }
				}
		 }
		 }
		 
		 //creating batch id and saving inot imp feeder loss table
			batchKey=importFeederLoss.get(0).getOrgId()+"-"+importFeederLoss.get(0).getMonth()+"-"+importFeederLoss.get(0).getYear()+"-"+ ts;
		 for (ImportFeederLoss importFeeder : importFeederLoss) {
//		batchKey=importFeeder.getOrgId()+"-"+importFeeder.getMonth()+"-"+importFeeder.getYear()+"-"+ ts;
		importFeeder.setId(commonUtils.generateId());
		importFeeder.setBatchKey(batchKey);
//		System.out.println(importFeeder.getResultDesc());
		 }
//		 System.out.println(importFeederLoss);
		 
		 importFeederLossRepo.saveAll(importFeederLoss);
			//calling procedure
		 String ssLossFunction = "{call FEEDER_LOSS.START_IMPORT(?,?,?)}";
			try {
				conn = dataSource.getConnection();
				stmt = conn.prepareCall(ssLossFunction);
				stmt.setString(1, batchKey);
				stmt.registerOutParameter(2, OracleTypes.VARCHAR);
				stmt.registerOutParameter(3, OracleTypes.VARCHAR);
				stmt.execute();
				res = stmt.getString(3);
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}	finally {
				
				try {
					if((stmt!= null) && (!stmt.isClosed())) {
						stmt.close();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					if((conn!= null) && (!conn.isClosed())) {
						conn.close();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			String feederId = importFeederLoss.get(0).getFeederId();
			String substationId = importFeederLoss.get(0).getSubstationId();
			String orgId = importFeederLoss.get(0).getOrgId();
			String month = importFeederLoss.get(0).getMonth();
			String year = importFeederLoss.get(0).getYear();
			CallCreateWithFeederLoss(feederId,substationId,orgId,month,year);
	return res;
	}
	
//	--    GENERATION_STATEMENT.CREATE_WITH_FEEDER_LOSS(I_FEEDER_ID,I_SUBSTATION_ID,I_SERVICE_NUMBER,I_ORG_ID, I_MONTH,I_YEAR,O_RESULT_CODE, O_RESULT_DESC) ;    

	public String CallCreateWithFeederLoss(String feederId,String subStationId,String orgId,String month,String year){
		System.out.println("im here");
		System.out.println(feederId);
//		subStationId,serviceNo,orgId,month,year,resultCode,resultDesc);
		String res= null;
		String createFeederLoss = "{call GENERATION_STATEMENT.CREATE_WITH_FEEDER_LOSS(?,?,?,?,?,?,?,?)}";
		
System.out.println("CREATE_WITH_FEEDER_LOSS");
		try {
		        conn1 = dataSource.getConnection();
		        stmt1 = conn1.prepareCall(createFeederLoss);
		        stmt1.setString(1, feederId);
		        stmt1.setString(2, subStationId);
//		        stmt1.setString(3, serviceNo);
		        stmt1.registerOutParameter(3, OracleTypes.VARCHAR);
		        stmt1.setString(4, orgId);
		        stmt1.setString(5, month);
		        stmt1.setString(6, year);
//		        stmt1.setString(7, resultCode);
//		        stmt1.setString(8, resultDesc);
		        stmt1.registerOutParameter(7, OracleTypes.VARCHAR);
		        stmt1.registerOutParameter(8, OracleTypes.VARCHAR);
		        stmt1.execute();
		        res= stmt1.getString(8);
		        System.out.println(feederId);
//		        log.info("Out-8-"+res);
		        conn1.close();
		      
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			try {
				if((stmt1!= null) && (!stmt1.isClosed())) {
					stmt1.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if((conn1!= null) && (!conn1.isClosed())) {
					conn1.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		System.out.println(res);
			return res;
	}
			
	
	
	@CrossOrigin(origins="*")
	@PatchMapping("/{id}")
	public ImportFeederLoss updateimportFeederLoss(@PathVariable(value="id")String id,@RequestBody ImportFeederLoss importFeederLoss)  throws OpenAccessException {
		return importFeederLossRepo.save(importFeederLoss);
	}
	
	@CrossOrigin(origins = "*")
	@DeleteMapping("/{id}")
	public void deleteimportFeederById(@PathVariable(value = "id") String id) throws OpenAccessException {
		importFeederLossRepo.deleteById(id);
	}
}
