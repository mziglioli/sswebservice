package com.model.enuns;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Status {
	//@formatter:off
	ACTIVE("Active"), 
	UNACTIVE("Unactive"), 
	DELETED("Deleted");
	//@formatter:on

	private String value;
}