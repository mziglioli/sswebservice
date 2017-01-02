package com.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Zigliolim
 */
@Getter
@Setter
@NoArgsConstructor
public class HelpArticleKeyWord implements Serializable {

	private static final long serialVersionUID = 4410601937300015269L;
	private int id;
	private String contentMessageKey;
	private Set<HelpArticle> helpArticles = new HashSet<HelpArticle>(0);
}