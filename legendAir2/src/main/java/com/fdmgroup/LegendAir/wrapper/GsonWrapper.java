package com.fdmgroup.LegendAir.wrapper;

import com.google.gson.Gson;

public class GsonWrapper {
	private Gson gson;
	
	public GsonWrapper() {
		gson = new Gson();
	}
	
	public String toJson(Object src) {
		return gson.toJson(src);
	}
}
