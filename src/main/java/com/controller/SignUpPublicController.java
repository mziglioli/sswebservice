package com.controller;

import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.controller.def.ControllerDefaultPublic;
import com.model.Article;
import com.model.Category;
import com.model.User;
import com.modelsecond.Test1;
import com.service.ArticleService;
import com.service.CategoryService;
import com.service.Test1Service;
import com.service.UserService;
import com.util.StaticURL;

@RestController
@Validated
public class SignUpPublicController extends ControllerDefaultPublic<UserService, User> {

	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ArticleService articleService;
	
	@PostMapping(value = StaticURL.SIGNUP)
	@ResponseStatus(code = HttpStatus.OK)
	public void signUp(@RequestBody @Valid User user, BindingResult bindingResult) {
		getService().save(user);
	}
	
	@GetMapping(value = "/login")
	@ResponseStatus(code = HttpStatus.OK)
	public String login() {
		return "login :)";
	}
	
	@GetMapping(value = StaticURL.LOGIN+"/success")
	@ResponseStatus(code = HttpStatus.OK)
	public String successLogin() {
		return "Welcome :)";
	}
	
	@GetMapping(value = StaticURL.SUCCESS_LOGOUT)
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public String successLogout() {
		return "Thanks for use our service :)";
	}

	// FIXME
	// test only
	@GetMapping(value = StaticURL.FIND_BY_ID)
	@ResponseStatus(code = HttpStatus.OK)
	public User find(@PathVariable String id) {
		return getService().getRepository().findOne(Long.valueOf(id));
	}
	
	@GetMapping(value = StaticURL.FIND_ALL)
	public Collection<User> teste() {
		return getService().findAll();
	}
	
	@GetMapping(value = StaticURL.CATEGORY + StaticURL.FIND_ALL)
	public Collection<Category> getCategories() {
		return categoryService.findAll();
	}
	
	@GetMapping(value = StaticURL.CATEGORY + StaticURL.FIND_BY_ID)
	public Category getCategoryById(@PathVariable String id) {
		return categoryService.getRepository().findOne(Long.valueOf(id));
	}
	
	@PostMapping(value = StaticURL.SAVE)
	@ResponseStatus(code = HttpStatus.OK)
	protected void save(@Valid @RequestBody Category entity, BindingResult bindingResult) {
//		categoryService.save(entity);
		System.out.println("save: " + String.valueOf(entity));
	}

	@PutMapping(value = StaticURL.UPDATE)
	@ResponseStatus(code = HttpStatus.OK)
	protected void update(@Valid @RequestBody Category entity, BindingResult bindingResult) {
		//categoryService.save(entity);
		System.out.println("update: " + String.valueOf(entity));
	}
	
	@GetMapping(value = StaticURL.ARTICLE + StaticURL.FIND_ALL)
	public Page<Article> getArticles() {
		return articleService.find(new PageRequest(0, 10));
	}
	
	@Autowired
	private Test1Service test1Service;
	
	@GetMapping(value = "/test1/")
	public List<Test1> getTest1() {
		return test1Service.findAllCached();
	}
	@GetMapping(value = "/test1/{id}")
	public Test1 id(@PathVariable String id) {
		return test1Service.findByIdCached(Long.valueOf(id));
	}
	@GetMapping(value = "/test1/{id}/{session}")
	public Test1 idsession(@PathVariable String id, @PathVariable String session) {
		return test1Service.findByIdCached(Long.valueOf(id), session);
	}
	@GetMapping(value = "/test1/clear")
	@ResponseStatus(code = HttpStatus.OK)
	public void clearCache() {
		test1Service.clearCache();
	}
	@GetMapping(value = "/test1/put/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public Test1 put(@PathVariable String id) {
		return test1Service.put(Long.valueOf(id));
	}
	@GetMapping(value = "/test1/post")
	@ResponseStatus(code = HttpStatus.OK)
	public Test1 post() {
		return test1Service.post();
	}
}