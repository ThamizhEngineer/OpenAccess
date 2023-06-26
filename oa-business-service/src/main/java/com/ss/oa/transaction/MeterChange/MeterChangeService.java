package com.ss.oa.transaction.MeterChange;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.ss.oa.transaction.vo.SlotWiseMeterReading;
import com.ss.oa.transaction.MeterChange.MeterChangeMc;
import com.ss.oa.transaction.job.JobService;
import com.ss.oa.transaction.vo.GenerationStatement;
import com.ss.oa.transaction.vo.MeterChange;
//@Component
@RestController
@RequestMapping("/MeterChangeCalculation")
@Service
public class MeterChangeService {
	@Resource
	private JdbcOperations jdbcOperations;
	
	@Resource
	private DataSource dataSource;
	
	@Autowired
    MeterChangeMcRepository meterChangeMcRepository;
	
	@Autowired
	JobService jobService;
	
	@Autowired
    MeterChangeListRepository meterChangeListRepository ;
	
	 private JdbcTemplate jdbcTemplateObject;
	Connection conn = null;
	CallableStatement stmt = null;

	@PersistenceContext
    private EntityManager entityManager;

	
	@CrossOrigin(origins = "*")
	@GetMapping("/MeterChangeDetail")
	
	public Iterable<MeterChangeMc>  MeterChangePrint(@RequestParam String ServiceNumber,@RequestParam String month,@RequestParam String year) {
		
		System.out.println(meterChangeMcRepository.findbyCombo(ServiceNumber, month, year));
		return meterChangeMcRepository.findbyCombo(ServiceNumber, month, year);
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping("/_internal")
	public Iterable<MeterChangeMc>  MeterChangePrintDoc(@RequestParam String ServiceNumber,@RequestParam String month,@RequestParam String year) {
		
		System.out.println(meterChangeMcRepository.findbyCombo(ServiceNumber, month, year));
		return meterChangeMcRepository.findbyCombo(ServiceNumber, month, year);
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping("/meterChange")
	public Iterable<MeterChangeList> Importmeterchange(@RequestParam String month,@RequestParam String year)throws Exception {

        //"login" this is the name of your procedure
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("METER_CHANGE.IMPORT_METER_CHANGE"); 
       
        //Declare the parameters in the same order
        query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(3, String.class, ParameterMode.OUT);
        query.registerStoredProcedureParameter(4, String.class, ParameterMode.OUT);

        //Pass the parameter values
        query.setParameter(1, month);
        query.setParameter(2, year);

        //Execute query
        
        query.execute();
        
        //Get output parameters
        String outCode = (String) query.getOutputParameterValue(3);
        String outMessage = (String) query.getOutputParameterValue(4);
        System.out.println("came in-----"+outCode);
        if(outCode.equals("SUCCESS")) {
        	
        	outCode = JobCaller(month,year);
        	System.out.println(outCode);
        	if (outCode =="SUCCESS"){
        	
        	return meterChangeListRepository.findByServicenoAndMonth(month,year); 
        	}
        	else {
        		
        		return meterChangeListRepository.findByServicenoAndMonth(month,year); 	
        		
        	}
        }
        else {
        	 System.out.println(outCode+outMessage);

             return meterChangeListRepository.findByServicenoAndMonth(month,year); 
        }
       
    }
	
	public String JobCaller(String month,String year)throws Exception {
		System.out.println("came in");
		 jobService.MeterChangeJob(month,year);
		
		return "SUCCESS";
		
	}
	@CrossOrigin(origins = "*")
	@GetMapping("/refresh")
	public Iterable<MeterChangeList> RefreshMeterChange(@RequestParam String month,@RequestParam String year)throws Exception {	
		
		
		return meterChangeListRepository.findByServicenoAndMonth(month,year);  
	}

}