package com.app.util;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppUtilTest {

	private String jsonStr = "{ \"root\" : { \"A\" : 10, \"B\" : { \"b1\" : 20 }, \"C\" : [ { \"c1\" : 30, \"c2\" : [{\"c21\": 40, \"c22\" : { \"c221\" : \"c221\" } }, {\"c21\" : 50 }] }, { \"c1\" : 60 } ] }}";
	
	@Test
	public void test() throws Exception {
		JSONObject json = (JSONObject) new JSONParser().parse(jsonStr);
		System.out.println(json);
		
		Object value = AppUtil.getObjectFromPath(json, "root");
		System.out.println("root : " + value);
		
		value = AppUtil.getObjectFromPath(json, "root.A");
		System.out.println("root.A : " + value);
		
		value = AppUtil.getObjectFromPath(json, "root.B.b1");
		System.out.println("root.B.b1 : " + value);
		
		value = AppUtil.getObjectFromPath(json, "root.C");
		System.out.println("root.C : " + value);
		
		value = AppUtil.getObjectFromPath(json, "root.C[0]");
		System.out.println("root.C[0] : " + value);
		
		value = AppUtil.getObjectFromPath(json, "root.C[1]");
		System.out.println("root.C[1] : " + value);
		
		value = AppUtil.getObjectFromPath(json, "root.C[0].c1");
		System.out.println("root.C[0].c1 : " + value);
		
		value = AppUtil.getObjectFromPath(json, "root.C[1].c1");
		System.out.println("root.C[1].c1 : " + value);
		
		value = AppUtil.getObjectFromPath(json, "root.C[0].c2");
		System.out.println("root.C[0].c2 : " + value);
		
		value = AppUtil.getObjectFromPath(json, "root.C[0].c2[0]");
		System.out.println("root.C[0].c2[0] : " + value);
		
		value = AppUtil.getObjectFromPath(json, "root.C[0].c2[0].c21");
		System.out.println("root.C[0].c2[0].c21 : " + value);
		
		value = AppUtil.getObjectFromPath(json, "root.C[0].c2[0].c22");
		System.out.println("root.C[0].c2[0].c22 : " + value);
		
		value = AppUtil.getObjectFromPath(json, "root.C[0].c2[0].c22.c221");
		System.out.println("root.C[0].c2[0].c22.c221 : " + value);
	}
	
	@Test
	public void testFormatMessage() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("name", "Swapnil Sarwade");
		params.put("city", "Ambajogai");
		params.put("profession", "Software Engineer");
		
		String result = AppUtil.formatMessage("My name is {name}. I live in {city} and I am a {profession}.", params);
		System.out.println(AppUtil.formatMessage("My name is {name}. I live in {city} and I am a {profession}.", params));
		assertEquals("My name is Swapnil Sarwade. I live in Ambajogai and I am a Software Engineer.", result);
	}
}
