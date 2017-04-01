package com.web;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.conf.PersistenceConfigTest;
import com.conf.TestConfiguration;
import com.config.security.SecurityConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.MyException;
import com.model.User;
import com.model.enuns.Status;
import com.util.StaticURL;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { SecurityConfig.class, TestConfiguration.class, PersistenceConfigTest.class })
public class SignUpTest {

	private MockMvc mock;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setup() throws Exception {
		mock = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void signupUserInvalid() throws Exception {
		User user = new User(null, null, "", "teste", Status.ACTIVE, "", null, null);
		ObjectMapper mapper = new ObjectMapper();
		String jsonContent = mapper.writeValueAsString(user);

		MvcResult mocResult = mock.perform(post(StaticURL.SIGNUP_TEST).content(jsonContent)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andReturn();
		assertEquals(400, mocResult.getResponse().getStatus());

		String content = mocResult.getResponse().getContentAsString();
		ObjectMapper oj = new ObjectMapper();
		MyException exc = oj.readValue(content, MyException.class);
		assertEquals("error.empty.name", exc.getError()[0]);
		assertEquals("error.empty.username", exc.getError()[1]);
		assertEquals("ação", "ação");
	}

	@Test
	@Transactional
	@Rollback
	public void signupUserSuccess() throws Exception {
		User user = new User(null, "name", "username", null, Status.ACTIVE, "", null, null);
		ObjectMapper mapper = new ObjectMapper();
		String jsonContent = mapper.writeValueAsString(user);

		MvcResult mocResult = mock.perform(post(StaticURL.SIGNUP_TEST).content(jsonContent)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andReturn();
		assertEquals(200, mocResult.getResponse().getStatus());
		assertEquals("ação", "ação");
	}
}