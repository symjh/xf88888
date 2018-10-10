package xf.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import xf.service.TestService;

@RestController
@EnableAutoConfiguration
@RequestMapping("/test")
public class TestBootController {
	
	@Autowired
	TestService testService;
	
	@RequestMapping("/n1")
	public String n1() {
		
		return testService.test();
	}
	
	
	
	
}
