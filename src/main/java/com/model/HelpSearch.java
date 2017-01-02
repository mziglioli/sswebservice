package com.traveljigsaw.web.helpcentre;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by FordL on 20/07/2016.
 */
@Getter
@Setter
@NoArgsConstructor
public class HelpSearch implements Serializable{

	private static final long serialVersionUID = -6401046438410824829L;

	private int searchId;
	private String cor;
	private String device;
	private String locale;
	private boolean match;
	private String searchedTerm;
	private Date date = null;
	private Set<HelpArticleData> helpArticleData;
	
	public void addHelpArticleData(HelpArticleData data){
		if(helpArticleData == null){
			helpArticleData = new HashSet<>();
		}
		helpArticleData.add(data);
	}
}