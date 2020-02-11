package com.szdtoo.mapper;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.szdtoo.model.MongoUser;

public interface MongoUserRepository extends MongoRepository<MongoUser, String>{
	
	public List<MongoUser> findByName(String name);

}
