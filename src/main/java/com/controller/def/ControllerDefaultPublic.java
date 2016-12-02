package com.controller.def;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.EntityJpa;
import com.service.ServiceDefault;
import com.util.StaticURL;

import lombok.Getter;

@RestController
@SuppressWarnings({ "rawtypes" })
@RequestMapping(value = StaticURL.PUBLIC)
public abstract class ControllerDefaultPublic<T extends ServiceDefault, E extends EntityJpa> {

	@Autowired
	@Getter
	protected T service;
}