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
public class DefaultPageForm implements Serializable{

	private static final long serialVersionUID = 4403665213675951081L;
	private String  search;
}