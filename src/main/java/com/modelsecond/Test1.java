package com.modelsecond;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.model.EntityJpa;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "help_test1")
public class Test1 extends EntityJpa {

	private static final long serialVersionUID = -2966772094062829954L;

	@Id
	@Column(name = "test1_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter(value=AccessLevel.NONE)
	private Long id;

	@Column
	private String name;
}