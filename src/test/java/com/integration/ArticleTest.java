package com.integration;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.conf.PersistenceConfigTest;
import com.conf.TestConfiguration;
import com.model.Article;
import com.service.ArticleService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { PersistenceConfigTest.class, TestConfiguration.class })
@SqlGroup({
	//@formatter:off	
	@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:category_insert.sql"),
	@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:category_clean.sql") 
	//@formatter:on	
})
public class ArticleTest {

	@Autowired
	private ArticleService service;

	@Test
	public void insert() {
		Long qtde = service.getRepository().count();
		assertEquals(Long.valueOf(4), qtde);
	}

	@Test
	public void checkOneCategoriesFromArticles() {
		Article article = service.getArticleWithCategories(1L);
		assertEquals(1, article.getCategories().size());
	}

	@Test
	public void checkTwoCategoriesFromArticles() {
		Article article = service.getArticleWithCategories(3L);
		assertEquals(2, article.getCategories().size());
	}

}