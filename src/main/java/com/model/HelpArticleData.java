package com.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by FordL on 20/07/2016.
 */
@Getter
@Setter
public class HelpArticleData implements Serializable{

	private static final long serialVersionUID = -6228252702950108569L;

	private int searchedArticlesId;
	private HelpArticle article;
	private HelpSearch search;
	
}