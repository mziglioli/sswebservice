package com.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MyException implements Serializable {

	private static final long serialVersionUID = -6566372946232080708L;
	private String[] error;
}