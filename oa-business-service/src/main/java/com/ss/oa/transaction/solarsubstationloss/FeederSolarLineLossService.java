package com.ss.oa.transaction.solarsubstationloss;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ss.oashared.common.CommonUtils;

@RestController
@Scope("prototype")
@RequestMapping("/transaction/solarLineLoss-substation")
public class FeederSolarLineLossService {

	@Autowired
	FeederSolarLineLossRepository feederSolarLineLossRepository;

	@Autowired
	CommonUtils utils;

	@PostMapping
	public ResponseEntity<?> addSubstation(@RequestBody FeederSolarLineLoss solarLineLoss) {	
		
		try {		
			if (solarLineLoss != null
					&& !feederSolarLineLossRepository.existsByFeederIdAndMonthAndYear(solarLineLoss.getFeederId(),
							solarLineLoss.getMonth(), solarLineLoss.getYear())) {

				Date date = new Date();
				long time = date.getTime();
				Timestamp ts = new Timestamp(time);

				String batchKey = solarLineLoss.getOrgId() + "-" + solarLineLoss.getMonth() + "-"
						+ solarLineLoss.getYear() + "-" + ts;

				solarLineLoss.setId(utils.generateId());
				solarLineLoss.setBatchKey(batchKey);

				feederSolarLineLossRepository.save(solarLineLoss);
			}
			return ResponseEntity.ok("Solarline loss saved Sucessfully");

		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}
