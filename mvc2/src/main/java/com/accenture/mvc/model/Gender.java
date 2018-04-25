package com.accenture.mvc.model;

public enum Gender {
	MALE("MALE"), FEMALE("FEMALE"), OTHERS("OTHERS");

	private String description;

	private Gender(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
