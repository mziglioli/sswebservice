package com.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HelpArticle implements Serializable {

	private static final long serialVersionUID = -1199405213260916276L;

	private int articleId;
	private String action;
	private String actionName;
	private String answer;
	private boolean completedBooking;
	private boolean booked;
	private boolean notBooked;
	private String question;
	private Set<ArticleHelpfulness> articlesHelpfulness;
	private Set<HelpArticleData> helpArticleData;
	private Set<HelpArticleKeyWord> keyWords = new HashSet<HelpArticleKeyWord>(0);
	private Set<HelpArticleUsefulness> usefulness = new HashSet<HelpArticleUsefulness>(0);
	private Set<HelpArticleOpen> openEvents = new HashSet<HelpArticleOpen>(0);	
	private Set<HelpArticleCategory> categories = new HashSet<HelpArticleCategory>(0);
	
	public List<Integer> getCategoriesIds(){
		if(categories != null){
			return categories.stream().map(HelpArticleCategory::getId).collect(Collectors.toList());
		}
		return null;
	}
	
	public boolean hasCategoriesId(int categoryId){
		if(categories != null){
			try(Stream<Integer> result = categories.stream().filter(c -> c.getId() == categoryId).					
					map(HelpArticleCategory::getId)){
				return result.findFirst().isPresent();
			}
		}
		return false;
	}
}