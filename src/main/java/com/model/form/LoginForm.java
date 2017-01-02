package com.model.form;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginForm implements Serializable {
	private static final long serialVersionUID = 143211960207743749L;

	private String username;
	private String password;
}