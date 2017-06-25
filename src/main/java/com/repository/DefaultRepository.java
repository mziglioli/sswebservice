package com.repository;

import java.util.Calendar;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import com.model.EntityJpa;

@NoRepositoryBean
public interface DefaultRepository<T extends EntityJpa>  extends JpaRepository<T, Long>, JpaSpecificationExecutor<T> {

	public default void save2(T entity) {
		entity.setCreatedBy(0L);
		entity.setCreatedDate(Calendar.getInstance());
		entity.setActive(true);
		save(entity);
	}
}