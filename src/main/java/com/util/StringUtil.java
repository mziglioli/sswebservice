package com.util;

public class StringUtil {

	public static String removeNotNumbers(String s){
		return s.replaceAll("[^\\d.]", "");
	}
}
