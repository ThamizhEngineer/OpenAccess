package com.ss.oa.transaction.substationloss;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
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
import com.ss.oa.transaction.vo.SubstationLoss;
import com.ss.oashared.common.CommonUtils;

@Scope("prototype")
@RestController
@RequestMapping("/transaction/substation-loss")
public class SubstationLossService {

	@Autowired
	SubstationLossRepository substationLossRepository;

	@Autowired
	private CommonUtils commonUtils;

	@CrossOrigin(origins = "*")
	@GetMapping
	public Iterable<SubstationLoss> getAllSubstationLoss(@RequestParam(name = "month", required = false) String month,
			@RequestParam(name = "year", required = false) String year,
			@RequestParam(name = "org-id", required = false) String orgId) throws OpenAccessException {

		return substationLossRepository.getSubstationLoss(month, year, orgId);
	}

	@CrossOrigin(origins = "*")
	@GetMapping("/{id}")
	public SubstationLoss getSubstationLossById(@PathVariable(value = "id") String id) throws OpenAccessException {
		return substationLossRepository.findById(id).get();
	}

	@CrossOrigin(origins = "*")
	@PostMapping
	public ResponseEntity<?> addSubstationLoss(@RequestBody SubstationLoss substationLoss) throws OpenAccessException {

		try {
			Date date = new Date();
			long time = date.getTime();
			Timestamp ts = new Timestamp(time);
			String batchKey = substationLoss.getOrgId() + "-" + substationLoss.getMonth() + "-"
					+ substationLoss.getYear() + "-" + ts;
			substationLoss.setId(commonUtils.generateId());
			substationLoss.setBatchKey(batchKey);

			Map<String, Object> response;

			if (substationLoss.getFeederId() != null) {

				if (!substationLossRepository.existsByFeederIdAndMonthAndYear(substationLoss.getFeederId(),
						substationLoss.getMonth(), substationLoss.getYear())) {

					substationLossRepository.save(substationLoss);
				} else {
					response = new HashMap<>();
					response.put("status", HttpStatus.ALREADY_REPORTED);
					response.put("message", "Line loss saved already");
					return ResponseEntity.ok().body(response);
				}
				response = new HashMap<>();
				response.put("status", HttpStatus.OK);
				response.put("message", substationLoss.getFuelType() + " line loss saved sucessfully");
				return ResponseEntity.ok().body(response);
			} else {
				return ResponseEntity.ok(substationLossRepository.save(substationLoss));
			}
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@CrossOrigin(origins = "*")
	@PatchMapping("/{id}")
	public SubstationLoss updateSubstationLoss(@PathVariable(value = "id") String id,
			@RequestBody SubstationLoss SubstationLoss) throws OpenAccessException {
		return substationLossRepository.save(SubstationLoss);
	}
}