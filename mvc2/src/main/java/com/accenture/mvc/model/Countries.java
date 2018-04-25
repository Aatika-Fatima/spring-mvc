package com.accenture.mvc.model;

public enum Countries {
	INDIA("INDIA", "IN"), CHINA("CHINA", "CN"), FRANCE("FRANCE", "FR"), US("USA", "US");

	private String name, code;

	private Countries(String name, String code) {
		this.name = name;
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
