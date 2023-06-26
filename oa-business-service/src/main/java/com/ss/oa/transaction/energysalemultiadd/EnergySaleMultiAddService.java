package com.ss.oa.transaction.energysalemultiadd;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ss.oa.common.OpenAccessException;
import com.ss.oa.transaction.vo.EnergySaleMultiAddHeader;
import com.ss.oa.transaction.vo.EnergySaleMultiAddLine;
import com.ss.oashared.common.CommonUtils;

import oracle.jdbc.OracleTypes;

@Scope("prototype")
@RestController
@RequestMapping("/transaction/energysale-multiadd")
public class EnergySaleMultiAddService {
	Logger log = org.slf4j.LoggerFactory.getLogger(this.getClass());

	@Resource
	private DataSource dataSource;
	@Autowired
	EnergySaleMultiAddHeaderRepository energySaleMultiAddHeaderRepository;
	
	
	@Autowired
	EnergySaleMultiAddLineRepository energySaleMultiAddLineRepository;
	

	@Autowired
	private CommonUtils commonUtils;
	
	Connection conn = null;
	CallableStatement stmt = null;
	

	@CrossOrigin(origins = "*")
	@GetMapping
	public Iterable<EnergySaleMultiAddHeader> getAllEnergySaleMultiAddHeader() throws OpenAccessException {
		return energySaleMultiAddHeaderRepository.findAll();
	}
	
	@CrossOrigin(origins="*")
	@GetMapping("/{id}")
	public Optional<EnergySaleMultiAddHeader> getEnergySaleMultiAddHeaderById(@PathVariable(value="id")String id)throws OpenAccessException{
		return energySaleMultiAddHeaderRepository.findById(id);
		
	}
	
	@CrossOrigin(origins = "*")
	@PostMapping
	public EnergySaleMultiAddHeader addEnergySaleMultiAddHeader(@RequestBody EnergySaleMultiAddHeader energySaleMultiAddHeader) throws OpenAccessException {
		energySaleMultiAddHeader.setId(commonUtils.generateId());
		for(EnergySaleMultiAddLine energySaleMultiAddLine:energySaleMultiAddHeader.getEnergySaleMultiAddLines()) {
			energySaleMultiAddLine.setId(commonUtils.generateId());
		}
		return energySaleMultiAddHeaderRepository.save(energySaleMultiAddHeader);

	}
	
	@CrossOrigin(origins = "*")
	@PatchMapping("/{id}")
	public EnergySaleMultiAddHeader updateEnergySaleMultiAddHeader(@RequestBody EnergySaleMultiAddHeader energySaleMultiAddHeader) throws OpenAccessException {
		return energySaleMultiAddHeaderRepository.save(energySaleMultiAddHeader);

	}
	
	@CrossOrigin(origins = "*")
	@PatchMapping("/delete-multiadd/{id}")
	public void deleteEnergySaleMultiAddHeader(@PathVariable(value="id")String id,@RequestBody EnergySaleMultiAddHeader energySaleMultiAdd) throws OpenAccessException {
		EnergySaleMultiAddHeader energySaleMultiAddHeader= getEnergySaleMultiAddHeaderById(id).get();
		if(energySaleMultiAddHeader.getEnergySaleMultiAddLines()!=null && !(energySaleMultiAddHeader.getEnergySaleMultiAddLines().isEmpty())) {
					for(EnergySaleMultiAddLine EnergySaleMultiAddLine:energySaleMultiAddHeader.getEnergySaleMultiAddLines()) {
					
						energySaleMultiAddLineRepository.delete(id);
					}
		}
		energySaleMultiAddHeaderRepository.delete(id);
		

	}
	
