package com.integration;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.conf.PersistenceConfigTest;
import com.conf.TestConfiguration;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.User;
import com.model.enuns.Status;
import com.repository.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { PersistenceConfigTest.class, TestConfiguration.class })
@Transactional
@Rollback
public class UserTest {

	private MockMvc mock;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private UserRepository repository;

	@Before
	public void setup() throws Exception {
		mock = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void count() {
		assertEquals(2, repository.count());
	}

	@Test
	public void oneRoles() {
		User user = repository.findOne(1L);
		assertEquals(true, user.isAdmin());
		assertEquals(true, user.isUser());
	}

	@Test
	public void twoRoles() {
		User user = repository.findOne(2L);
		assertEquals(false, user.isAdmin());
		assertEquals(true, user.isUser());
	}

	@Test
	public void testUser() throws Exception {
		MockHttpServletRequestBuilder result = get("http://localhost:8080/public/1");
		User user = buildUser();
		MvcResult mocResult;
		mocResult = mock.perform(result).andReturn();
		User userWeb = userFromJson(mocResult.getResponse().getContentAsString());
		assertEquals(userWeb, user);
		assertEquals(true, userWeb.isAdmin());
		assertEquals(true, userWeb.isUser());

	}

	private User buildUser() {
		return new User(1L, "admin", "admin@admin.com", "", Status.ACTIVE, "System Administrator", null, null);
	}

	private User userFromJson(String json) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(json, User.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}