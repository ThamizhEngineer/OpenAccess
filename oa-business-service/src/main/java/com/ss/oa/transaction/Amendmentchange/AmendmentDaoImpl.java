package com.ss.oa.transaction.Amendmentchange;

import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;

import com.ss.oa.common.BaseDaoJdbc;

@Component
public class AmendmentDaoImpl  extends BaseDaoJdbc implements AmendmentDao {

	@Resource
	private JdbcOperations jdbcOperations;

	@Resource
	private DataSource dataSource;

	private SimpleJdbcCall functionImportMr;
	private SimpleJdbcCall functionProcessGenStmt;
	private SimpleJdbcCall functionImportProcessMri;

	private String mr_month;
	private String mr_year;
	private String batch_Id;
	private String pgs_month;
	private String pgs_year;

	@Override
	public String importMeterReading(Map<String, String> criteria) {
	

				batch_Id = criteria.get("batch-id");
				mr_month=criteria.get("month");
				mr_year=criteria.get("year");

				JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
				jdbcTemplate.setResultsMapCaseInsensitive(true);
				functionImportMr = new SimpleJdbcCall(jdbcTemplate).withFunctionName("IMPORT_MR");

				return CallImportMr(batch_Id, mr_month, mr_year);
	}
	public String CallImportMr(String batch_Id, String mr_month, String mr_year) {
		SqlParameterSource in = new MapSqlParameterSource().addValue("V_BATCH_ID", batch_Id).addValue("month", mr_month)
				.addValue("year", mr_year);
		return functionImportMr.executeFunction(String.class, in);
	}

	@Override
	public String ProcessGenerationStatements(Map<String, String> criteria) {
		pgs_month = criteria.get("month");
		pgs_year = criteria.get("year");

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.setResultsMapCaseInsensitive(true);
		functionProcessGenStmt = new SimpleJdbcCall(jdbcTemplate).withFunctionName("PROCESS_GEN_STMT");

		return CallProcessGenStmt(pgs_month, pgs_year);
	}
	public String CallProcessGenStmt(String pgs_month, String pgs_year) {
		SqlParameterSource in = new MapSqlParameterSource().addValue("V_MONTH", pgs_month).addValue("V_YEAR", pgs_year);
		return functionProcessGenStmt.executeFunction(String.class, in);
	}

	@Override
	public String importAndProcessMri(Map<String, String> criteria) {
		
		batch_Id = criteria.get("batch-id");

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.setResultsMapCaseInsensitive(true);
		functionImportProcessMri = new SimpleJdbcCall(jdbcTemplate).withFunctionName("IMPORT_AND_PROCESS_MRI_BATCH");

		SqlParameterSource in = new MapSqlParameterSource().addValue("V_IMP_BATCH_ID", batch_Id);
		return functionImportProcessMri.executeFunction(String.class, in);
	}

}
