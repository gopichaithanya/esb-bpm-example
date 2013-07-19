package com.mulesoft.demo;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class Event {
	
	private String contents;
	private String destination;
	
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> getFields() throws JsonParseException, JsonMappingException, IOException {
		return (Map<String, Object>)(new ObjectMapper()).readValue(contents, HashMap.class);
	}
	
}
