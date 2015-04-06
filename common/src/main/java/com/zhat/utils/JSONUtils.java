package com.zhat.utils;

import org.json.JSONException;
import org.json.JSONObject;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JSONUtils {
	public static String EMPTY_JSON_TEXT = "{}";
	
	public static String toJSONObjectText(Object object) {
		Gson gson = new GsonBuilder()
				.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
		return gson.toJson(object);
	}
	
	public static JSONObject toJSONObject(Object object) throws JSONException {
		return new JSONObject(toJSONObjectText(object));
	}
}
