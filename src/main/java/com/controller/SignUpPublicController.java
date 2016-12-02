package com.controller;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.controller.def.ControllerDefaultPublic;
import com.model.User;
import com.service.UserService;
import com.util.StaticURL;

@RestController
@Validated
public class SignUpPublicController extends ControllerDefaultPublic<UserService, User> {

	@PostMapping(value = StaticURL.SIGNUP)
	@ResponseStatus(code = HttpStatus.OK)
	public void signUp(@RequestBody @Valid User user, BindingResult bindingResult) {
		getService().save(user);
	}

	// FIXME
	// test only
	@GetMapping(value = StaticURL.FIND_ALL)
	public Collection<User> teste() {
		return getService().findAll();
	}

	// FIXME
	// test only
	@GetMapping(value = StaticURL.FIND_BY_ID)
	@ResponseStatus(code = HttpStatus.OK)
	public User find(@PathVariable String id) {
		return getService().getRepository().findOne(Long.valueOf(id));
	}
}