	@CrossOrigin(origins = "*")
	@PostMapping("/validate-multiadd")
	public EnergySaleMultiAddHeader esMultiAddValidateFunction(@RequestBody EnergySaleMultiAddHeader energySaleMultiAddHeader) throws Exception {
		
		String esMultiAddHeaderId="";
		if(energySaleMultiAddHeader==null || energySaleMultiAddHeader.getEnergySaleId()==null || energySaleMultiAddHeader.getEnergySaleMultiAddLines()==null || energySaleMultiAddHeader.getEnergySaleMultiAddLines().isEmpty()) {
			throw new Exception("Valid Data Missing in input - "+energySaleMultiAddHeader);
		}
		multiAddClear(energySaleMultiAddHeader.getEnergySaleId(),"Y");
		for(EnergySaleMultiAddLine energySaleMultiAddLine:energySaleMultiAddHeader.getEnergySaleMultiAddLines()) {
			energySaleMultiAddLine.setId(commonUtils.generateId());
			energySaleMultiAddLineRepository.save(energySaleMultiAddLine);
		}
		esMultiAddHeaderId = multiAddInit(energySaleMultiAddHeader.getEnergySaleId());
		
		if(esMultiAddHeaderId!=null) {
			energySaleMultiAddHeader.setId(esMultiAddHeaderId);
			multiAddValidate(energySaleMultiAddHeader.getEnergySaleId(),esMultiAddHeaderId);
		}

		return energySaleMultiAddHeader;
	}
	
	@CrossOrigin(origins = "*")
	@PatchMapping("/import-multiadd/{id}")
	public EnergySaleMultiAddHeader esMultiAddImportFunction(@PathVariable(value="id")String id,@RequestBody EnergySaleMultiAddHeader energySaleMultiAddHeader) throws OpenAccessException {
		
		
		multiAddImport(energySaleMultiAddHeader.getEnergySaleId(),energySaleMultiAddHeader.getId());
		

		return energySaleMultiAddHeader;
	}
	
	public String multiAddClear(String energySaleId,String overwrite) {
			
	String clearFunction = "{call energy_sale_bulk_allotment.clear(?,?,?,?)}"; 
	
	String res= null;
		try {
		        conn = dataSource.getConnection();
		        stmt = conn.prepareCall(clearFunction);
		        stmt.setString(1, energySaleId);
		        stmt.setString(2, overwrite);
		        stmt.registerOutParameter(3, OracleTypes.VARCHAR);
		        stmt.registerOutParameter(4, OracleTypes.VARCHAR);
		        stmt.execute();
		        res= stmt.getString(4);   
				log.info("Out-4-"+res);
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
	
	public String multiAddInit(String energySaleId) {
		
		String clearFunction = "{call energy_sale_bulk_allotment.init(?,?,?,?)}"; 
		
		String res= null;
			try {
			        conn = dataSource.getConnection();
			        stmt = conn.prepareCall(clearFunction);
			        stmt.setString(1, energySaleId);
			        stmt.registerOutParameter(2, OracleTypes.VARCHAR);
			        stmt.registerOutParameter(3, OracleTypes.VARCHAR);
			        stmt.registerOutParameter(4, OracleTypes.VARCHAR);
			        stmt.execute();
			        res= stmt.getString(4);   
					log.info("Out-4-"+res);
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
	
	public String multiAddValidate(String energySaleId,String esMultiAddHeaderId) {
		
		String clearFunction = "{call energy_sale_bulk_allotment.validate(?,?,?,?)}"; 
		
		String res= null;
			try {
			        conn = dataSource.getConnection();
			        stmt = conn.prepareCall(clearFunction);
			        stmt.setString(1, energySaleId);
			        stmt.setString(2, esMultiAddHeaderId);
			        stmt.registerOutParameter(3, OracleTypes.VARCHAR);
			        stmt.registerOutParameter(4, OracleTypes.VARCHAR);
			        stmt.execute();
			        res= stmt.getString(4);   
					log.info("Out-4-"+res);
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
	
public String multiAddImport(String energySaleId,String esMultiAddHeaderId) {
		
		String clearFunction = "{call energy_sale_bulk_allotment.import(?,?,?,?)}"; 
		
		String res= null;
			try {
			        conn = dataSource.getConnection();
			        stmt = conn.prepareCall(clearFunction);
			        stmt.setString(1, energySaleId);
			        stmt.setString(2, esMultiAddHeaderId);
			        stmt.registerOutParameter(3, OracleTypes.VARCHAR);
			        stmt.registerOutParameter(4, OracleTypes.VARCHAR);
			        stmt.execute();
			        res= stmt.getString(4);   
					log.info("Out-4-"+res);
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
	
}
