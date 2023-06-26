package com.ss.oa.transaction.UnAllocatedRemarks;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ss.oa.model.transaction.UnAllocatedRemarks;

import oracle.jdbc.OracleTypes;

@Controller
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/unallocatedremarks")
public class UnAllocatedRemarksService {
Logger log = org.slf4j.LoggerFactory.getLogger(this.getClass());
	
	@Resource
	private DataSource dataSource;
	
	@Autowired
	UnAllocatedRemarksRepository unAllocatedRemarksRepository;
	
	Connection conn = null;
	CallableStatement stmt = null;
	
	@Autowired
	UnAllocatedRemarksService unallocatedservice ;
	
	
	@GetMapping("/all")
   private Iterable<UnAllocatedRemarks> getall(@RequestParam(name="edcId",required=false) String edcId,
		   @RequestParam(name="month",required=false) String Month,
		   @RequestParam(name="year",required=false) String year,
		   @RequestParam(name="flowtype",required=false) String flowtype ) {
		
		
		
	      //unAllocatedRemarksRepository.UpdateRemarks(mCompServiceId,Month,remarks,year);
	      //return unAllocatedRemarksRepository.findAll();
		
		
		
		unallocatedservice.callProcessforunallocated(edcId, Month, year, flowtype);
		
		return unAllocatedRemarksRepository.findbycombo(edcId,Month,year,flowtype);
	
		
	}
	
	
	
	@GetMapping("/updatenew")
	private String getalls(@RequestParam(name="Servicenum",required=false) String mCompServiceId,
			   @RequestParam(name="month",required=false) String Month,
			   @RequestParam(name="year",required=false) String year,
			   @RequestParam(name="remarks",required=false) String remarks ) {
		
		
		
		
		
		       unAllocatedRemarksRepository.UpdateRemarks(mCompServiceId,Month,remarks,year);
		      return "Saved Successfully";
			}

	@GetMapping("/fordetail")
	private UnAllocatedRemarks fordetails(@RequestParam(name="Servicenum",required=false) String mCompServiceId,
			   @RequestParam(name="month",required=false) String Month,
			   @RequestParam(name="year",required=false) String year
			   ){
		System.out.println(unAllocatedRemarksRepository.ForDetail(mCompServiceId,Month,year));
		
		return unAllocatedRemarksRepository.ForDetail(mCompServiceId,Month,year);
		
	}
	
		public String callProcessforunallocated(String m_org_id,String month,String year,String Flowtype) {
			System.out.println("came in here");
			System.out.println(m_org_id);
			System.out.println(month);
			System.out.println(Flowtype);
			System.out.println(year);
			String Iscaptive = "";
			String Isstb = "";
			String Isthirdparty = "";
			if(Flowtype == "IS-CAPTIVE") {
				 Iscaptive = "Y";
				 Isstb = "N";
				 Isthirdparty = "N";
			}
			if(Flowtype == "THIRD-PARTY") {
				 Iscaptive = "N";
				 Isstb = "N";
				 Isthirdparty = "Y";
			}
			if(Flowtype == "STB") {
				 Iscaptive = "N";
				 Isstb = "Y";
				 Isthirdparty = "N";
			}
			
			System.out.println(Iscaptive);
			System.out.println(Isstb);
			System.out.println(Isthirdparty);
		    String Allocationfunction= "{call AUDIT_OPS.REGISTER_UNALLOC_SERVICE(?,?,?,?,?,?,?,?)}";
			String res= "successful";
		    try {
			        conn = dataSource.getConnection();
			        stmt = conn.prepareCall(Allocationfunction);
			        stmt.setString(1, m_org_id);//i_org_id in varchar2 ,
                    stmt.setString(2, month);//i_reading_month in varchar2 ,
			        stmt.setString(3, year);//i_reading_year in varchar2 ,
			        stmt.setString(4, Iscaptive);//i_is_captive in varchar2 ,
			        stmt.setString(5, Isstb);//i_is_stb in varchar2 ,
			        stmt.setString(6, Isthirdparty);//i_is_third_party in varchar2 ,
			        stmt.registerOutParameter(7, OracleTypes.VARCHAR);
			        stmt.registerOutParameter(8, OracleTypes.VARCHAR);
			        
			        stmt.execute();
			        System.out.println(stmt);
			        log.info("stmt-"+stmt);
			        
			        stmt.execute();
			        System.out.println("executed");
			          
				
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
			System.out.println(res);
				return res;
		}	
	
	}
	
