package com.ss.oa.transaction.importsubstationloss;

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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ss.oa.common.OpenAccessException;
import com.ss.oa.transaction.substationloss.SubstationLossRepository;
import com.ss.oa.transaction.vo.ImportSubstationLoss;
import com.ss.oa.transaction.vo.SubstationLoss;
import com.ss.oashared.common.CommonUtils;

import oracle.jdbc.OracleTypes;

@Scope("prototype")
@RestController
@RequestMapping("/transaction/import-substation-loss")
public class ImportSubstationLossService {

	@Resource
	private DataSource dataSource;
	@Autowired
	ImportSubstationRepository importSubstationRepository;

	@Autowired
	SubstationLossRepository substationLossRepository;

	@Autowired
	private CommonUtils commonUtils;

	Connection conn = null;
	
	CallableStatement stmt = null;

	@CrossOrigin(origins = "*")
	@GetMapping
	public Iterable<ImportSubstationLoss> getAllImportSubstationLoss(
			@RequestParam(name = "month", required = false) String month,
			@RequestParam(name = "year", required = false) String year,
			@RequestParam(name = "org-id", required = false) String orgId) throws OpenAccessException {
		return importSubstationRepository.getImportSubstationLoss(month, year, orgId);
	}

	@CrossOrigin(origins = "*")
	@GetMapping("/{id}")
	public ImportSubstationLoss getImportSubstationLossById(@PathVariable(value = "id") String id)
			throws OpenAccessException {
		return importSubstationRepository.findById(id).get();
	}

	@CrossOrigin(origins = "*")
	@PostMapping
	public ResponseEntity<?> addImportSubstationLoss(@RequestBody List<ImportSubstationLoss> importSubstationLossList)
			throws OpenAccessException {
		
		List<SubstationLoss> substationLosses = (List<SubstationLoss>) substationLossRepository.findAll();
		List<SubstationLoss> existingSSLoss = new ArrayList<SubstationLoss>();
		Date date = new Date();
		long time = date.getTime();
		Timestamp ts = new Timestamp(time);
		String res = null;

		for (SubstationLoss existingSubstationLoss : substationLosses) {
			
			if (importSubstationLossList != null && !(importSubstationLossList.isEmpty())) {

				for (ImportSubstationLoss addImportSS : importSubstationLossList) {

					if (addImportSS.getFeederId() == null) {
						

						if (addImportSS.getOrgId().equals(existingSubstationLoss.getOrgId())
								&& addImportSS.getMonth().equals(existingSubstationLoss.getMonth())
								&& addImportSS.getYear().equals(existingSubstationLoss.getYear())
								&& addImportSS.getSubstationId().equals(existingSubstationLoss.getSubstationId())) {

							    existingSSLoss.add(existingSubstationLoss);
							if (!existingSSLoss.isEmpty()) {

								throw new OpenAccessException(
										"10(1) Loss Percent Has Been Entered For this Month.For Further clarification Please contact TANGEDCO IT Team");
							}
						}

					} else {

						if (addImportSS.getOrgId().equals(existingSubstationLoss.getOrgId())
								&& addImportSS.getMonth().equals(existingSubstationLoss.getMonth())
								&& addImportSS.getYear().equals(existingSubstationLoss.getYear())
								&& addImportSS.getSubstationId().equals(existingSubstationLoss.getSubstationId())
								&& addImportSS.getFeederId().equals(existingSubstationLoss.getFeederId())) {

							   existingSSLoss.add(existingSubstationLoss);
							   System.out.println("data is there ...");
							
							if (!existingSSLoss.isEmpty()) {

								System.out.println("Sorryyy... Check your logic");
								throw new OpenAccessException(
										"10(1) Loss Percent Has Been Entered For this Month.For Further clarification Please contact TANGEDCO IT Team");
							}
						}

					}
				}
			}
		}

		String batchKey = importSubstationLossList.get(0).getOrgId() + "-" + importSubstationLossList.get(0).getMonth()
				+ "-" + importSubstationLossList.get(0).getYear() + "-" + ts;
		


		importSubstationLossList.forEach(importSubstationLoss -> {
			
			importSubstationLoss.setId(commonUtils.generateId());
			importSubstationLoss.setBatchKey(batchKey);
			importSubstationRepository.save(importSubstationLoss);
		});

		String ssLossFunction = "{call SUBSTATION_LOSS.START_IMPORT(?,?,?)}";

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
		} finally {

			try {
				if ((stmt != null) && (!stmt.isClosed())) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if ((conn != null) && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		
		return ResponseEntity.ok(res);
	}

	@CrossOrigin(origins = "*")
	@PatchMapping("/{id}")
	public ImportSubstationLoss updateImportSubstationLoss(@PathVariable(value = "id") String id,
			@RequestBody ImportSubstationLoss importSubstationLoss) throws OpenAccessException {

		return importSubstationRepository.save(importSubstationLoss);
	}

}