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
import com.model.Category;
import com.service.CategoryService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { PersistenceConfigTest.class, TestConfiguration.class })
@SqlGroup({
	//@formatter:off	
	@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:category_insert.sql"),
	@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:category_clean.sql") 
	//@formatter:on	
})
public class CategoryTest {

	@Autowired
	private CategoryService categoryService;

	@Test
	public void insert() {
		Long qtde = categoryService.getRepository().count();
		assertEquals(Long.valueOf(2), qtde);
	}

	@Test
	public void checkArticlesFromCategory() {
		Category category = categoryService.getCategoryWithArticles(1L);
		assertEquals(3, category.getArticles().size());
	}

}