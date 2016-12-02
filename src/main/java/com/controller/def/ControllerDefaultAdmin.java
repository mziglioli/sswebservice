package com.controller.def;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.model.EntityJpa;
import com.service.ServiceDefault;
import com.util.StaticURL;
import com.util.StaticValue;

import lombok.Getter;

@SuppressWarnings({ "rawtypes", "unchecked" })
@RestController
public abstract class ControllerDefaultAdmin<T extends ServiceDefault, E extends EntityJpa> {

	@Autowired
	@Getter
	protected T service;

	@PostMapping(value = StaticURL.SAVE)
	@PreAuthorize(StaticValue.HAS_ROLE_ADMIN)
	@ResponseStatus(code = HttpStatus.OK)
	protected void save(@RequestBody E entity) {
		getService().save(entity);
	}

	@PutMapping(value = StaticURL.UPDATE)
	@PreAuthorize(StaticValue.HAS_ROLE_ADMIN)
	@ResponseStatus(code = HttpStatus.OK)
	protected void update(@RequestBody E entity) {
		getService().save(entity);
	}

	@GetMapping(value = StaticURL.FIND_ALL)
	@PreAuthorize(StaticValue.HAS_ROLE_ADMIN)
	protected Collection<E> findAll() {
		return getService().findAll();
	}

	@GetMapping(value = StaticURL.FIND_BY_ID)
	@PreAuthorize(StaticValue.HAS_ROLE_ADMIN)
	protected E find(@PathVariable String id) {
		return (E) getService().getRepository().findOne(id);
	}

	@DeleteMapping(value = StaticURL.DELETE)
	@PreAuthorize(StaticValue.HAS_ROLE_ADMIN)
	@ResponseStatus(code = HttpStatus.OK)
	protected void delete(@PathVariable String id) {
		getService().getRepository().delete(id);
	}
}