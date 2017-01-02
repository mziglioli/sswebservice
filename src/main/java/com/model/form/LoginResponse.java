package com.model.form;

import java.io.Serializable;

import com.model.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse implements Serializable{

	private static final long serialVersionUID = -2419527094434360042L;
	private String cookieXSRF;
	private String cookie;
	private User user;
}