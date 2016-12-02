package com.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.controller.def.ControllerDefaultAdmin;
import com.model.User;
import com.service.UserService;
import com.util.StaticURL;
import com.util.StaticValue;

@RestController
@RequestMapping(value = StaticURL.USER)
public class UserController extends ControllerDefaultAdmin<UserService, User> {

	@Override
	@PostMapping(value = StaticURL.SAVE)
	@PreAuthorize(StaticValue.HAS_ROLE_USER)
	@ResponseStatus(code = HttpStatus.CREATED)
	protected void save(@RequestBody User entity) {
		getService().save(entity);
	}

	@GetMapping(value = StaticURL.FIND_BY_ID)
	@PreAuthorize(StaticValue.HAS_ROLE_USER)
	protected User find(@PathVariable String id) {
		return getService().findUserById(id);
	}
}