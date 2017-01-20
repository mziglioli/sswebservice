package com.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.controller.def.ControllerDefault;
import com.model.Article;
import com.service.ArticleService;
import com.util.StaticURL;

@RestController
@RequestMapping(value = StaticURL.ARTICLE)
public class ArticleController extends ControllerDefault<ArticleService, Article> {

}