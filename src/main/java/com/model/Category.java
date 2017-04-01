package com.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.model.form.LoginForm;
import com.util.StaticDB;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = StaticDB.TABLE_CATEGORY, uniqueConstraints = { @UniqueConstraint(columnNames = { "name" }) })
@JsonIgnoreProperties(value = { "articles" })
public class Category extends EntityJpa {

	private static final long serialVersionUID = 6785222895629356881L;

	@Id
	@Column(name = "category_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	@NotNull(message = "error.empty.name")
	@NotEmpty(message = "error.empty.name")
	private String name;

	@Column
	@NotNull(message = "error.empty.icon")
	@NotEmpty(message = "error.empty.icon")
	private String icon;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = StaticDB.TABLE_ARTICLE_CATEGORY, 
		joinColumns = {@JoinColumn(name = "category_id", referencedColumnName = "category_id") }, 
		inverseJoinColumns = {@JoinColumn(name = "article_id", referencedColumnName = "article_id") })
	private Set<Article> articles;
}