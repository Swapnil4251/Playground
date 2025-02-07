package com.practice.zensar;

public class JsonParsor {
//
//	private static final String FILE_PATH = "/Users/swapnilsarwade/Desktop/Java Programs/SampleOrder.json";
//	
//	public static void main(String[] args) throws Exception {
//		String str = new String(Files.readAllBytes(Paths.get(FILE_PATH)));
//		JSONObject json = (JSONObject) JSONSerializer.toJSON(str);
//		Map<String, Object> map = jsonToMap(json);
//		System.out.println("Order fields: "+map.get("orderField"));
//		System.out.println("Order fields: "+map.get("entityIdentificationField"));
//		
//	}
//	
//	public static Map<String, Object> jsonToMap(JSONObject json) throws JSONException {
//	    Map<String, Object> retMap = new HashMap<String, Object>();
//
//	    if(!json.isNullObject()) {
//	        retMap = toMap(json);
//	    }
//	    return retMap;
//	}
//
//	public static Map<String, Object> toMap(JSONObject object) throws JSONException {
//		Map<String, Object> map = new HashMap<String, Object>();
//		if (!object.isNullObject()) {
//		    Iterator<String> keysItr = object.keys();
//		    while(keysItr.hasNext()) {
//		        String key = keysItr.next();
//		        Object value = object.get(key);
//	
//		        if(value instanceof JSONArray) {
//		            value = toList((JSONArray) value);
//		        }
//	
//		        else if(value instanceof JSONObject) {
//		            value = toMap((JSONObject) value);
//		        }
//		        map.put(key, value);
//		    }
//		}
//	    return map;
//	}
//
//	public static List<Object> toList(JSONArray array) throws JSONException {
//	    List<Object> list = new ArrayList<Object>();
//	    for(int i = 0; i < array.size(); i++) {
//	        Object value = array.get(i);
//	        if(value instanceof JSONArray) {
//	            value = toList((JSONArray) value);
//	        }
//
//	        else if(value instanceof JSONObject) {
//	            value = toMap((JSONObject) value);
//	        }
//	        list.add(value);
//	    }
//	    return list;
//	}
//	
}
