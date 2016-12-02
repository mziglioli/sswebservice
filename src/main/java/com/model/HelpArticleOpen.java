package com.traveljigsaw.web.helpcentre;

import java.io.Serializable;
import java.util.Date;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Zigliolim
 * */
@Setter
@Getter
@NoArgsConstructor
public class HelpArticleOpen implements Serializable{

	private static final long serialVersionUID = -8789517765604564648L;
	@Setter(value=AccessLevel.NONE)
	private int id;
    private HelpArticle article;
    private Date date = null;
    private String locale;

}