package com.security.user;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "authority")
public class Authority implements GrantedAuthority, Serializable {

	private static final long serialVersionUID = 4026573955643452466L;
	@NotNull
	private String authority;
}