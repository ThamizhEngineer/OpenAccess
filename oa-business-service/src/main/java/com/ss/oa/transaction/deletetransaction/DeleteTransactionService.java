package com.ss.oa.transaction.deletetransaction;


import java.sql.CallableStatement;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ss.oa.common.OpenAccessException;
import com.ss.oa.model.transaction.DeleteTransaction;
import com.ss.oa.transaction.vo.DeleteTransactionLog;
import com.ss.oashared.common.CommonUtils;
import com.ss.oashared.interceptor.AuthUserRepository;
import com.ss.oashared.interceptor.AuthUserTypeAccessRepository;
import com.ss.oashared.model.AuthUser;
import com.ss.oashared.model.AuthUserTypeAccess;
import com.ss.oashared.model.VCompanyService;

import oracle.jdbc.OracleTypes;

@RestController
@RequestMapping("/transaction/delete-transaction")
public class DeleteTransactionService {
	Logger log = org.slf4j.LoggerFactory.getLogger(this.getClass());
	
	@Resource
	private DataSource dataSource;
	
	@Autowired
	DeleteTransactionRepository deleteTransactionRepository;
	
	@Autowired
	private CommonUtils commonUtils;
	
	@Autowired
	EntityManager entityManager;
	
	@Autowired
	AuthUserTypeAccessRepository authUserTypeAccessRepository;
	
	@Autowired
	AuthUserRepository authUserRepository;
	
	Connection conn = null;
	CallableStatement stmt = null;
	
	@GetMapping("/get")
	public Iterable<DeleteTransaction> getDeleteTransactionRepository()
	{
		return deleteTransactionRepository.findAll();
		
	}
	
	@GetMapping("/getAll")
	public List<DeleteTransaction> getAllDeleteTransaction(HttpServletRequest request,@RequestParam(name="org-id",required=true) String orgId ,@RequestParam(name="gen-service-number",required=false) String genServiceNumber ,@RequestParam(name="month",required=false) String readingMonth, @RequestParam(name="year",required=false) String readingYear)
			{
//		System.out.println( request.getHeader("authorization"));
//		String req=request.getHeader("authorization");
//		List<AuthUser>users=authUserRepository.findByToken(req);
//		System.out.println(users.get(0).getUserTypeCode());
////		if(users.get(0).getUserTypeCode()!=null)
		
		return deleteTransactionRepository.getTransaction(orgId,genServiceNumber,readingMonth, readingYear);
	}
	
//	------get all from delete_txn_log table
	@GetMapping("/getAllLog")
	public List<DeleteTransactionLog>  getAllTransactionLog(HttpServletRequest request,@RequestParam(name="org-id",required=true) String mOrgId ,
			@RequestParam(name="gen-service-number",required=false) String serviceNo ,@RequestParam(name="month",required=false) String month, 
			@RequestParam(name="year",required=false) String year){
		return deleteTransactionRepository.getTransactionLog(mOrgId, serviceNo, month, year);
	}
	
	
	@PostMapping("/post")
	public ArrayList<DeleteTransaction> addNewDeleteTransaction(HttpServletRequest request, @RequestParam(value="transactionTypeCode")String transactionTypeCode,@RequestBody ArrayList<DeleteTransaction> deleteTransaction) // Array list
	{
		System.out.println( request.getHeader("authorization"));
		String req=request.getHeader("authorization");
		List<AuthUser>users=authUserRepository.findByToken(req);
		System.out.println(users.get(0).getUserTypeCode());
	
	
		for(DeleteTransaction i:deleteTransaction) {
			System.out.println();	

			String userTypeCode=users.get(0).getUserTypeCode();
			String id=i.getGenServiceNumber();
			System.out.println(id);
			
		List<VCompanyService> fuelGroupName = deleteTransactionRepository.findBySerNum(i.getGenServiceNumber());
		
			String fuelGrpName=fuelGroupName.get(0).getFuelGroupName();
			List<AuthUserTypeAccess> accesses=authUserTypeAccessRepository.findByUserTypeCode(userTypeCode);
			System.out.println("fuelGrpName"+fuelGrpName);
		    System.out.println("usertypecode"+users.get(0).getUserTypeCode().equals("A"));
			if(accesses!=null && !accesses.isEmpty() || users.get(0).getUserTypeCode().equals("A")) {
			System.out.println("hi");
			if(accesses==null||accesses.isEmpty()) {
				i.setRemarks(users.get(0).getUserName()+"-"+users.get(0).getUserTypeCode());
				deleteTransactionRepository.save(i);
				if(transactionTypeCode.equals("01")) {
					 callDeleteTransByServiceAllotment(i.getRemarks(),i.getGenServiceNumber(),i.getReadingMonth(),i.getReadingYear(),i.getDeletedLedger(), i.getDeletedEs(), i.getDeletedGs(), i.getDeletedMr());
				}
				else {
				callDeleteTransByServiceReading(i.getRemarks(),i.getGenServiceNumber(),i.getReadingMonth(),i.getReadingYear(),i.getDeletedLedger(), i.getDeletedEs(), i.getDeletedGs(), i.getDeletedMr());
				}
			}
				for(AuthUserTypeAccess authuser:accesses) {
				System.out.println("1"+authuser.getUserTypeCode());
				System.out.println("2"+users.get(0).getUserTypeCode());
			
				System.out.println("came in");
				i.setRemarks(users.get(0).getUserName()+"-"+users.get(0).getUserTypeCode());
				deleteTransactionRepository.save(i);
					if(transactionTypeCode.equals("01")) {
						 callDeleteTransByServiceAllotment(i.getRemarks(),i.getGenServiceNumber(),i.getReadingMonth(),i.getReadingYear(),i.getDeletedLedger(), i.getDeletedEs(), i.getDeletedGs(), i.getDeletedMr());
					}
					else {
					callDeleteTransByServiceReading(i.getRemarks(),i.getGenServiceNumber(),i.getReadingMonth(),i.getReadingYear(),i.getDeletedLedger(), i.getDeletedEs(), i.getDeletedGs(), i.getDeletedMr());
					}
				
			 if(users.get(0).getUserTypeCode().equals(authuser.getUserTypeCode())&&authuser.getFuelGroupName().equals(fuelGrpName)) {
				 i.setRemarks(users.get(0).getUserName()+"-"+users.get(0).getUserTypeCode());
				 deleteTransactionRepository.save(i);
					if(transactionTypeCode.equals("01")) {
					 callDeleteTransByServiceAllotment(i.getRemarks(),i.getGenServiceNumber(),i.getReadingMonth(),i.getReadingYear(),i.getDeletedLedger(), i.getDeletedEs(), i.getDeletedGs(), i.getDeletedMr());
					}
					else {
					callDeleteTransByServiceReading(i.getRemarks(),i.getGenServiceNumber(),i.getReadingMonth(),i.getReadingYear(),i.getDeletedLedger(), i.getDeletedEs(), i.getDeletedGs(), i.getDeletedMr());
					}	
			  }else {
				throw new OpenAccessException("Delete Txn Cannot be done - Based on Rule Definition");
			 }}
		   }else {
			throw new OpenAccessException("No Rule Definition in Auth_user_type_Access");
		   }
		}
		return deleteTransaction;	
	  }
	
