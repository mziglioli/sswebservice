package com.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.controller.def.ControllerDefault;
import com.model.Category;
import com.service.CategoryService;
import com.util.StaticURL;

@RestController
@RequestMapping(value = StaticURL.CATEGORY)
public class CategoryController extends ControllerDefault<CategoryService, Category> {

}