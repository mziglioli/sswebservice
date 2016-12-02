package com.model.enuns;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Authorities {

	//@formatter:off
	USER("ROLE_USER")
	, MANAGER("ROLE_MANAGER")
	, ADMIN("ROLE_ADMIN");
	//@formatter:on

	private String role;

	public static List<String> getAuthorities() {
		return Stream.of(Authorities.values()).map(Authorities::getRole).collect(Collectors.toList());
	}
}
