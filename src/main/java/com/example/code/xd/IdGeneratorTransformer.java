package com.example.code.xd;

import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.Transformer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@MessageEndpoint
public class IdGeneratorTransformer {

	private static final Log logger = LogFactory.getLog(IdGeneratorTransformer.class);
	
	@Autowired
	private ObjectMapper mapper;
	
	@Transformer(inputChannel="input",outputChannel="output")
	public String addId(String payload) throws Exception {
		//convert to map
		Map<String,Object> map = mapper.readValue(payload, new TypeReference<Map<String,Object>>() {});
		//generate an id
		map.put("id",UUID.randomUUID().toString());
		//convert back
		return mapper.writeValueAsString(map);
	}
	
}
