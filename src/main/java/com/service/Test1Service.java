package com.service;

import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.modelsecond.Test1;
import com.repositorysecond.Test1Repository;

@Service
@CacheConfig(cacheNames = "tests")
public class Test1Service extends ServiceDefault<Test1, Test1Repository> {
	
	@CacheEvict(cacheNames = "tests", allEntries = true)
    public void clearCache(){}

    @Cacheable
    public List<Test1> findAllCached() {
        System.out.println("Executing: findAllCached");
        return repository.findAll();
    }
    
    @Cacheable(key="#id")
    public Test1 findByIdCached(Long id) {
        System.out.println("Executing: findBuId " + id);
        return repository.findOne(id);
    }
    
    @Cacheable(key="{#id, #session}")
    public Test1 findByIdCached(Long id, String session) {
        System.out.println("Executing: findBuId " + id + " session: " + session);
        //session logic
        return repository.findOne(id);
    }
    
    @CachePut(key="#id")
    public Test1 put(Long id){
    	Test1 test1 = repository.findOne(id);
    	return save(test1);
    }
    
    @CacheEvict
    public Test1 post(){
    	Test1 test1 = new Test1();
    	test1.setName("name");
    	test1 = save(test1);
    	return test1;
    }
}