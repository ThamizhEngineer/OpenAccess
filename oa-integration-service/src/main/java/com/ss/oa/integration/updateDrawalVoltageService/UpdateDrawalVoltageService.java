package com.ss.oa.integration.updateDrawalVoltageService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ss.oa.vo.Code;
import com.ss.oashared.common.CommonUtils;

@RestController
@RequestMapping(path = "/update-drawal-voltages")
public class UpdateDrawalVoltageService {
	@Value("${company.service.url}")
	private String companyServiceUrl;

	@Value("${codes.url}")
	private String codesUrl;

	@Autowired
	UpdateDrawalVoltageRepository updateDrawalVoltageRepository;
	@Autowired
	ServiceRepository serviceRepository;

	@GetMapping("/{number}")
	public Service getAllUpdateDrawalVoltage(@PathVariable(value = "number") String number) {
		List<UpdateDrawalVoltage> updateDrawalVoltages = updateDrawalVoltageRepository.findByServiceNo(number);

		RestTemplate restTemplate = CommonUtils.getTemplate();
		String url = companyServiceUrl;
		Service service = restTemplate.getForObject(url + number, Service.class);

		String codeUrl = codesUrl;

		Code[] codes = restTemplate.getForObject(codeUrl, Code[].class);

		List<Code> code = Arrays.asList(codes);

		for (Code code1 : code) {
			for (UpdateDrawalVoltage updateDrawalVoltage : updateDrawalVoltages) {
				
				if (updateDrawalVoltage.getVoltage().equals(code1.getValueDesc())) {
					String voltageCode = code1.getValueCode();
					service.setVoltageCode(voltageCode);
					serviceRepository.save(service);
					
				}

			}

		}
		return service;
	}
}
