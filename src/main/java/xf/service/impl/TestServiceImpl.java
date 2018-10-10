package xf.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import xf.product.mapper.TestMapper;
import xf.service.TestService;

@Component 
public class TestServiceImpl implements TestService{

	@Autowired
	TestMapper testMapper;
	
	@Override
	public String test() {
		List<Map<String,Object>> list = testMapper.selectProduct();
		return "dsada";
	}
	
	
}
