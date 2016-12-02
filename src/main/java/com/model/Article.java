package com.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.util.StaticDB;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = StaticDB.ARTICLE)
public class Article implements EntityJpa, Serializable {

	private static final long serialVersionUID = 6942092712979147417L;

	@Id
	@Column(name = "article_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	@NotNull(message = "error.empty.title")
	@NotEmpty(message = "error.empty.title")
	private String title;

	@Column
	@NotNull(message = "error.empty.answer")
	@NotEmpty(message = "error.empty.answer")
	private String answer;

	@Column
	@NotNull(message = "error.empty.action")
	@NotEmpty(message = "error.empty.action")
	private String action;

	@Column(name = "action_name")
	@NotNull(message = "error.empty.actionName")
	@NotEmpty(message = "error.empty.actionName")
	private String actionName;

	@JsonIgnore
	@ManyToMany(mappedBy = "articles")
	private Set<Category> categories;
}