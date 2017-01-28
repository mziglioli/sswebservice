package com.model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class EntityJpa implements Serializable{

	private static final long serialVersionUID = 3501653427034661829L;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date", nullable = false, updatable = false)
	private Calendar createdDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_date", nullable = true)
	private Calendar updatedDate;

	@Column(name = "created_by", nullable = false, updatable = false)
	private Long createdBy;

	@Column(name = "updated_by", nullable = true)
	private Long updatedBy;
	
	@Column(columnDefinition="tinyint(1) default 1")
	private boolean active = true;
	
	public abstract Long getId();
}