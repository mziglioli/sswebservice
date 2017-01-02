package com.model;

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
public class HelpArticleUsefulness implements Serializable{

	private static final long serialVersionUID = 5148935944507304910L;
	@Setter(value=AccessLevel.NONE)
	private int id;
    private HelpArticle article;
    private String session;
    private Date date = null;
    private String locale;
    private boolean useful;

}