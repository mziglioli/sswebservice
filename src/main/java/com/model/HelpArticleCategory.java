package com.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Zigliolim
 */
@Getter
@Setter
@NoArgsConstructor
public class HelpArticleCategory implements Serializable {

	private static final long serialVersionUID = -5704904230795392394L;

	@Setter(value=AccessLevel.NONE)
	private int id;
	private String messageKey;
	private String icon;
	private int rank;
	private boolean active;
	private Set<HelpArticle> helpArticles = new HashSet<HelpArticle>(0);
}