package com.ss.oa.integration.intsurplusunit;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import com.ss.oa.vo.SurplusUnit;
import com.ss.oa.vo.TempSurplusUnit;
import com.ss.oashared.common.CommonUtils;
import com.ss.oashared.common.OpenAccessException;

@RestController
public class IntSurplusUnitService {

	@Value("${ht.surplusunits.url}")
	private String htSurplusUnitServiceUrl;
	
	@Autowired
	private CommonUtils commonUtils;

	Connection conn = null;
	CallableStatement stmt = null;

	@Autowired
	private IntSurplusUnitRepository surRepo;

	@Autowired
	private IntSurplusUnitHelper surHelper;

	// @CrossOrigin(origins = "*")
	// @PostMapping("/surpost")
	// public SurplusUnit surplusUnitFromHtPost(@RequestBody List<TempSurplusUnit> tempSurplusUnit)
	// 		throws OpenAccessException, ParseException {

	// 	return SetValuesfromTempSurplus(tempSurplusUnit);
	// }

	
	@GetMapping(value="/get-surplusunits-from-ht")
	public String surplusUnitFromHt(@RequestParam(name="month",required=false) String month,
			@RequestParam(name="year",required=false) String year) throws OpenAccessException, ParseException {
//		Calendar cal = Calendar.getInstance();
//		cal.add(Calendar.MONTH, -1);
//
//		SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
//		String month = monthFormat.format(new Date(cal.getTimeInMillis()));
//		System.out.println(month);
//		SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
//		String year = yearFormat.format(new Date(cal.getTimeInMillis()));
//		System.out.println(year);

		String[] edcArray = { "432", "434", "439", "450", "470", "472", "474", "476", "478" };


			
			for (int i = 0; i < edcArray.length; i++) {
				try {
				String url = htSurplusUnitServiceUrl + "?org_code=" + edcArray[i] + "&reading_mnth=" + month + "&reading_yr=" + year;
				System.out.println(url);
				List<TempSurplusUnit> tempSurplusUnits =Arrays.asList(CommonUtils.getTemplate().getForObject( url, TempSurplusUnit[].class));
				System.out.println("in for loop");

				System.out.println(tempSurplusUnits.size());

					SetValuesfromTempSurplus(tempSurplusUnits);		
					
				} catch (RestClientException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			


		return "success";
	}

	private SurplusUnit SetValuesfromTempSurplus(List<TempSurplusUnit> tempSurplusUnits) throws ParseException {
		SurplusUnit surplusUnit = new SurplusUnit();
		String batchKey = commonUtils.generateId();

		try {
			System.out.println(tempSurplusUnits.size());

			tempSurplusUnits.forEach(tempSurplusUnit -> {
				try {
					System.out.println(tempSurplusUnit);

				String surplusRemarks = tempSurplusUnit.getReadingMnth() + '-' + tempSurplusUnit.getReadingYr() + '-'
						+ getCurrentTimeStamp();
				surplusUnit.setId(commonUtils.generateId());
				surplusUnit.setServiceNo(tempSurplusUnit.getServiceNo());
				surplusUnit.setSuplrCode(tempSurplusUnit.getSuplrCode());
				surplusUnit.setSuplrType(tempSurplusUnit.getSuplrType());
				surplusUnit.setSuplrName(tempSurplusUnit.getSuplrName());
				surplusUnit.setReadingDt(convertDateFromString(tempSurplusUnit.getReadingDt()));
				surplusUnit.setReadingMnth(tempSurplusUnit.getReadingMnth());
				surplusUnit.setReadingYr(tempSurplusUnit.getReadingYr());
				surplusUnit.setC24(tempSurplusUnit.getC24());
				surplusUnit.setC1(tempSurplusUnit.getC1());
				surplusUnit.setC2(tempSurplusUnit.getC2());
				surplusUnit.setC3(tempSurplusUnit.getC3());
				surplusUnit.setC4(tempSurplusUnit.getC4());
				surplusUnit.setC5(tempSurplusUnit.getC5());
				surplusUnit.setImportRemarks(surplusRemarks);
				surplusUnit.setBatchKey(batchKey);
				surRepo.save(surplusUnit);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		surHelper.incrementSurplusUnits(batchKey);
		return surplusUnit;
	}

	private LocalDate convertDateFromString(String readingDt) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
		String date = readingDt;
		LocalDate localDate = LocalDate.parse(date, formatter);
		System.out.println(formatter.format(localDate));
		return localDate;
	}

	private Timestamp getCurrentTimeStamp() {
		Date date = new Date();
		long time = date.getTime();
		Timestamp ts = new Timestamp(time);
		return ts;
	}

}
