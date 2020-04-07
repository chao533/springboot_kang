package com.kang.model.mongo;


import java.util.List;
import java.util.Map;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Document(collection="mongo_user")
public class MongoUser {

	private String id;
	
	private String name;
	
	private Boolean gender;
	
	private List<Map<String,Object>> goodsList;
}
