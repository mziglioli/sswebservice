package com.controller.def;

import java.util.Collection;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.model.EntityJpa;
import com.service.ServiceDefault;
import com.util.StaticURL;
import com.util.StaticValue;

import lombok.Getter;

@SuppressWarnings({ "rawtypes", "unchecked" })
public abstract class ControllerDefault<T extends ServiceDefault, E extends EntityJpa> {

	@Autowired
	@Getter
	protected T service;

	@PostMapping(value = StaticURL.SAVE)
	@PreAuthorize(StaticValue.HAS_ROLE_USER)
	@ResponseStatus(code = HttpStatus.OK)
	protected void save(@Valid @RequestBody E entity, BindingResult bindingResult) {
		getService().save(entity);
	}

	@PutMapping(value = StaticURL.UPDATE)
	@PreAuthorize(StaticValue.HAS_ROLE_USER)
	@ResponseStatus(code = HttpStatus.OK)
	protected void update(@Valid @RequestBody E entity, BindingResult bindingResult) {
		getService().save(entity);
	}

	@GetMapping(value = StaticURL.FIND_ALL)
	@PreAuthorize(StaticValue.HAS_ROLE_USER)
	protected Collection<E> findAll() {
		return getService().findAll();
	}

	@GetMapping(value = StaticURL.FIND_BY_ID)
	@PreAuthorize(StaticValue.HAS_ROLE_USER)
	protected E find(@PathVariable String id) {
		if (StringUtils.isNumeric(id)) {
			return (E) getService().getRepository().findOne(Long.valueOf(id));
		}
		return null;
	}

	@DeleteMapping(value = StaticURL.DELETE)
	@PreAuthorize(StaticValue.HAS_ROLE_USER)
	@ResponseStatus(code = HttpStatus.OK)
	protected void delete(@PathVariable String id) {
		getService().getRepository().delete(id);
	}
}