package com.kang.mapper;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.kang.model.MongoUser;

public interface MongoUserRepository extends MongoRepository<MongoUser, String>{
	
	public List<MongoUser> findByName(String name);

}
