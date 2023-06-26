package com.ss.oashared.common;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class PingService {
	@GetMapping("/ping")
	public Object ping() {
		return "service works";
	}
}
