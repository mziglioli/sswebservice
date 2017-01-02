package com.controller;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.model.Category;
import com.model.User;
import com.service.CategoryService;
import com.service.UserService;
import com.util.StaticURL;

@RestController
@Validated
public class SignUpPublicController extends ControllerDefaultPublic<UserService, User> {

	@Autowired
	private CategoryService categoryService;
	
	@PostMapping(value = StaticURL.SIGNUP)
	@ResponseStatus(code = HttpStatus.OK)
	public void signUp(@RequestBody @Valid User user, BindingResult bindingResult) {
		getService().save(user);
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
}