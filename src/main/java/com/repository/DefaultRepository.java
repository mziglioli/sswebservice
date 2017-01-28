package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import com.model.EntityJpa;

@NoRepositoryBean
public interface DefaultRepository<T extends EntityJpa>  extends JpaRepository<T, Long>, JpaSpecificationExecutor<T> {

}