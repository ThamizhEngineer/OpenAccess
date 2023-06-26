package com.ss.oa.integration.energyadjusteddata;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ss.oa.vo.EnergyAdjustedData;
import com.ss.oa.vo.EnergyAdjustedDataCharge;
import com.ss.oashared.common.CommonUtils;
import com.ss.oashared.common.OpenAccessException;

import oracle.jdbc.OracleTypes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class EnergyAdjustedDataService {
	private static final Logger logger = LoggerFactory.getLogger(EnergyAdjustedDataService.class);
	
	@Resource
	private DataSource dataSource;
	
	@Autowired
	private CommonUtils commonUtils;

	
	@Autowired
	EnergyAdjustedDataRepository energyAdjustedDataRepository;

	@Autowired
	EnergyAjustedDataRepo energyAjustedDataChargeRepo;
	
	@GetMapping("/getall")
	public Iterable<EnergyAdjustedData> getAllTempGenStmt()throws OpenAccessException{
		return energyAdjustedDataRepository.findAll();
	}
	@GetMapping("/{id}")
	public Optional<EnergyAdjustedData> getAllTempGenStmtId(@PathVariable(value="id")String id)throws OpenAccessException{
		return energyAdjustedDataRepository.findById(id);
	}
	
	@CrossOrigin(origins = "*")
	@PostMapping(value="/post-energy-adjusted-service/_internal")
	public ArrayList<EnergyAdjustedData> addEnergyAdjustmenData(@RequestBody ArrayList<EnergyAdjustedData> energyAdjustedDatas){
		logger.info("EnergyAdjustedData-"+energyAdjustedDatas);
		
		energyAdjustedDatas.forEach(energyAdjustedData -> {
			try {
				String ids=commonUtils.generateId();
				String batchKey=commonUtils.generateId();
				String rdMonth=StringUtils.leftPad(energyAdjustedData.getReadingMnth(), 2, "0");
				String rdYear=energyAdjustedData.getReadingYr();
				energyAdjustedData.setId(ids);
				energyAdjustedData.setBatchKey(batchKey);
				energyAdjustedData.setReadingMnth(rdMonth);
				if(energyAdjustedData.getEnergyadjusteddatacharge()!=null&&!energyAdjustedData.getEnergyadjusteddatacharge().isEmpty()) {
					for(EnergyAdjustedDataCharge energyAdjustedDataCharge:energyAdjustedData.getEnergyadjusteddatacharge()) {
						energyAdjustedDataCharge.setId(commonUtils.generateId());
						energyAdjustedDataCharge.setEnergyAdjustedDataId(ids);
						energyAjustedDataChargeRepo.save(energyAdjustedDataCharge);
					}
					
				}
				if(energyAdjustedData.getCharges()!=null&&!energyAdjustedData.getCharges().isEmpty()) {
					for(Charges charge:energyAdjustedData.getCharges()) {
						EnergyAdjustedDataCharge energyAdjustedDataCharge= new EnergyAdjustedDataCharge();
						energyAdjustedDataCharge.setId(commonUtils.generateId());
						energyAdjustedDataCharge.setEnergyAdjustedDataId(ids);
						energyAdjustedDataCharge.setChargeCode(charge.getChargesCode());
						energyAdjustedDataCharge.setChargeAmount(charge.getCharge());
						energyAjustedDataChargeRepo.save(energyAdjustedDataCharge);
					}
				}
				energyAdjustedDataRepository.save(energyAdjustedData);

				processIntAdjData(batchKey,rdMonth,rdYear);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		
		return energyAdjustedDatas ;
	}

	@PatchMapping("/{id}/lines")
	public EnergyAdjustedData addEnergyAdjustedDataLines(@RequestBody EnergyAdjustedData energyAdjustedData)throws OpenAccessException{
		if(energyAdjustedData.getEnergyadjusteddatacharge()!=null&&!energyAdjustedData.getEnergyadjusteddatacharge().isEmpty()) {
			for(EnergyAdjustedDataCharge energyAdjustedDataCharge:energyAdjustedData.getEnergyadjusteddatacharge()) {
				
				energyAjustedDataChargeRepo.save(energyAdjustedDataCharge);
			}
		}
		return energyAdjustedData;
	}
	
	public String processIntAdjData(String batchKey,String month,String year) {
	String incrementSurplusUnitsFunction = "{call IMP_INT_ADJUSTED_UNIT.PROCESS_INT_ADJUSTED_UNIT(?,?,?,?,?)}"; 
	String res= null;
	Connection conn = null;
	CallableStatement stmt = null;
		try {
				conn =  dataSource.getConnection();
				stmt = conn.prepareCall(incrementSurplusUnitsFunction);	
		        stmt.setString(1, batchKey);
		        stmt.setString(2, month);
		        stmt.setString(3, year);
		        stmt.registerOutParameter(4, OracleTypes.VARCHAR);
		        stmt.registerOutParameter(5, OracleTypes.VARCHAR);
		        stmt.execute();
		        res= stmt.getString(4);  
		        stmt.close();
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
	
	@CrossOrigin(origins = "*")
	@GetMapping(value="/get-energy-adjusted-service")
	public Iterable<EnergyAdjustedData>getEAdjData() throws OpenAccessException {
		return energyAdjustedDataRepository.findAll();
	}
}