	@GetMapping("/getbyServiceNumber")
	public Iterable<DeleteTransaction> getAllDeleteTransaction(@RequestParam(name="remarks",required=false) String remarks,@RequestParam(name="genServiceNumber",required=false) String genServiceNumber,@RequestParam(name="readingMonth",required=false) String readingMonth,@RequestParam(name="readingYear",required=false) String readingYear) throws OpenAccessException
	{
		return deleteTransactionRepository.getDeleteTransaction(remarks, genServiceNumber, readingMonth, readingYear);
	}
	@GetMapping("/get-by-gen-serv-no/{genServiceNumber}/{readingMonth}/{readingYear}")
	public DeleteTransaction getByServNum(@PathVariable(value="genServiceNumber")String genServiceNumber,@PathVariable(value="readingMonth")String readingMonth,@PathVariable(value="readingYear")String readingYear)throws OpenAccessException{
		DeleteTransaction del= deleteTransactionRepository.findByGenServiceNumberAndReadingMonthAndReadingYear(genServiceNumber,readingMonth,readingYear);
	System.out.println(del);
	return del;
	}
	
	//--get by id from delete_txn_log table
//	@GetMapping("/get-by-gen-serv-no-log/{genServiceNumber}/{readingMonth}/{readingYear}")
//	public DeleteTransactionLog getByServNumLog(@PathVariable(value="genServiceNumber")String serviceNo,
//			@PathVariable(value="readingMonth")String month,@PathVariable(value="readingYear")String year)throws OpenAccessException{
//		DeleteTransactionLog del = deleteTransactionRepository.getByIdLog(serviceNo, month, year);
//		return del;	
//	}
	
	
	@DeleteMapping("/call")
	public String callDeleteTransByServiceAllotment(@RequestParam String remarks, @RequestParam String serviceNumber, @RequestParam String readingMonth, @RequestParam String readingYear, @RequestParam String deleteLedger, @RequestParam String deleteEs, @RequestParam String deleteGs, @RequestParam String deleteMr) {
		deleteLedger="Y";deleteEs="Y";deleteGs="Y";deleteMr="Y";
		String deleteFunction = "{call DELETE_TXN.DELETE_BY_SERVICE(?,?,?,?,?,?,?,?,?,?)}";
		String res= "SUCCESS";
		
		try {
		        conn = dataSource.getConnection();
		        stmt = conn.prepareCall(deleteFunction);
		        stmt.setString(1, remarks);
		        stmt.setString(2, serviceNumber);
		   //     System.out.println("serviceNumber--"+serviceNumber);
		        stmt.setString(3, readingMonth);
		    //    System.out.println("readingMonth--"+readingMonth);
		        stmt.setString(4, readingYear);
		    //    System.out.println("readingYear--"+readingYear);
		        stmt.setString(5, deleteLedger);
		   //     System.out.println("deleteLedger--"+deleteLedger);
		        stmt.setString(6, deleteEs);
		//        System.out.println("deleteEs--"+deleteEs);
		        stmt.setString(7, deleteGs);
		 //       System.out.println("deleteGs--"+deleteGs);
		        stmt.setString(8, deleteMr);
		 //       System.out.println("deleteMr--"+deleteMr);
		//        System.out.println(stmt);
		        log.info("stmt-"+stmt);
		        stmt.registerOutParameter(9, OracleTypes.VARCHAR);
		        stmt.registerOutParameter(10, OracleTypes.VARCHAR);
		        stmt.execute();
		        System.out.println("executed");
		        res = stmt.getString(9) + " - " + stmt.getString(10);   
				log.info("Out-8-"+res);
				stmt.close();
		        conn.close();
		      
		} catch (Exception e) {
			res = "FAILURE - " + e.getMessage();
		} finally {
			
			try {
				if((stmt!= null) && (!stmt.isClosed())) {
					stmt.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				res = "FAILURE - " + e.getMessage();
			}
			try {
				if((conn!= null) && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				res = "FAILURE - " + e.getMessage();
			}
			
		}
		if (res.startsWith("FAILURE")) {

			throw new OpenAccessException(res);
		}
		
			return res;
		
	}
	
	@DeleteMapping("/del")
	public String callDeleteTransByServiceReading(@RequestParam String remarks, @RequestParam String serviceNumber, @RequestParam String readingMonth, @RequestParam String readingYear, @RequestParam String deleteLedger, @RequestParam String deleteEs, @RequestParam String deleteGs, @RequestParam String deleteMr) {
		deleteLedger="Y";deleteEs="Y";deleteGs="N";deleteMr="N";
		String deleteFunction = "{call DELETE_TXN.DELETE_BY_SERVICE(?,?,?,?,?,?,?,?,?,?)}";
		String res= "SUCCESS";
		
		try {
		        conn = dataSource.getConnection();
		        stmt = conn.prepareCall(deleteFunction);
		        stmt.setString(1, remarks);
		   //     System.out.println("remarks--"+remarks);
		        stmt.setString(2, serviceNumber);
		   //     System.out.println("serviceNumber--"+serviceNumber);
		        stmt.setString(3, readingMonth);
		   //     System.out.println("readingMonth--"+readingMonth);
		        stmt.setString(4, readingYear);
		   //     System.out.println("readingYear--"+readingYear);
		        stmt.setString(5, deleteLedger);
		  //      System.out.println("deleteLedger--"+deleteLedger);
		        stmt.setString(6, deleteEs);
		    //    System.out.println("deleteEs--"+deleteEs);
		        stmt.setString(7, deleteGs);
		  //      System.out.println("deleteGs--"+deleteGs);
		        stmt.setString(8, deleteMr);
		  //      System.out.println("deleteMr--"+deleteMr);
		        System.out.println(stmt);
		        log.info("stmt-"+stmt);
		        stmt.registerOutParameter(9, OracleTypes.VARCHAR);
		        stmt.registerOutParameter(10, OracleTypes.VARCHAR);
		        stmt.execute();
		        System.out.println("executed");
		        res = stmt.getString(9) + " - " + stmt.getString(10);   
				log.info("Out-8-"+res);
		        conn.close();
		      
		} catch (Exception e) {
			res = "FAILURE - " + e.getMessage();
		} finally {
			
			try {
				if((stmt!= null) && (!stmt.isClosed())) {
					stmt.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				res = "FAILURE - " + e.getMessage();
			}
			try {
				if((conn!= null) && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				res = "FAILURE - " + e.getMessage();
			}
			
		}
		if (res.startsWith("FAILURE")) {

			throw new OpenAccessException(res);
		}
			return res;

	}
	
	
	@DeleteMapping("/delete")
	public String callProcessIntDeleteTxn(@RequestParam String remarks, @RequestParam String deleteLedger, @RequestParam String deleteEs, @RequestParam String deleteGs, @RequestParam String deleteMr) {
		String deleteFunction = "{call DELETE_TXN.PROCESS_INT_DELETE_TXN(?,?,?,?,?)}";
		String res= "successful";
		
		try {
		        conn = dataSource.getConnection();
		        stmt = conn.prepareCall(deleteFunction);
		        stmt.setString(1, remarks);
		        stmt.setString(2, deleteLedger);
		        stmt.setString(3, deleteEs);
		        stmt.setString(4, deleteGs);
		        stmt.setString(5, deleteMr);
		        stmt.execute();
		        res= stmt.getString(5);   
				log.info("Out-5-"+res);
		        conn.close();
		      
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
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
			return res;
	}	
}


