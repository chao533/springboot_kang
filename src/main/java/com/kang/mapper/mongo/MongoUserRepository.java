package com.kang.mapper.mongo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.kang.model.mongo.MongoUser;


public interface MongoUserRepository extends MongoRepository<MongoUser, String>{
	
	public List<MongoUser> findByName(String name);

}
