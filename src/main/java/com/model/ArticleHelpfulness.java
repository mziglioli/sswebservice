package com.traveljigsaw.web.helpcentre;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by fordl on 02/06/2016.
 */
@Getter
@Setter
@NoArgsConstructor
public class ArticleHelpfulness implements Serializable {

	private static final long serialVersionUID = 5594347402872735677L;

	private Integer id;
	private HelpArticle helpArticle;
	private String locale;
	private int notHelpful;
	private int helpful;
	private int ranking;
}