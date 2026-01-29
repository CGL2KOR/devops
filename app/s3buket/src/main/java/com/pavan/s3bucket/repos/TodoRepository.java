package com.pavan.s3bucket.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pavan.s3bucket.pojo.Todo;

@Repository
public interface TodoRepository extends CrudRepository<Todo, Integer>{
	
	Todo findByTitle(String title);
}
